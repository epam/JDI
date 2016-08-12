package ddt.framework

import com.eviware.soapui.SoapUI
import com.eviware.soapui.model.testsuite.TestCase
import com.eviware.soapui.model.testsuite.TestCaseRunner
import com.eviware.soapui.model.testsuite.TestCaseRunContext
import com.eviware.soapui.support.GroovyUtils
import ddt.framework.model.Data
import groovy.util.logging.Log4j
import org.apache.log4j.Level
import org.apache.log4j.Logger
import java.util.regex.Pattern

@Log4j
/**
 * Utils used in SoapUI test cases.
 */
class TestCaseUtils {

    final static ANNOTATION_FIND = "@FIND" // (Deprecated) Annotation for AEM and PnA search, use method FIND instead
    final static ANNOTATION_LAST_RESPONSE = "@LASTRESPONSE" // Annotation for response from previous REST request
    final static ANNOTATION_LAST_EXPECTED = "@LASTEXPECTED" // Annotation for EXPECTED response from previous test step
    final private static HTTP_METHODS = ["GET", "POST", "DELETE", "PUT", "OPTIONS"]    // List of REST methods for tests

    final static String PROPERTY_FILE = "testProps.properties"  // Name of configuration file with properties

    // SoapUI properties
//    private static Properties PROPERTIES

    // Project properties
    final static String ENDPOINT_PROPERTY = "endPoint"  // Property with endpoint for REST requests
    final static String TEST_DATA_FOLDER_PROPERTY = "dataPath"  // Property with path to test data file
	final static String REPORT_FOLDER_PROPERTY = "reportPath"
    // Property with full path to the variables map file
    final static String DATA_MAPPING_FILE_PROPERTY = "dataMappingFile"
	
    // Cookie property
    final static String COOKIE_PROPERTY = "cookie"

    // Test Suite properties
    final static String DATA_FILE_NAME_PROPERTY = "dataFile"    // Property with test data file name
	
	// Test Case Naming Pattern
	final static String TEST_CASE_NAMING_PATTERN_PROPERTY = "namingPattern"
	
    // Test Case properties
    final static String EXCEL_FILE_PROPERTY = "xlsFile" // Property with full path to test data file
    final static String CURRENT_ROW_PROPERTY = "currentRow" // Property with current row number from data file
    final static String COUNT_ROWS_PROPERTY = "countRows"  // Property with rows count number from data file
    final static String DESCRIPTION_PROPERTY = "desc"   // Property with test step description
    final static String EXPECTED_RESPONSE_PROPERTY = "resp" // Property with expected response
    // Property with evaluation script for expected response
    final static String EVALUATE_RESPONSE_SCRIPT_PROPERTY = "evalResponse"
    final static String STATUS_PROPERTY = "status"  // Property with expected HTTP status of response
    final static String CONTENT_TYPE_PROPERTY = "contentType"   // Property with expected content-type of response
    // Property with map for expected assertion results
    final static String EXPECTED_ASSERTION_RESULTS_PROPERTY = "expectedAssertionResults"
    final static String RESOURCE_PATH_PROPERTY = "resPath" // Property with resource path for REST request
    final static String CURRENT_RESPONSE_PROPERTY = "currentResponse"   // Property with actual response
    final static String CURRENT_STATUS_PROPERTY = "currentStatus"   // Property with actual HTTP status of response

    // SoapUI Logger
    private static logger
	


    /**
     * Get all project properties from SoapUI project.
     *
     * @param context - Test Case Run Context which contains test case for execution
     * @return Return Properties object with project properties from SoapUI project.
     */
    static Properties getPropertiesFromSoapUIProject(TestCaseRunContext context) {
        def testCase = context.testCase
        def properties = new Properties()
        testCase.testSuite.project.getPropertyList().each { property ->
            properties.setProperty(property.name, property.value)
        }
        return properties
    }

