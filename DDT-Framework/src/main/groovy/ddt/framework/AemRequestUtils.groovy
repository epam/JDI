package ddt.framework

import com.eviware.soapui.model.testsuite.TestCase
import com.eviware.soapui.impl.wsdl.teststeps.RestTestRequestStep
import ddt.framework.model.Data

/**
 * AEM Test Request implementation based on REST GET request.
 */
class AemRequestUtils extends RestRequestUtils {

    /**
     * Set up REST Request parameters from test data:
     *  - Set endpoint
     *  - Set Headers
     *  - Set resource path
     *  - Set media type
     *  - Set request content 
     *  - Set parameters
	 *
	 * Set Up/Enable assertions
     *
	 * @param alias - Object alias
     * @param data - Data object with test data for test step execution
     * @param testCase - TestCase object which contains test case for execution
	 * @param previousResponse - expected response of previously executed test step
	 * @param previousExpected - actual response from previously executed test step
     */
    static void setTestStepConfiguration(String alias, Data data, TestCase testCase, String previousResponse, String previousExpected) {
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
		enableStepScriptAssertions([AssertionUtils.SAVE_OBJECT_ASSERTION_NAME], data, testCase)

    }
}
