package ddt.framework

import groovy.util.logging.Log4j

import com.eviware.soapui.model.iface.MessageExchange
import com.eviware.soapui.model.testsuite.TestCaseRunContext
import com.eviware.soapui.model.testsuite.TestCase

import ddt.framework.model.CompareResult


@Log4j
/**
 * Class with utils for response assertion.
 *
 * Class contains methods for comparison expected and actual HTTP statuses and response content types, bodies and cookies. The cookie comparison method is called if ANNOTATION_COOKIE is set in content-type field in data.
 * Each comparison may be skipped if ANNOTATION_SKIP is set in appropriate field in data. In that case the comparison result is set to true and comparison message contains 'Comparison is skipped' text.
 *
 * It is possible to compare actual and expected values both for equality and for not equality depending on value for appropriate assertion in assertion results map:
 *  - If assertion results map contains false for STATUS_ASSERTION, CONTENT_TYPE_ASSERTION or RESPONSE_ASSERTION then appropriate comparison method should expect false in result.
 *  - In other cases by default comparison methods should expect true in result.
 */
class AssertionUtils {

    final static ANNOTATION_SKIP = "@SKIP"                  // Skip assertion annotation
    final static private ANNOTATION_COOKIE = "@COOKIE"              // Assert response cookies annotation

    final static private STATUS_ASSERTION = "status"                // Key for expected assertion result for status
    final static private CONTENT_TYPE_ASSERTION = "content-type"
    // Key for expected assertion result for content-type
    final static private RESPONSE_ASSERTION = "response"            // Key for expected assertion result for response

	final static private String HTTP_STATUS_ASSERTION_NAME = 'HTTP Status Assertion'
	final static private String CONTENT_TYPE_ASSERTION_NAME = 'Content-Type Assertion'
	final static private String RESPONSE_ASSERTION_NAME = 'Response Assertion'
	final static private String SAVE_OBJECT_ASSERTION_NAME = 'Save Object Assertion'
	
    /**
     * This method returns expected value of comparison result for assertion.
     *
     * If assertion results field contains String "false" then comparison results for RESPONSE_ASSERTION is expected to be false.
     * If assertion results field is not defined in test data or if it is impossible to cast assertion results to Map type then comparison result is expected to be true by default.
     * If assertion results map for specified assertion type contains value with not Boolean type (including null object) then comparison result is also expected to be true by default.
     *
     * @param context - Test Case Run Context which contains test data from test step execution with TestCaseUtils.EXPECTED_ASSERTION_RESULTS_PROPERTY
     * @param assertionType - Assertion type: STATUS_ASSERTION, CONTENT_TYPE_ASSERTION or RESPONSE_ASSERTION
     * @return Returns expected value of comparison result for specific assertion from test case context.
     */
    static boolean getExpectedAssersionResult(TestCaseRunContext context, String assertionType) {
        def testCase = context?.getTestCase()
        def assertionString = testCase?.getPropertyValue(TestCaseUtils.EXPECTED_ASSERTION_RESULTS_PROPERTY) as String
        if (!assertionString)
            return true

        if (assertionString.toLowerCase() == "false" && assertionType == RESPONSE_ASSERTION)
            return false

        Map<String, Boolean> expectedAssertionMap
        try {
            expectedAssertionMap = Eval.me(assertionString) as Map<String, Boolean>
        }
        catch (Exception e) {
            log.error "Assertion Map could not be evaluated as Map: ${assertionString}"
            log.error "message: $e"
            expectedAssertionMap = [:]
        }

        // Check if it Map
        if (expectedAssertionMap.getClass() != [:].getClass())
            return true

        //Check if value is Boolean
        if (expectedAssertionMap[assertionType].getClass() != false.getClass())
            return true

        return expectedAssertionMap[assertionType]
    }

