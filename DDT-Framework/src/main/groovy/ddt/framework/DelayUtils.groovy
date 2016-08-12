package ddt.framework


import com.eviware.soapui.impl.wsdl.teststeps.WsdlDelayTestStep
import com.eviware.soapui.model.testsuite.TestCase
import ddt.framework.model.Data
import groovy.util.logging.Log4j

@Log4j
class DelayUtils {

	static void setTestStepConfiguration(Data data, TestCase testCase) {
	  // Select TestStep
	  def testStep = testCase?.getTestStepByName(data?.method) as WsdlDelayTestStep
	  
	  log.info "delay = ${data?.resourcePath}"
	  testStep?.setDelayString(data?.resourcePath)
	}
	
	
}