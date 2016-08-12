package ddt.framework

import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep
import com.eviware.soapui.impl.wsdl.teststeps.assertions.basic.GroovyScriptAssertion
import com.eviware.soapui.model.testsuite.TestCase
import com.eviware.soapui.support.types.StringToStringMap
import ddt.framework.model.Data
import ddt.framework.model.Parameter
import groovy.util.logging.Log4j

/**
 * Utils to configure REST Test Step
 */
@Log4j
class RestRequestUtils {

    /**
     * Set up REST Request parameters from test data:
     *  - Set endpoint
     *  - Set Headers
     *  - Set resource path
     *  - Set media type
     *  - Set request content 
     *  - Set parameters
	 *
	 *  Set Up/Enable assertions
     *
     * @param data - Data object with test data for test step execution
     * @param testCase - TestCase object which contains test case for execution
	 * @param previousResponse - expected response of previously executed test step
	 * @param previousExpected - actual response from previously executed test step
     */
    static void setTestStepConfiguration(Data data, TestCase testCase, String previousResponse, String previousExpected) {
        // Select TestStep
        def testStep = testCase?.getTestStepByName(data?.method) as RestTestRequestStep

        setEndpoint(data, testCase)

        // Set request headers
        setHeaders(data, testCase, previousResponse, previousExpected)

        // Set resource path
        log.info "resourcePath = ${data?.resourcePath}"
        testStep?.resource?.setPath(data?.resourcePath)

        // Set request media type
        log.info "mediaType = ${data?.mediaType}"
        testStep?.testRequest?.setMediaType(data?.mediaType)

        // Set PostQueryString parameter to false to eliminate body substitution with parameters
        testStep?.testRequest?.setPostQueryString(false)

        // Add request body
        def requestContent = getRequestContentFromData(data, previousResponse, previousExpected) ?: ""
        testStep?.testRequest?.setRequestContent(requestContent)

        // Add request parameters
        setRequestParameters(data?.parameters, testStep, previousResponse, previousExpected)
		
		// Enable/Set Assertions
		def stepAssertionList = []
		stepAssertionList.add(AssertionUtils.HTTP_STATUS_ASSERTION_NAME)
		stepAssertionList.add(AssertionUtils.CONTENT_TYPE_ASSERTION_NAME)
		stepAssertionList.add(AssertionUtils.RESPONSE_ASSERTION_NAME)
		enableStepScriptAssertions (stepAssertionList, data, testCase)
    }

    /**
     * Set endpoint for REST Request from tes data.
     *
     * @param data - Data object with test data for test step execution
     * @param testCase - TestCase object which contains test case for execution.
     */
    static void setEndpoint(Data data, TestCase testCase) {

        def testStep = testCase?.getTestStepByName(data?.method) as RestTestRequestStep

        def endpoint = data?.endpoint ?: testCase?.getPropertyValue(TestCaseUtils.ENDPOINT_PROPERTY)

        log.info "endpoint = ${endpoint}"
        testStep?.getHttpRequest()?.setEndpoint(endpoint)
    }

    /**
     * Process and set up headers for REST Request:
     *   - Evaluate script for headers string if needed;
     *   - Evaluate headers string as Map; and
     *   - Set up headers for REST Request.
     *
     * @param data - Data object with test data for test step execution
     * @param previousResponse - Response from previous REST Request.
     */
    static void setHeaders(Data data, TestCase testCase, String previousResponse, String previousExpected) {
        // Select TestStep
        def testStep = testCase?.getTestStepByName(data?.method) as RestTestRequestStep

        def requestHeaders = TestCaseUtils.resolveAnnotationsLast(data?.requestHeaders?.headers, previousResponse, previousExpected)

        def evalScript = data?.requestHeaders?.evalScript
        log.info "Headers.evalScript = ${evalScript}"

        // Evaluate headers string if script present
        if (evalScript)
            try {
                requestHeaders = Eval.x(requestHeaders, evalScript)
            }
            catch (Exception e) {
                log.error "Eval script could not be evaluated for Headers: $evalScript"
                log.error "message: $e"
            }
        log.info "Headers = ${requestHeaders}"

        // Evaluate Headers as Map<String,String>
        def headersMap
        try {
            headersMap = Eval.me(requestHeaders as String) as Map<String, String>
        }
        catch (Exception e) {
            log.error "Request Headers could not be evaluated as Map: ${requestHeaders}"
            log.error "message: $e"
            headersMap = [:]
        }

        if (headersMap.getClass() != [:].getClass())
            headersMap = [:]

        StringToStringMap map = headersMap ? new StringToStringMap(headersMap) : new StringToStringMap()

        testStep?.getHttpRequest()?.setRequestHeaders(map)
    }

