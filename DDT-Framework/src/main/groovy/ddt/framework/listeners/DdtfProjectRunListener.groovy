package ddt.framework.listeners

import com.eviware.soapui.model.support.ProjectRunListenerAdapter
import com.eviware.soapui.model.testsuite.ProjectRunner
import com.eviware.soapui.model.testsuite.ProjectRunContext
import com.eviware.soapui.model.testsuite.TestSuiteRunner
import com.eviware.soapui.model.testsuite.TestSuite
import com.eviware.soapui.model.testsuite.TestSuite.TestSuiteRunType
import ddt.framework.TestCaseUtils
import groovy.util.logging.Log4j
import org.apache.log4j.Logger

@Log4j
class DdtfProjectRunListener extends ProjectRunListenerAdapter {
  
  protected final Logger scriptLogger = Logger.getLogger("groovy.log")

  public void beforeRun(ProjectRunner projectRunner, ProjectRunContext runContext) {
	//scriptLogger.info "prj listener - beforeRun method"

    def globalSuiteThreadPoolSize = com.eviware.soapui.SoapUI.globalProperties.getPropertyValue("suitethreadpoolsize")
	//scriptLogger.info globalSuiteThreadPoolSize
	if( globalSuiteThreadPoolSize!= null ) {
	  runContext.getProject().getTestSuiteList().each { suite ->
	    //reset runType to PARALLEL for all Suites in current project
	    suite.setRunType(TestSuiteRunType.PARALLEL)
	   } 
	 } else {
	  runContext.getProject().getTestSuiteList().each { suite ->
	    //reset runType to SEQUENTIAL for all Suites in current project
	    suite.setRunType(TestSuiteRunType.SEQUENTIAL)
	   }
	 }

  }
  
  public void afterRun(ProjectRunner projectRunner, ProjectRunContext runContext) {
    //scriptLogger.info "prj listener - afterRun method"

  }

  public void beforeTestSuite(ProjectRunner projectRunner, ProjectRunContext runContext, TestSuite testRunnable) {
    //scriptLogger.info "prj listener - beforeTestSuite method"
	//scriptLogger.info testRunnable.getRunType()

  }
  
  public void afterTestSuite(ProjectRunner projectRunner, ProjectRunContext runContext, TestSuiteRunner testRunner) {
    //scriptLogger.info "prj listener - afterTestSuite method"
  }
  
 
}