package ddt.framework

import com.eviware.soapui.impl.wsdl.WsdlProject
import com.eviware.soapui.model.support.PropertiesMap
import com.eviware.soapui.model.testsuite.TestSuite
import com.eviware.soapui.model.testsuite.TestCase
import com.eviware.soapui.model.testsuite.TestRunner
import com.eviware.soapui.tools.SoapUITestCaseRunner
import com.eviware.soapui.model.testsuite.TestRunner.Status

/**
 * Class with basic methods to execute SoapUI project. It is used for testing DDT Framework library.
 */
class SoapUIUtils {

    /**
     * Run SoapUI project.
     *
     * @param path - Full path to XML file with SoapUIProject
     * @return Returns true if run was successfully finished
     */
    static Boolean testSoapUIRunner(String path) {
        SoapUITestCaseRunner runner = new SoapUITestCaseRunner()
        runner.setProjectFile(path)
        runner.setPrintReport(true)
        println "JUnitReport = ${runner.isJUnitReport()}"
        println "PrintReport = ${runner.isPrintReport()}"
        try {
            return runner.run()
        } catch (Exception e){
            return false
        }
    }

    /**
     * Run specific Test Case from Test Suite of SoapUI project.
     *
     * @param projectPath - Full path to XML file with SoapUIProject
     * @param testSuiteName - Name of the Test Suite from SoapUI project
     * @param testCaseName - Name of the Test Case from Test Suite of SoapUI project
     * @return Returns true if run was successfully finished
     * @throws Exception
     */
    static Boolean testTestCaseRunner(String projectPath, String testSuiteName, String testCaseName) throws Exception
    {
        WsdlProject project = new WsdlProject( projectPath )
        TestSuite testSuite = project.getTestSuiteByName( testSuiteName )
        TestCase testCase = testSuite.getTestCaseByName( testCaseName )
            try {
            // create empty properties and run synchronously
            TestRunner runner = testCase.run( new PropertiesMap(), false )

            return Status.FINISHED == runner.getStatus()
        } catch (Exception e){
                return false
        }
    }
}
