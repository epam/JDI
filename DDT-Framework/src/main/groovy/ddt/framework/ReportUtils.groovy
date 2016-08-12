package ddt.framework

import groovy.util.logging.Log4j
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import groovy.xml.*
import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern

import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStepResult
import com.eviware.soapui.impl.wsdl.teststeps.ManualTestStepResult
import com.eviware.soapui.impl.wsdl.teststeps.RestRequestStepResult
import com.eviware.soapui.impl.wsdl.teststeps.JdbcTestStepResult
import com.eviware.soapui.model.testsuite.TestCaseRunner
import com.eviware.soapui.model.testsuite.TestStepResult
import com.eviware.soapui.model.testsuite.TestStepResult.TestStepStatus

import ddt.framework.model.TestCaseResult

@Log4j
/**
 * Utils used after test cases run to collect data for reporting
 */
class ReportUtils {

  static final Pattern TC_NAME_PATTERN = ~/^\[(TC.+)\]\s*Step(.+)/

  private static String projectName
  private static String suiteName
  private static String reportPath
  private final  Object json
  
  ReportUtils (String projectName, String suiteName, String path, TestCaseRunner testRunner) {
    this.projectName = projectName
	this.suiteName = suiteName
    this.reportPath = path
	this.json = generateJson(testRunner)
  }

  /**
  * Get value of json field
  */
  Object getJson() {
     return json
  }

  
  /**
  * Try to find current test case in collected test cases list by Name
  *
  * @param testcasesList - list of collected TestCaseResult objects
  * @param testcaseName  - test case name to compare
  * @return Handle of found test case or NULL
  */
  private static TestCaseResult findResultByTestCaseName(ArrayList<String> testcasesList, String testcaseName) {
      TestCaseResult findResult = null
  
      if ( testcasesList.isEmpty() ) return null
	  
	  testcasesList.each { testcase ->
		  //log.info "${testcase.name}  ???  ${testcaseName}"
	      if ( testcase.name == testcaseName ) {
		    findResult = testcase
		  }
	  }
  
      return findResult
  }

  
  /**
  * Summarise test cases info and add it to test suite Map object
  *
  * @param testcasesList - list of collected TestCaseResult objects
  * @param tsMap         - test suite Map object handle
  */
  private static void addSumTestsInfo(ArrayList<String> testcasesList, LinkedHashMap<String,Object> tsMap) {
    Integer tcCount  = 0
	Integer tcFailed = 0
	Long    tcTimes  = 0
	
	testcasesList.each { testcase ->
	   tcCount++
	   tcTimes = tcTimes + testcase.time
	   if ( testcase.failSign )
	     tcFailed++
	}

    tsMap.put("tests",tcCount?.toString())
	tsMap.put("time",tcTimes?.toString())
	tsMap.put("failures",tcFailed?.toString())
	
  }