    /**
     * Load properties from the PROPERTY_FILE file from the project directory. Use properties from SoapUI project as default properties
     *
     * @param context - Test Case Run Context which contains test case for execution
     * @return Return Properties from SoapUI project as default properties and "projectPath/PROPERTY_FILE".
     */
    static Properties loadProperties(TestCaseRunContext context) {
        //Get project path
        def groovyUtils = new GroovyUtils(context)
        def projectPath = groovyUtils.projectPath

        log.info "Project path = ${projectPath}"
		
        // Use SoapUI Project properties as defaults
        def defaultsProperties = getPropertiesFromSoapUIProject(context)
        def properties = new Properties(defaultsProperties)

        //Get properties from file if exist
        def propertiesFile = new File(projectPath, PROPERTY_FILE)

        if (propertiesFile.exists()) {
            log.info "Property file = ${propertiesFile.absolutePath}"
            propertiesFile.withInputStream {
                properties.load(it)
            }
        } else
            log.info "Property file does not exist"

        return properties
    }

    /**
     * Load variable values from map file.
     *
     * @param mapFilePath - full path to Excel file with variable values
     * @return Return Map with variable names and values.
     */
    static Map<String, String> loadDataVariableMapping(String mapFilePath) {
        log.info "Load Variable Mapping from File: ${mapFilePath}"
        def variablesMap = new VariablesMapping(mapFilePath).variablesMap
        return variablesMap
    }


    /**
	 * Clear test case properties
	 *
	 * @param testCase - test case object
	 */
	static private clearTestCaseProperties (TestCase testCase) {
	
	    // clear all test case properties firstly
		testCase.getPropertyList().each { property ->
		    testCase.removeProperty(property.name)
		}
		
		// set only 'main' properties, all others will be set during test case execution
	    testCase.setPropertyValue(EXCEL_FILE_PROPERTY, "")
		testCase.setPropertyValue(COUNT_ROWS_PROPERTY, "0")
		testCase.setPropertyValue(CURRENT_ROW_PROPERTY, "0")		
	    testCase.setPropertyValue(DATA_MAPPING_FILE_PROPERTY, "")
		testCase.setPropertyValue(REPORT_FOLDER_PROPERTY, "")
		
	} 
	
    /**
     * Finish test case
	 *
	 * @param context - test case run context object
	 * @param testRunner - test case runner object
     */
    static void finishTestCase(TestCaseRunContext context, TestCaseRunner testRunner) {
	
	  //REPORT
	  if ( context?.commonProperties?.getProperty(REPORT_FOLDER_PROPERTY) ) {
	    def report = new ReportUtils(context.testCase.testSuite.project.name, 
		                             context.testCase.testSuite.name, 
									 context.commonProperties.getProperty(REPORT_FOLDER_PROPERTY),
									 testRunner)
		//log.info "report JSON ${report.getJson()}"
		
		// create XML report suitable for maven surefire report plugin by default
		report.createXmlSuiteReportFile()
	    }

	
	  // CLEAR user objects from context
	  context.currentTestData = null
	  context.variablesMapping = null
	  context.commonProperties = null
	  
	} 	 
	