    /**
     * Process request content from test data:
     *   - If request content contains ANNOTATION_LAST_RESPONSE then use previousResponse as request content;
     *   - Evaluate script for request content string if needed.
     *
     * @param data - Data object with test data for test step execution
     * @param previousResponse - Response from previous REST Request.
     * @return Return processed request content string.
     */
    static String getRequestContentFromData(Data data, String previousResponse, String previousExpected) {
        def requestContent = TestCaseUtils.resolveAnnotationsLast(data?.requestBody?.body, previousResponse, previousExpected)

        log.info "requestBody.evalScript = ${data?.requestBody?.evalScript}"
        def evalScript = data?.requestBody?.evalScript

        // Evaluate body string if script present
        if (evalScript)
            try {
                requestContent = Eval.x(requestContent, evalScript)
            }
            catch (Exception e) {
                log.error "Eval script could not be evaluated for Request Content: $evalScript"
                log.error "message: $e"
            }

        log.info "requestBody = ${requestContent}"

        return requestContent
    }

    /**
     * Process and set up parameters for REST Request:
     *   - Clear parameters from previous test step execution;
     *   - Evaluate script for parameter value if needed;
     *   - Process parameters as QUERY parameter if it starts with curly bracket "{" and set up request parameter to test step; or
     *   - Process parameter values as PATH and set up it to resource path.
     *
     * @param parameters - Parameter array from test data
     * @param requestStep - Rest Test Request Step with request step for execution
     */
    static void setRequestParameters(Parameter[] parameters, RestTestRequestStep requestStep, String previousResponse, String previousExpected) {
        def resourcePath = requestStep.resource.getPath()

        // Clear parameters from previous step execution
        requestStep?.resource?.getPropertyNames()?.each { property ->
            requestStep?.resource?.removeProperty(property)
        }

        log.info "Parameters:"
        parameters?.each { parameter ->
            if (parameter) {
                def parameterName = parameter?.name
                def parameterValue = TestCaseUtils.resolveAnnotationsLast(parameter?.value, previousResponse, previousExpected)
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
                    if (!requestStep?.resource?.hasProperty(parameterName))
                        requestStep?.resource?.addProperty(parameterName)
                    requestStep?.setPropertyValue(parameterName, parameterValue as String)
                } else {
                    // Process parameter as PATH
                    resourcePath += "/" + parameterValue
                    requestStep?.resource?.setPath(resourcePath)
                }
            }
        }
    }

    /**
     * Create or update the GroovyScriptAssertion for test step specified by test data
     *
     * @param assertionName - Name for a new assertion
     * @param script - Script text for Groovy Script Assertion
     * @param data - Test Data with test step name
     * @param testCase - Test Case with list of Test Steps
     */
    static void setScriptAssertion(String assertionName, String script, Data data, TestCase testCase) {
        // Select TestStep
        def testStep = testCase?.getTestStepByName(data?.method) as RestTestRequestStep

        def assertion = testStep.getAssertionByName(assertionName) as GroovyScriptAssertion
        if (assertion) {
            assertion.setScriptText(script)
        } else {
            assertion = testStep.addAssertion(GroovyScriptAssertion.LABEL) as GroovyScriptAssertion
            assertion.setScriptText(script)
        }
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
        def testStep = testCase?.getTestStepByName(data?.method) as RestTestRequestStep

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