  /**
  * Generate JSON object from test cases run results
  *
  * @param testRunner - Handle on current testRunner object
  * @return JSON Object
  */
  Object generateJson (TestCaseRunner testRunner) {
   //log.info " ... into generateJson ...  "

   def testcasesList = new ArrayList()
   def tsMap         = new LinkedHashMap()

   def testCaseRunResult = new TestCaseResult(
                                name: "",
								time: 0,
								failSign: false,
								resultMessages: new ArrayList(),
								failure: new LinkedHashMap())
   
   for(int r=0; r <= testRunner.getResults().size()-1; r++) {
	 def result = testRunner.getResults().get(r)
	 def testStep = result.getTestStep()
	 
     // go through result messages to search for TC_NAME_PATTERN
     for(message in result.getMessages()) {
	    //log.info "test step result message ${message}"
	    def matcher = ( message =~ TC_NAME_PATTERN )
		if ( matcher ) {
		  //log.info "when pattern is matched -> ${matcher.group(0)}"
		  //log.info "current TC ${currentTC}"
		  
		  if ( testCaseRunResult.name == "" ) {
		     testCaseRunResult.name = matcher.group(1)
			 //log.info "currentTC ${testCaseRunResult.name}"
			 testcasesList.add(testCaseRunResult)
		   }
		  else
		  if ( testCaseRunResult.name != matcher.group(1) ) {
		     //log.info "...when another test case name..."
			 
			 def findSimularTestCase = findResultByTestCaseName(testcasesList, matcher.group(1))
			 //log.info "after a try to find simular test case ${findSimularTestCase}"
			 if ( findSimularTestCase ) {
			    // setup found test case to continue
			    testCaseRunResult = findSimularTestCase
				//log.info "... when found the same test..."
			 }
			 else {
			    //log.info "... when start new test..."
				
				testCaseRunResult = new TestCaseResult(
                                        name: matcher.group(1),
						        		time: 0,
						        		failSign: false,
						        		resultMessages: new ArrayList(),
						        		failure: new LinkedHashMap())
				testcasesList.add(testCaseRunResult)
				
			 }

		    } 
		  
		} 
	  } // for (message .... to search TC_NAME_PATTERN ...
	 
		
	    // sum time amount
        testCaseRunResult.time = testCaseRunResult.time + result.getTimeTaken()		
	  
	    // get test step type (i.e. 'groovy', 'delay', 'properties', 'request', 'restrequest', 'jdbc', etc.)
        def type = testStep.config.type    
	    //log.info "test step type ${type}"	

		// collect specific test step info 
        switch(type){    
	      case 'manualTestStep':              // manual test step -> PAUSE
     	    testCaseRunResult.resultMessages.add("step ${testStep.name} (user do some manual actions)")
		    break
			
          case 'groovy':                      // Script test step -> Before, DataDriver, goto Lopper... , After
		    break
			
          case 'delay':                       // Delay test step -> WAIT
            testCaseRunResult.resultMessages.add("step ${testStep.name} (execution waits some defined in milliseconds time)")			
            break
	
          //case 'properties':               //Properties test step - NOT used in current imlementation
            //break

          //case 'request':                   //SOAP Request/Response test step - NOT used in current implementation
            //break
			
		  case ['restrequest','jdbc']:       // Rest,Jdbc request test step -> GET, PUT, POST, DELETE, OPTIONS, JDBC
		     testCaseRunResult.resultMessages.add("step ${testStep.name}")

             def requestResponse = result?.response
			 
			 if ( requestResponse != null ) {
             def rawRequestData = requestResponse.getRawRequestData()
			   if ( rawRequestData != null ) {
			     def rawReq = new String(rawRequestData,"UTF-8")
			     //log.info "rawReq ${rawReq}"
				 testCaseRunResult.resultMessages.add("REQUEST: " + rawReq)
			   } else {
			     testCaseRunResult.resultMessages.add("NO raw request")
			   }
			   
			  def rawResponseData = requestResponse.getRawResponseData()
			    if ( rawResponseData != null ) {
			      def rawRes = new String(rawResponseData,"UTF-8")
			      //log.info "rawRes ${rawRes}"
				  testCaseRunResult.resultMessages.add("RESPONSE: " + rawRes)
				 } else {
				  testCaseRunResult.resultMessages.add("NO raw response")
				 }
			  } else {
			    testCaseRunResult.resultMessages.add("NO sent request and incoming response at all")
			  }
		
            break		
		    
        }  // switch(type) ...		
		
		// collect result messages
       for(message in result.getMessages()) {
    	 //log.info "test step result message ${message}"
	     def matcher1 = ( message =~ TC_NAME_PATTERN )
	      if ( !matcher1 ) {
		    testCaseRunResult.resultMessages.add(message)
			//log.info "collect result messages ${message}"
		  } 
	    }	// for ...	
		
		// process fails
        if ( result?.getStatus() == TestStepStatus.FAILED ) {
		  //log.info "...when Fail is detected..."
		  if ( testCaseRunResult.failSign == false ) {
		    //log.info "...collect info about fail..."
		     testCaseRunResult.failure.put("type","Failing due to failed test step")
			 testCaseRunResult.failure.put("message","Failing due to failed test step ${testStep.name}")
			 testCaseRunResult.failure.put("info",testCaseRunResult.resultMessages.collect())

		  }
		  testCaseRunResult.failSign = true		  
		}
       
	   // last iteration
	   if ( r == testRunner.getResults().size()-1 ) {
	    //log.info "... last iteration ..."

       tsMap.put("testcases",testcasesList)
	   tsMap.put("name","${projectName}.${suiteName}")
	   tsMap.put("errors","0")	   
	   addSumTestsInfo(testcasesList, tsMap)
	   
	   }   // if ... last iteration ...

	
    }   //  for(result ...
	
	
  //log.info "collected Map Object ${tsMap.toString()}"
  return JsonOutput.toJson(tsMap)
  
  }  // ... generateJson method
  

