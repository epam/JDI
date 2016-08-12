package ddt.framework.listeners

import com.eviware.soapui.impl.wsdl.teststeps.RestRequestStepResult
import com.eviware.soapui.impl.wsdl.teststeps.JdbcTestStepResult
import com.eviware.soapui.impl.wsdl.teststeps.WsdlTestStepResult
import com.eviware.soapui.impl.wsdl.teststeps.ManualTestStepResult
import com.eviware.soapui.model.support.TestRunListenerAdapter
import com.eviware.soapui.model.testsuite.TestCaseRunContext
import com.eviware.soapui.model.testsuite.TestCaseRunner
import com.eviware.soapui.model.testsuite.TestStep
import com.eviware.soapui.model.testsuite.TestStepResult
import com.eviware.soapui.model.testsuite.TestStepResult.TestStepStatus
import ddt.framework.TestCaseUtils
import groovy.util.logging.Log4j
import org.apache.log4j.Logger

@Log4j
class DdtfTestRunLogListener extends TestRunListenerAdapter {

    protected final Logger scriptLogger = Logger.getLogger("groovy.log")

    private long startTime;

    public void beforeStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStep testStep) {
        //scriptLogger.info "start ${testStep.name}, type ${testStep.getClass()}"
    }

    public void beforeRun(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        //startTime = System.nanoTime();
    }

    public void afterStep(TestCaseRunner testRunner, TestCaseRunContext runContext, TestStepResult result) {
        //scriptLogger.info "end ${result.testStep.name}, type ${result.getClass()}"
        if ( result instanceof RestRequestStepResult || 
		     result instanceof JdbcTestStepResult    ||
             result instanceof ManualTestStepResult  ||			 
			 ( result instanceof WsdlTestStepResult && result.testStep.name == 'WAIT' )  ) {
            def stepDescription = runContext.testCase.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)
			def stepStatus = result.getStatus()
			def stepTimeTaken = result.getTimeTaken()

			result.addMessage(stepDescription + " - [${stepStatus}], time taken = " + stepTimeTaken.toString())
			    
        }
    }

    public void afterRun(TestCaseRunner testRunner, TestCaseRunContext runContext) {
        //long endTime = System.nanoTime();
        //scriptLogger.info "TestCase [${testRunner.getTestCase().getName()}] took ${(endTime - startTime)} nanoseconds."

	/*******	
		scriptLogger.info "TestCase [${runContext.testCase.name}]"
		testRunner.getResults().each { result ->
		  result.getMessages().each { message ->
		    scriptLogger.info "${result.getStatus()} ${message}"
		  }
		  
		}
	******/
	
    }
}