    /**
     * Prepare test case before test execution:
     *  - Load properties;
     *  - Get map file with variables from DATA_MAPPING_FILE_PROPERTY;
     *  - Set up endpoint for test case;
     *  - Get test data file name for test suite execution and set up full path to data file for test case EXCEL_FILE_PROPERTY.
     *
     * @param context - Test Case Run Context which contains test case for execution.
     */
    static void prepareTestCase(TestCaseRunContext context) {
	  
	  try {

        // Init "ddt framework" log tab in SoapUI
        def logMonitor = SoapUI.getLogMonitor()
        if (logMonitor && !logMonitor?.hasLogArea("ddt.framework"))
            logMonitor.addLogArea("DDTF log", "ddt.framework", false).setLevel(Level.INFO)

        logger = Logger.getLogger("ddt.framework")


        //Delete downloaded files from previous run
        clearDownloads(context)		
		
		// Get properties and keep them in context
		context.commonProperties = new Properties(loadProperties(context))
		
        def testCase = context.testCase
		
		// clear properties values of test case
		clearTestCaseProperties(testCase)
		
	
        // Get Map file from properties
        def mapFile = context?.expand(context?.commonProperties?.getProperty(DATA_MAPPING_FILE_PROPERTY))
		if ( mapFile )
		  context.commonProperties.setProperty(DATA_MAPPING_FILE_PROPERTY, mapFile)
        log.info "dataMappingFile = ${mapFile}"

        // Save Map file  to test case properties (just for visibility at the moment)
        testCase.setPropertyValue(DATA_MAPPING_FILE_PROPERTY, mapFile)
		
		
		// Keep mapping in context
		context.variablesMapping = new VariablesMapping(mapFile)		

		// Optional in Project|testProps properties, can be kept in map-file
        // Get endPoint from properties
        def endPoint = context?.commonProperties?.getProperty(ENDPOINT_PROPERTY)
        log.info "endPoint = ${endPoint}"
        // Save endpoint to test case properties
        testCase.setPropertyValue(ENDPOINT_PROPERTY, endPoint)

		// Optional
        // Cookie property
        def cookie = context?.commonProperties?.getProperty(COOKIE_PROPERTY)
        log.info "Cookie = ${cookie}"
        testCase.setPropertyValue(COOKIE_PROPERTY, cookie)

        // Get test data file name
        def dataFile = testCase.testSuite.getPropertyValue(DATA_FILE_NAME_PROPERTY)

        //Get folder with test data
        def dataFolder = context.expand(context?.commonProperties?.getProperty(TEST_DATA_FOLDER_PROPERTY))
		if ( dataFolder )
		  context.commonProperties.setProperty(TEST_DATA_FOLDER_PROPERTY, dataFolder)
        if (dataFolder && dataFolder[dataFolder.length() - 1] != "\\")
            dataFolder = dataFolder + "\\"

        def dataFilePath = dataFolder + dataFile

        log.info "xlsFile = ${dataFilePath}"
        testCase.setPropertyValue(EXCEL_FILE_PROPERTY, dataFilePath)
		
		// Keep TestData in context
		context.currentTestData = new TestData(dataFilePath)
		// Set COUNT_ROWS_PROPERTY to test case properties
        testCase.setPropertyValue(COUNT_ROWS_PROPERTY, context.currentTestData.countRows.toString())
		
		//Get folder for reports
		def reportFolder = context.expand(context?.commonProperties?.getProperty(REPORT_FOLDER_PROPERTY))
		if ( reportFolder )
		  context.commonProperties.setProperty(REPORT_FOLDER_PROPERTY, reportFolder)
		log.info "report folder = ${reportFolder}"
		testCase.setPropertyValue(REPORT_FOLDER_PROPERTY, reportFolder)
		
	  }
      catch (Exception e) {
		  log.info e.toString()
		  log.error e.getMessage()
		  log.error e.getStackTrace()
	  }
				
    }