    /**
     * Compare expected and actual HTTP Response Status.
     *
     * Comparison may be skipped if ANNOTATION_SKIP is set in TestCaseUtils.STATUS_PROPERTY of test case. In that case the comparison result is set to true and comparison message contains 'Comparison is skipped' text.
     *
     * If expected assertion results map contains false for STATUS_ASSERTION then actual and expected statuses should be different. Else they should be equal.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution with expected HTTP Status
     * @return Returns CompareResult object with comparison result as value and comparison description text as message.
     */
    static CompareResult compareHTTPStatus(MessageExchange messageExchange, TestCaseRunContext context) {
        def testCase = context?.getTestCase()
        def stepDescription = testCase?.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)
        def expectedAssertionResult = getExpectedAssersionResult(context, STATUS_ASSERTION)

        // Compare HTTP status
        def actualStatus = messageExchange?.responseHeaders["#status#"][0].trim()
        def expectedStatus = testCase?.getPropertyValue(TestCaseUtils.STATUS_PROPERTY)
        testCase?.setPropertyValue(TestCaseUtils.CURRENT_STATUS_PROPERTY, actualStatus)

        if (expectedStatus?.toUpperCase() != ANNOTATION_SKIP) {

            log.info "expectedAssertionResult for Status = ${expectedAssertionResult}"
            log.info "actualAssertionResult for Status = ${actualStatus == expectedStatus}"
            if (expectedAssertionResult)
                return new CompareResult(
                        value: actualStatus == expectedStatus,
                        message: "STATUS: ${stepDescription} \r\n" +
                                "Results should be EQUAL: \r\n" +
                                "'Expected' = '${expectedStatus}' \r\n" +
                                "'Actual'  =  '${actualStatus}'")
            else
                return new CompareResult(
                        value: actualStatus != expectedStatus,
                        message: "STATUS: ${stepDescription} \r\n" +
                                "Results should NOT be EQUAL: \r\n" +
                                "'Expected' = '${expectedStatus}' \r\n" +
                                "'Actual'  =  '${actualStatus}'")

        } else
            return new CompareResult(
                    value: true,
                    message: "STATUS: ${stepDescription} \r\n" +
                            "Comparison is skipped")
    }

    /**
     * Compare expected and actual content type if it exist.
     *
     * Comparison may be skipped if ANNOTATION_SKIP is set in TestCaseUtils.CONTENT_TYPE_PROPERTY of test case.
     *
     * Comparison is skipped if
     *  - the ANNOTATION_SKIP is set in TestCaseUtils.CONTENT_TYPE_PROPERTY of test case, then the comparison result is set to true and comparison message contains 'Comparison is skipped' text;
     *  - the ANNOTATION_COOKIE is set in TestCaseUtils.CONTENT_TYPE_PROPERTY of test case, then the comparison result is set to true and comparison message contains 'Comparison is skipped' text; or
     *  - the response content type is empty, then thecomparison result is set to true and comparison message contains 'Actual content-type is not exist' text.
     *
     *  If expected assertion results map contains false for CONTENT_TYPE_ASSERTION then actual and expected content-types should be different. Else they should be equal.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution with expected Content-Type
     * @return Returns CompareResult object with comparison result as value and comparison description text as message.
     */
    static CompareResult compareContentType(MessageExchange messageExchange, TestCaseRunContext context) {
        def testCase = context?.getTestCase()
        def stepDescription = testCase?.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)
        def expectedAssertionResult = getExpectedAssersionResult(context, CONTENT_TYPE_ASSERTION)

        // Compare format and encoding if content type if exists
        def expectedContentType = testCase?.getPropertyValue(TestCaseUtils.CONTENT_TYPE_PROPERTY)

        if (expectedContentType?.toUpperCase() != ANNOTATION_SKIP &&
                expectedContentType?.toUpperCase() != ANNOTATION_COOKIE) {

            def actualContentType = messageExchange?.getResponseHeaders()["Content-Type"]
            if (actualContentType) {
                // If actual content-type exist, then compare it with expected
                log.info "expectedAssertionResult for Content-Type = ${expectedAssertionResult}"
                log.info "actualAssertionResult for Content-Type = ${actualContentType[0] == expectedContentType}"
                if (expectedAssertionResult)
                    return new CompareResult(
                            value: actualContentType[0] == expectedContentType,
                            message: "Content-type: ${stepDescription} \r\n" +
                                    "Results should be EQUAL: \r\n" +
                                    "'Expected' = '${expectedContentType}' \r\n" +
                                    "'Actual'  =  '${actualContentType[0]}'")
                else return new CompareResult(
                        value: actualContentType[0] != expectedContentType,
                        message: "Content-type: ${stepDescription} \r\n" +
                                "Results should NOT be EQUAL: \r\n" +
                                "'Expected' = '${expectedContentType}' \r\n" +
                                "'Actual'  =  '${actualContentType[0]}'")
            } else //Else skip comparison
                return new CompareResult(
                        value: true,
                        message: "Content-type: ${stepDescription} \r\n" +
                                "Actual content-type is not exist")
        }

        return new CompareResult(
                value: true,
                message: "Content-type: ${stepDescription} \r\n" +
                        "Comparison is skipped")
    }

    /**
     * Compare expected and actual REST responses.
     *
     * If expected content type is set to ANNOTATION_COOKIE then call the compare response cookies method else call the response body method.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution with expected response
     * @return Returns CompareResult object with comparison result as value and comparison description text as message.
     */
    static CompareResult compareResponse(MessageExchange messageExchange, TestCaseRunContext context) {
        def testCase = context?.getTestCase()
        def expectedContentType = testCase?.getPropertyValue(TestCaseUtils.CONTENT_TYPE_PROPERTY)
        if (expectedContentType?.toUpperCase() == ANNOTATION_COOKIE) // Compare cookie
            return compareCookie(messageExchange, context)
        else // Compare response body
            return compareResponseBody(messageExchange, context)
    }

    /**
     * Compare expected and actual bodies of REST responses.
     *
     * Comparison may be skipped if ANNOTATION_SKIP is set in TestCaseUtils.EXPECTED_RESPONSE_PROPERTY of test case. In that case the comparison result is set to true and comparison message contains 'Comparison is skipped' text.
     *
     * If it is possible then response bodies should be compared as JSON, else as String.
     *
     * If expected assertion results map contains false for RESPONSE_ASSERTION then actual and expected responses should be different. Else they should be equal.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution with expected response
     * @return Returns CompareResult object with comparison result as value and comparison description text as message.
     */
    static CompareResult compareResponseBody(MessageExchange messageExchange, TestCaseRunContext context) {
        log.info "Compare Response Body"
        def testCase = context?.getTestCase()
        def stepDescription = testCase?.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)
        def expectedAssertionResult = getExpectedAssersionResult(context, RESPONSE_ASSERTION)

        //Get and compare response
        def expectedResponse = testCase?.getPropertyValue(TestCaseUtils.EXPECTED_RESPONSE_PROPERTY)
        def actualResponse = messageExchange?.responseContent?.trim()

        //Set actual response to property
        testCase?.setPropertyValue(TestCaseUtils.CURRENT_RESPONSE_PROPERTY, actualResponse)

        // Process actual data before assertion
        def evalScript = testCase?.getPropertyValue(TestCaseUtils.EVALUATE_RESPONSE_SCRIPT_PROPERTY)

        def isEvaluated = false
        if (evalScript) {
            try {
                // This construction makes available messageExchange and context in scope of script evaluation
                actualResponse = Eval.xyz(actualResponse, messageExchange, context, "def messageExchange = y; def context = z; $evalScript")
                log.info "Script is evaluated for response = ${evalScript}"
                isEvaluated = true
            }
            catch (Exception e) {
                log.error "Eval script could not be evaluated for Actual Response: $evalScript"
                log.error "message: $e"
            }
        } else
            log.info "Script is NOT evaluated for response, script =  ${evalScript}"

        if (expectedResponse?.toUpperCase() != ANNOTATION_SKIP) {

            def comparisonResults = CompareUtils.compare(expectedResponse, actualResponse as String)

            log.info "expectedAssertionResult for Response Body = ${expectedAssertionResult}"
            log.info "actualAssertionResult for Response Body = ${comparisonResults.value}"

            return new CompareResult(
                    value: comparisonResults.value == expectedAssertionResult,
                    message: "RESPONSE: ${stepDescription} \r\n" +
                            "COMPARISON RESULTS ${expectedAssertionResult ? 'should be EQUAL' : 'should NOT be EQUAL'}: \r\n" +
                            "${comparisonResults.message} \r\n" +
                            "isEvaluated: $isEvaluated \r\n")
        } else
            return new CompareResult(
                    value: true,
                    message: "RESPONSE: ${stepDescription} \r\n" +
                            "Comparison is skipped")
    }

    /**
     * Compare expected and actual Set-Cookies of REST responses.
     *
     * Comparison may be skipped if ANNOTATION_SKIP is set in TestCaseUtils.EXPECTED_RESPONSE_PROPERTY of test case. In that case the comparison result is set to true and comparison message contains 'Comparison is skipped' text.
     *
     * If expected assertion results map contains false for RESPONSE_ASSERTION then actual and expected response cookies should be different. Else they should be equal.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution with expected response cookie
     * @return Returns CompareResult object with comparison result as value and comparison description text as message.
     */
    static CompareResult compareCookie(MessageExchange messageExchange, TestCaseRunContext context) {
        log.info "Compare Cookie"
        def testCase = context?.getTestCase()
        def stepDescription = testCase?.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)
        def expectedAssertionResult = getExpectedAssersionResult(context, RESPONSE_ASSERTION)

        //Get and compare cookie
        def expectedResponse = testCase?.getPropertyValue(TestCaseUtils.EXPECTED_RESPONSE_PROPERTY)
        def actualResponse = messageExchange?.responseHeaders["Set-Cookie"]?.toString()

        //Set actual response to property
        testCase?.setPropertyValue(TestCaseUtils.CURRENT_RESPONSE_PROPERTY, actualResponse)

        // Process actual data before assertion
        def evalScript = testCase?.getPropertyValue(TestCaseUtils.EVALUATE_RESPONSE_SCRIPT_PROPERTY)

        def isEvaluated = false
        if (evalScript) {
            try {
                // This construction makes available messageExchange and context in scope of script evaluation
                actualResponse = Eval.xyz(actualResponse, messageExchange, context, "def messageExchange = y; def context = z; $evalScript")
                log.info "Script is evaluated for response = ${evalScript}"
                isEvaluated = true
            }
            catch (Exception e) {
                log.error "Eval script could not be evaluated for Actual Response: $evalScript"
                log.error "message: $e"
            }
        } else
            log.info "Script is NOT evaluated for response, script =  ${evalScript}"

        if (expectedResponse?.toUpperCase() != ANNOTATION_SKIP) {

            expectedResponse = expectedResponse.toString() == "null" ? "" : expectedResponse
            actualResponse = actualResponse.toString() == "null" ? "" : actualResponse

            log.info "expectedAssertionResult for Cookie = ${expectedAssertionResult}"
            log.info "actualAssertionResult for Cookie = ${actualResponse == expectedResponse}"
            if (expectedAssertionResult)
                return new CompareResult(
                        value: actualResponse == expectedResponse,
                        message: "RESPONSE COOKIES: ${stepDescription} \r\n" +
                                "isEvaluated: $isEvaluated \r\n" +
                                "Results should be EQUAL: \r\n" +
                                "'Expected' = '${expectedResponse}' \r\n" +
                                "'Actual'  =  '${actualResponse}'")
            else
                return new CompareResult(
                        value: actualResponse != expectedResponse,
                        message: "RESPONSE COOKIES: ${stepDescription} \r\n" +
                                "isEvaluated: $isEvaluated \r\n" +
                                "Results should NOT be EQUAL: \r\n" +
                                "'Expected' = '${expectedResponse}' \r\n" +
                                "'Actual'  =  '${actualResponse}'")
        } else
            return new CompareResult(
                    value: true,
                    message: "RESPONSE COOKIES: ${stepDescription} \r\n" +
                            "Comparison is skipped")
    }


	/**
	 * Save Object from response of REST request
	 *
	 * Used for FIND method at the moment
	 * 
	 * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution with expected response cookie
     * @return Returns CompareResult object with comparison result as value and comparison description text as message
	 */
    static CompareResult saveObject(MessageExchange messageExchange, TestCaseRunContext context) {
        log.info "Save Object"
		def testCase = context?.getTestCase()
        def stepDescription = testCase?.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)

		// get actual response 
		def actualResponse = messageExchange?.responseContent?.trim()
		
		//Set actual response to property
        testCase?.setPropertyValue(TestCaseUtils.CURRENT_RESPONSE_PROPERTY, actualResponse)
									   
        // at this point we suppose that the assertion is enabled|set correctly
		// so we will not check which method this assertion is applied for
        
		// when it's FIND method
		String typeValue = messageExchange?.modelItem?.testStep?.getPropertyValue("find_type")
        if ( typeValue ) {	
		    if ( FindRequestUtils.KNOWN_TYPES.contains(typeValue.trim().toLowerCase()) ) {

			    String objectAlias = FindRequestUtils?.getObjectAlias(typeValue, messageExchange)
				String objectType  = FindRequestUtils?.getObjectType(typeValue)
				String findResult  = FindRequestUtils?.getFindResult(typeValue, messageExchange)
				
				if ( findResult ) {
				    if ( objectAlias ) {
					
					    def evalUtils = new EvalUtils()
			            //evalUtils.VARIABLES = context?.variablesMapping?.variablesMap			
			            evalUtils.PRODUCT_VARIABLES = context?.variablesMapping?.productsMap
						def saveResult = evalUtils.setProduct(objectAlias,findResult)
					
						if ( saveResult != '' ) {
// 	context?.variablesMapping?.productsMap = evalUtils.PRODUCT_VARIABLES					   
						    return new CompareResult(
	                                          value: true,
				                              message: "OBJECT is SAVED: ${stepDescription} \r\n" +
											           "Format: ${objectType} \r\n" +
													   "Alias: ${objectAlias} \r\n" +
					    	        	               "'Actual' = '${saveResult}'")
						}
						else 
						    return new CompareResult(
	                                          value: false,
				                              message: "OBJECT is NOT SAVED: ${stepDescription} \r\n" +
					    	        	               "Some problems appeared during saving")
					}
					else
					    return new CompareResult(
	                                      value: false,
				                          message: "OBJECT is NOT SAVED: ${stepDescription} \r\n" +
					    	        	           "Object alias can not be defined")
 
				}
				else
				    return new CompareResult(
	                                  value: false,
				                      message: "OBJECT is NOT SAVED: ${stepDescription} \r\n" +
					    	    	           "Nothing has been found")
            }    
		    else
			    return new CompareResult(
	                              value: false,
				                  message: "OBJECT is NOT SAVED: ${stepDescription} \r\n" +
					    		           "Provided type ${typeValue} is unknown")
		}   
		
        // could not find any procedures to save Object		
        return new CompareResult(
	                      value: false,
		                  message: "OBJECT is NOT SAVED: ${stepDescription} \r\n" +
						           "Procedures on saving object do not exist")
	}
	
	
	/**
	 * Get Script text to create Script Assertions for test steps
	 *
	 * @param assertionName - assertion name to create, for example 'HTTP Status Assertion'
	 * @return Text for script assertion
	 */
	static String getScriptAssertionText (String assertionName) {
	    // default value
	    String scriptText = ""
	    
		switch(assertionName) {
		    case HTTP_STATUS_ASSERTION_NAME:
			    scriptText = " ddt.framework.DdtfAssertions.assertHTTPStatus(messageExchange, context) "
			    break
			case CONTENT_TYPE_ASSERTION_NAME:
			    scriptText = " ddt.framework.DdtfAssertions.assertContentType(messageExchange, context) "
			    break
			case RESPONSE_ASSERTION_NAME:
			    scriptText = " ddt.framework.DdtfAssertions.assertResponse(messageExchange, context) "
			    break
			case SAVE_OBJECT_ASSERTION_NAME:
			    scriptText = " ddt.framework.DdtfAssertions.saveObject(messageExchange, context)  "
			    break
			
		}
		
		return scriptText
		
	}

}
