package ddt.framework

import com.eviware.soapui.model.testsuite.TestCaseRunContext
import com.eviware.soapui.model.iface.MessageExchange
import groovy.util.logging.Log4j

@Log4j
/**
 * Class with REST Response assertions.
 */
class DdtfAssertions {

    /**
     * Perform REST Response assertion after REST Test Step execution.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     */
    static void scriptAssertion(MessageExchange messageExchange, TestCaseRunContext context){

        assertHTTPStatus(messageExchange, context)

        assertContentType(messageExchange, context)

        assertResponse(messageExchange, context)
    }

    /**
     * Perform assertion of HTTP Statuses after REST Test Step execution.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     */
    static void assertHTTPStatus(MessageExchange messageExchange, TestCaseRunContext context){
        def compareResult = AssertionUtils.compareHTTPStatus(messageExchange,context)
        log.info "CompareHTTPStatus = ${compareResult.value}: ${compareResult.message}"
        assert compareResult.value: compareResult.message
    }

    /**
     * Perform assertion of REST Response Content-Type after REST Test Step execution.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     */
    static void assertContentType(MessageExchange messageExchange, TestCaseRunContext context){
        def compareResult = AssertionUtils.compareContentType(messageExchange, context)
        log.info "CompareContentType = ${compareResult.value}: ${compareResult.message}"
        assert compareResult.value: compareResult.message
    }

    /**
     * Perform assertion of REST Response after REST Test Step execution.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     */
    static void assertResponse(MessageExchange messageExchange, TestCaseRunContext context){
        def compareResult = AssertionUtils.compareResponse(messageExchange, context)
        log.info "CompareResponse = ${compareResult.value}: ${compareResult.message}"
        assert compareResult.value: compareResult.message
    }

	/**
     * Perfom saving Object after REST Test Step execution
	 *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution	
	 */
	static void saveObject(MessageExchange messageExchange, TestCaseRunContext context){
	    def compareResult = AssertionUtils.saveObject(messageExchange, context)
	    log.info "saveObject = ${compareResult.value}: ${compareResult.message}"
        assert compareResult.value: compareResult.message
	}
	
    /**
     * Perform REST Response assertion after execution POST Test Step.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     *
     * @deprecated This method is obsolete. Use the scriptAssertion method.
     */
    @Deprecated
    static void postAssertion(MessageExchange messageExchange, TestCaseRunContext context) {
        scriptAssertion(messageExchange, context)
    }

    /**
     * Perform REST Response assertion after execution GET Test Step.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     *
     * @deprecated This method is obsolete. Use the scriptAssertion method.
     */
    @Deprecated
    static void getAssertion(MessageExchange messageExchange, TestCaseRunContext context){
        scriptAssertion(messageExchange, context)
    }

    /**
     * Perform REST Response assertion after execution DELETE Test Step.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     *
     * @deprecated This method is obsolete. Use the scriptAssertion method.
     */
    @Deprecated
    static void deleteAssertion(MessageExchange messageExchange, TestCaseRunContext context){
        scriptAssertion(messageExchange, context)
    }

    /**
     * Perform REST Response assertion after execution PUT Test Step.
     *
     * @param messageExchange - SoapUI Message Exchange with REST response
     * @param context - Test Case Run Context which contains test data from test step execution
     *
     * @deprecated This method is obsolete. Use the scriptAssertion method.
     */
    @Deprecated
    static void putAssertion(MessageExchange messageExchange, TestCaseRunContext context){
        scriptAssertion(messageExchange, context)
    }
}