    /**
     * Iterate over test data file, set up test step parameters, resolves variables in test data and navigate to appropriate test step for execution.
     *  - Get test data from context and set up CURRENT_ROW_PROPERTY for test case;
     *  - Get data from CURRENT_ROW_PROPERTY row in EXCEL_FILE_PROPERTY file;
     *  - Resolve property references in data;
     *  - Resolve variable references in data;
     *  - Set up properties for test case execution;
     *  - Set test step configuration from data; and
     *  - Give execution control to the appropriate test step.
     *
     * @param context - TestCase Context object which contains test case, test data and variables from map-file
     * @param testRunner - TestCaseRunner object with test case running context.
     */
    static void dataDriver(TestCaseRunContext context, TestCaseRunner testRunner) {

	    // get objects from context
	    def testCase = (TestCase)context.testCase
		def testData = (TestData)context.currentTestData
		def variablesMapping = (VariablesMapping)context.variablesMapping
		def properties = (Properties)context.commonProperties

        testData.setCurrentRow(testCase.getPropertyValue(CURRENT_ROW_PROPERTY).toInteger())
		
		// Skip commented lines
		def gotoRow = testData.getNextRow()
		//log.info "${gotoRow} == ${testData.currentRow.toString()}"
		
		log.info "Process line: " + testData.currentRow.toString()

        // Set new value to testCase CURRENT_ROW_PROPERTY 
        testCase.setPropertyValue(CURRENT_ROW_PROPERTY, testData.currentRow.toString())

			//
			// ??? Handle on data object can be kept in context ???
			// context.currentDataObject = testData.getData()
			//
		
		// Parse test data
		Data data = testData.getData()

        if ( data != null ) {

            log.info "description = ${data.description}"

            // Resolve references to properties from config file and from SoapUI project in test data
            TestData.substitutePropertiesInRequest(data, properties)
		    
			def evalUtils = new EvalUtils()
			evalUtils.VARIABLES = variablesMapping.variablesMap			
			evalUtils.PRODUCT_VARIABLES = variablesMapping.productsMap			
			
            // Resolve variables in test data
            TestData.substituteVariablesInRequest(data, evalUtils.VARIABLES)

            // Resolve references to previously found products
            TestData.substitutesProductInData(data, evalUtils.PRODUCT_VARIABLES)
	
            // Get resource method & path
            def method = data.method
            log.info "method = ${method}"

            def resourcePath = data.resourcePath
            testCase.setPropertyValue(RESOURCE_PATH_PROPERTY, resourcePath)

            // Set expected results - response and status
            log.info "status = ${data.status}"
            testCase.setPropertyValue(STATUS_PROPERTY, data.status)

            log.info "contentType = ${data.contentType}"
            testCase.setPropertyValue(CONTENT_TYPE_PROPERTY, data.contentType)

            log.info "expectedAssertionResults = ${data.expectedAssertionResults}"
            testCase.setPropertyValue(EXPECTED_ASSERTION_RESULTS_PROPERTY, data.expectedAssertionResults)

            // Process response
            def previousExpectedResponse = getExpectedResponseFromTestCase(testCase)
            def previousActualResponse = getActualResponseFromTestCase(testCase)
	
            // Set request parameters for HTTP methods

            switch (method) {
			
                case HTTP_METHODS:
                    RestRequestUtils.setTestStepConfiguration(data, testCase, previousExpectedResponse, previousActualResponse)
                    break
					
                case "JDBC":
                    JDBCRequestUtils.setTestStepConfiguration(data, testCase, previousExpectedResponse, previousActualResponse)
                    break
				
				case "FIND":
				    FindRequestUtils.processFind(data, testCase, previousExpectedResponse, previousActualResponse)
				    break
				
                case "PAUSE":
                    testRunner.getTestCase().getTestStepByName(method).setPropertyValue("ExpectedResult", resourcePath)
                    break
					
				case "WAIT":
				    DelayUtils.setTestStepConfiguration(data, testCase)
					break
            }

            // Resolve references to properties from config file and from SoapUI project in test data
            TestData.substitutePropertiesInResponse(data, properties)
            TestData.substituteVariablesInResponse(data, evalUtils.VARIABLES)
			TestData.substituteVariablesInResponse(data, evalUtils.PRODUCT_VARIABLES)

            def expectedResponse = getExpectedResponseFromData(data, previousActualResponse, previousExpectedResponse)
            testCase.setPropertyValue(EXPECTED_RESPONSE_PROPERTY, expectedResponse)

            log.info "expectedResponse.evalScript = ${data.expectedResponse.evalScript}"
            testCase.setPropertyValue(EVALUATE_RESPONSE_SCRIPT_PROPERTY, data.expectedResponse.evalScript)

			def testName = "[TC_${testCase.name}]"
		
			def namingPattern = properties?.getProperty(TEST_CASE_NAMING_PATTERN_PROPERTY)
			if ( namingPattern != null )
			  testName = findTestCaseName (data.description,namingPattern,testCase.name)
			
            // Log description for this iteration
            def description = "${testName} Step: ${data.description}  @: ${method} ${resourcePath}".trim()
            testCase.setPropertyValue(DESCRIPTION_PROPERTY, description);
            log.info description
			
			// NOTE: data.method can be reassigned to point to test step template requests that should be executed in reality
			//       so data.method is used to choose next step for execution, variable 'method' is used for logging purposes
            if ( data.method == "SKIP") {
                log.info "Test Step Skipped"
                testRunner.gotoStepByName("Looper")
            } else if (testCase.getTestStepByName(data.method))
                testRunner.gotoStepByName(data.method)
            else {
                log.error "Test Step ${data.method} (named in DATA file as ${method}) does not exist"
                testRunner.gotoStepByName("Looper")
            }

        } 
	
		else {
            testCase.setPropertyValue(CURRENT_ROW_PROPERTY, testData.countRows.toString())
            testRunner.gotoStepByName("Looper")
        }

		
    }

    /**
     * (Deprecated) Implement wait test step. Execute sleep method with parameter stored in RESOURCE_PATH_PROPERTY.
     *
     * @param testCase - TestCase object which contains test case for execution
     */
    static void wait(TestCase testCase) {
        log.info "Wait: " + testCase.getPropertyValue(RESOURCE_PATH_PROPERTY)
        sleep(testCase?.getPropertyValue(RESOURCE_PATH_PROPERTY)?.toInteger())
    }
	