  /**
  * Get hashCode of json field
  *
  * @return Returns hashCode
  */
  int hashCode() {
      return (this.json != null ? this.json.hashCode() : 0)
  }

  
  /**
  * Get short file name that is suitable for maven surefire plugin
  *
  * @return Returns file name without extension
  */
  String getShortSuiteFileName() {
	// template name for maven-surefire-plugin
	return "TEST-${this.suiteName}"
  }

  
  /**
  * Get full file name
  *
  * @param extension - required extension
  * @return Returns full file name
  */
  String getFullSuiteFileName(String extension){
	  String fullFileName = ""
	
	  switch(extension){
	    case 'JSON':
		  fullFileName = getShortSuiteFileName() + ".json"
		  break
		case 'XML':
		  fullFileName = getShortSuiteFileName() + ".xml"
		  break
		default:
		  fullFileName = getShortSuiteFileName() + ".txt"
		  break
	  }
	  
	  //log.info "${fullFileName}"
	  return fullFileName
	}

	
   static void createJsonSuiteReportFile () {
     
   }


   /**
   * Generates XML from provided json Object that is suitable for maven surefire plugin
   *
   * @param json - created json Object
   */   
   static String generateXmlSuiteReport (Object json) {
   
     String infoString = ""
     def j = new JsonSlurper().parseText(json)
	 def sw = new StringWriter()
	 def mkb = new MarkupBuilder(sw)
	 mkb.setDoubleQuotes(true)
	 
	 mkb.testsuite(name: j.name,tests: j.tests,failures: j.failures,errors: j.errors,time: j.time) {
		   properties {}
		   j.testcases.each { t ->
		     if( t.failSign ) {
			   testcase(name: t.name,time: t.time) {
			     failure (type: t.failure.type,message: t.failure.message) { 
				    t.failure.info.each { i ->
					  infoString = "<p>" + i.value + "</p>"
					}
				    mkp.yieldUnescaped "<![CDATA[$infoString]]>" 
				 }
			   }
			 }
			 else {
			   testcase(name:t.name,time:t.time)
			 }

		   }
		   
		 }
     
	 return sw.toString()
   
     //log.info "${sw}"
   } // generateXmlSuiteReport ...

   
  /**
  * Creates XML report file for Suite level that is suitable for maven surefire plugin
  * puts the file to folder that is defined in project/properties parameter REPORT_FOLDER_PROPERTY
  *
  */  
  void createXmlSuiteReportFile () {
    def xmlInfo = '<?xml version="1.0" encoding="UTF-8"?>' + generateXmlSuiteReport(this.json)
	
    def reportFileName = getFullSuiteFileName('XML')
    def reportXmlFile = new File(this.reportPath,reportFileName)
	//log.info "${reportXmlFile.absolutePath}"
	
	if ( !reportXmlFile.exists() ) {
	  reportXmlFile.createNewFile()
	}
	
	def fw = new FileWriter(reportXmlFile)
	fw.write(xmlInfo)
	fw.flush()
	fw.close()
	
	//log.info "file ${reportXmlFile.absolutePath}  is created"
  }
   
}