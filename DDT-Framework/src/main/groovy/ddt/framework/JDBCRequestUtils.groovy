package ddt.framework

import com.eviware.soapui.impl.wsdl.teststeps.JdbcRequestTestStep
import com.eviware.soapui.impl.wsdl.teststeps.assertions.basic.GroovyScriptAssertion
import com.eviware.soapui.model.testsuite.TestCase
import com.mysql.jdbc.Driver
import ddt.framework.model.Data
import ddt.framework.model.Parameter
import groovy.util.logging.Log4j

/**
 * Created by Aleksei_Galkin on 3/1/2016.
 */
@Log4j
class JDBCRequestUtils {

    /**
     * Set up JDBC Request parameters from test data:
     *  - Set driver
     *  - Set Connection string
     *  - Set query string
     *  - Set media type
	 *
	 * Set Up/Enable assertions
     *
     * @param data - Data object with test data for test step execution
     * @param testCase - TestCase object which contains test case for execution
	 * @param previousResponse - expected response of previously executed test step
	 * @param previousExpected - actual response from previously executed test step
     */
    static void setTestStepConfiguration(Data data, TestCase testCase, String previousResponse, String previousExpected) {
        // Select TestStep
        def testStep = testCase?.getTestStepByName(data?.method) as JdbcRequestTestStep

        // Set JDBC Driver
        testStep.setDriver(Driver.name)

        setConnectionString(data, testCase)

        // Add SQL Query
        def sqlQuery = getSqlQueryFromData(data, previousResponse, previousExpected) ?: ""
        testStep?.setQuery(sqlQuery)
		
		// Enable/Set Assertions
		enableStepScriptAssertions ([AssertionUtils.RESPONSE_ASSERTION_NAME], data, testCase)		
		
    }

	
    /**
     * Set Connection string for JDBC Request from test data.
     *
     * @param data - Data object with test data for test step execution
     * @param testCase - TestCase object which contains test case for execution.
     */	
    private static void setConnectionString(Data data, TestCase testCase) {

        def testStep = testCase?.getTestStepByName(data?.method) as JdbcRequestTestStep

        def connectionString = data?.endpoint ?: testCase?.getPropertyValue(TestCaseUtils.ENDPOINT_PROPERTY)

        // Set resource path
        log.info "resourcePath = ${data?.resourcePath}"
        connectionString += data?.resourcePath ?: ""

        // Add request parameters
        connectionString = setConnectionParameters(data?.parameters, connectionString)

        log.info "connectionString = $connectionString"
        testStep?.setConnectionString(connectionString)
    }

    /**
     * Set Connection parameters for JDBC Request from test data.
     *
     * @param parameters - parameters from Data object
     * @param connectionString - current JDBC test step connection string
	 * @return Returns JDBC connection string with added parameters
     */	
    static private String setConnectionParameters(Parameter[] parameters, String connectionString) {
        def connectionPath = connectionString.split(/[?]/)[0]
        def connectionParameters = connectionString.split(/[?]/).length > 1 ? connectionString.split(/[?]/)[1] : ""

        log.info "Parameters:"
        parameters?.each { parameter ->
            if (parameter) {
                def parameterName = parameter?.name
                def parameterValue = parameter?.value
                def evalScript = parameter?.evalScript

                log.info "name = ${parameterName} ; value = ${parameterValue} ; evalScript = ${evalScript}"

                // Evaluate parameter value if script present
                if (evalScript)
                    try {
                        parameterValue = Eval.x(parameterValue, evalScript)
                    }
                    catch (Exception e) {
                        log.error "Eval script could not be evaluated for Parameter $parameterName: $evalScript"
                        log.error "message: $e"
                    }

                if (!parameterName?.startsWith("{")) {
                    // Process parameter as QUERY
                    connectionParameters += connectionParameters ? "&$parameterName=${parameterValue as String}" : "$parameterName=${parameterValue as String}"
                } else {
                    // Process parameter as PATH
                    connectionPath += "/" + connectionPath
                }
            }
        }
        return connectionParameters ? "$connectionPath?$connectionParameters" : "$connectionPath"
    }

    /**
     * Get SQL query from test data.
     *
     * @param data - Data object with test data for test step execution
	 * @param previousResponse - expected response of previously executed test step
	 * @param previousExpected - actual response from previously executed test step
	 * @return Returns evaluated SQL query string
     */	
    static private String getSqlQueryFromData(Data data, String previousResponse, String previousExpected) {
        def sqlQuery = data?.requestBody?.body
        log.info "requestBody.evalScript = ${data?.requestBody?.evalScript}"
        def evalScript = data?.requestBody?.evalScript

        // Process request content and eval() it if needed
        if (sqlQuery?.toUpperCase() == TestCaseUtils.ANNOTATION_LAST_RESPONSE)
            sqlQuery = previousResponse

        if (sqlQuery?.toUpperCase() == TestCaseUtils.ANNOTATION_LAST_EXPECTED)
            sqlQuery = previousExpected

        // Evaluate body string if script present
        if (evalScript)
            try {
                sqlQuery = Eval.x(sqlQuery, evalScript)
            }
            catch (Exception e) {
                log.error "Eval script could not be evaluated for SQL Query: $evalScript"
                log.error "message: $e"
            }

        log.info "requestBody = $sqlQuery"

        return sqlQuery
    }

    /**
   	 * Enable or/and set Required Assertions
	 *
	 * @param assertions - list of required assertions, for example ["HTTP Status Assertion","Content-Type Assertion",... ...]
	 * @param data - Test Data with test step name
	 * @param testCase - Test Case with list of Test Steps
	 */
	static void enableStepScriptAssertions (ArrayList assertionList, Data data, TestCase testCase) {

	    // Select TestStep
        def testStep = testCase?.getTestStepByName(data?.method) as JdbcRequestTestStep

		// disable step assertions if they are not in the array list
        testStep.getAssertionList().each { stepAssertion ->
		        stepAssertion.disabled = true
		}		
		
		for (assertItem in assertionList) {
		
		    def assertion = testStep?.getAssertionByName(assertItem) as GroovyScriptAssertion

			// when assertion exists then enable it
            if ( assertion ) {
			    assertion.disabled = false
			}
			else {
			    // try to get script assertion text
			    def scriptText = AssertionUtils?.getScriptAssertionText(assertItem)?.trim()
				
				// add an assertion to step in case script text is available
				if ( scriptText != "" ) {
				    try {
				        def newAssertion = testStep.addAssertion("Script Assertion") as GroovyScriptAssertion
						newAssertion.name = assertItem
					    newAssertion.setScriptText(scriptText)
					} catch (Exception e) {
					    log.error "Unable to add new script Assertion: ${assertItem}"
                        log.error "message: $e"
					}

				}
				else
				    log.error "Could not set assertion ${assertItem} for step ${data.description}"

			}
			
		}
		
	}

}