    /**
     * Increase current row counter and navigate to the "data driver" test step for the next iteration.
     *
     * @param testCase - TestCase object which contains test case for execution
     * @param testRunner - TestCaseRunner object with test case running context.
     */
    static void looper(TestCase testCase, TestCaseRunner testRunner, String gotoStep) {
        def currentRow = testCase?.getPropertyValue(CURRENT_ROW_PROPERTY)?.toInteger()

        ++currentRow

        if (currentRow >= testCase?.getPropertyValue(COUNT_ROWS_PROPERTY)?.toInteger()) 
            return
			
        testCase?.setPropertyValue(CURRENT_ROW_PROPERTY, currentRow.toString())

        if (testCase?.getTestStepByName(gotoStep))
            testRunner?.gotoStepByName(gotoStep)
        else
            log.error "Test Step ${gotoStep} does not exist"
    }

    static private String getActualResponseFromTestCase(TestCase testCase) {
        return testCase.getPropertyValue(CURRENT_RESPONSE_PROPERTY)
    }

    static private String getExpectedResponseFromTestCase(TestCase testCase) {
//        return testCase.getPropertyValue(EXPECTED_RESPONSE_PROPERTY)
          return testCase?.getPropertyValue(EXPECTED_RESPONSE_PROPERTY) ?: ""
    }

    /**
     * Process expected response from test data:
     *   - If expected response contains ANNOTATION_LAST_RESPONSE then use previousResponse as expected response.
     *
     * @param data - Data object with test data for test step execution
     * @param previousResponse - Response from previous REST Request.
     * @return Return processed expected response string.
     */
    static private String getExpectedResponseFromData(Data data, String previousResponse, String previousExpected) {
        // Process response
        def expectedResponse = resolveAnnotationsLast(data?.expectedResponse?.body, previousResponse, previousExpected)

        log.info "expectedResponse = ${expectedResponse}"

        return expectedResponse
    }

    static public String resolveAnnotationsLast(String data, String previousResponse, String previousExpected) {
        if (data?.toUpperCase() == ANNOTATION_LAST_RESPONSE)
            return previousResponse

        if (data?.toUpperCase() == ANNOTATION_LAST_EXPECTED)
            return previousExpected

        return data
    }

    final static private DOWNLOADS_FOLDER = "downloads"

    static String getDownloadFolder(TestCaseRunContext context) {
        def projectPath = new GroovyUtils(context).projectPath
        def dataName = context?.testCase?.testSuite?.getPropertyValue(DATA_FILE_NAME_PROPERTY)?.split("\\.")?.first()

        return "$projectPath/$DOWNLOADS_FOLDER/$dataName"
    }

    static void clearDownloads(TestCaseRunContext context) {
        log.info "Delete all Downloaded Files"

        def downloadFilePath = getDownloadFolder(context)
        def downloadFolder = new File(downloadFilePath)

        if (downloadFolder.exists())
            downloadFolder.listFiles().each { downloads ->
                downloads.delete()
            }
    }
	
    /**
     * Process description from test data file:
     *   - If description contains pattern then return new test name
     *
     * @param description - current Description from data-file cell
     * @param pattern     - pattern from property, should contain one group as minimum, 
     * @return              name of Test Case for following usage
     */	
	static String findTestCaseName (String description, String pattern, String defaultValue) {
	  // default return value   
	  def findResult = "[TC_" + defaultValue + "]"
	  
	  def matcher = (description =~ pattern)
	  if ( matcher ) {
	    if (  matcher.groupCount() == 0 )
		  log.warn "Pattern '${pattern}' does NOT contain any group. Default value ${findResult} will be set" 
	    else
	    if ( matcher.groupCount() == 1 )
	      findResult = "[TC_" + matcher.group(1) + "]"
		else {
		  // here we think that there are 2 or more groups in defined pattern
		  // only two group will be excluded and set as test case name
		  findResult = "[TC_" + matcher.group(1) + "_" + matcher.group(2) + "]"
		}
	  } 
	  else {
	    log.warn "Could not find any entry '${pattern}' in '${description}'. Default value ${findResult} will be set" 
	  }
	  
	  return findResult
	}
	

}