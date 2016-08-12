package ddt.framework

import groovy.util.logging.Log4j

import com.eviware.soapui.model.iface.MessageExchange
import com.eviware.soapui.model.testsuite.TestCase
import ddt.framework.model.Data
import ddt.framework.model.Parameter

/**
 * Class for specific Search implementations - method FIND
 *    input parameter 'type' is required, its value will be converted to low case programmatically
 *    for each known types the class should contain methods - Validate and Find with type name as prefix
 *    for example: for 'type' = 'someType' there should be implemented methods 'sometypeValidate', 'sometypeFind'
 */
@Log4j
class FindRequestUtils {
    static final private KNOWN_TYPES = ['aem_product','pna_product']  // List of known types, should be in low case, used by assertion saveObject

	final static String FIND_TYPE_PARAMETER = "find_type"
	final static String FIND_ALIAS_PARAMETER = "find_alias"

    /**
     * Process FIND method
	 * 
	 * @param data - Data object with test data for test step execution
     * @param testCase - TestCase object which contains test case for execution
	 * @param previousResponse - expected response of previously executed test step
	 * @param previousExpected - actual response from previously executed test step
     */	 
	static void processFind (Data data, TestCase testCase, String previousResponse, String previousExpected) {

		Parameter   findType = data?.getParameterByName(FIND_TYPE_PARAMETER)

        if ( findType ) {
		    // convert FIND_TYPE_PARAMETER parameter to Low case
			// at this point data should be already substituted, so we don't execute evalScript
			findType.name = findType.name.trim().toLowerCase()
			findType.value = findType.value.trim().toLowerCase()
			
		    // verify that type is known
			if ( KNOWN_TYPES.contains(findType.value) ) {

			    // validate parameters for the find type and run find
				def validationResult = "${findType.value}Validate"(data)
				if ( validationResult == "" ) 
				    "${findType.value}Find"(data,testCase,previousResponse,previousExpected)
				else {
				    log.error " ${validationResult}"
					// skip step execution
                    data?.method = "SKIP"
				}
			}
			else {
		        log.error " provided ${FIND_TYPE_PARAMETER} parameter '${findType.value}' is unknown"
			    // skip step execution
                data?.method = "SKIP"			
			}
		}		
		else {
		    log.error " required ${FIND_TYPE_PARAMETER} parameter is not found"
			// skip step execution
            data?.method = "SKIP"
		}

	}
	
	
	/**
	 * Validate aem_product find type
	 *
	 * @param data - Data object with test data for test step execution
	 * @return Returns Error message text in case of found problems, empty string when OK
	 */
	static String aem_productValidate (Data data) {
	
	    Parameter checkParameter
	    
		def aemHost = data?.endpoint ?: data?.getParameterByName(AemSearchUtils.AEM_HOST_PARAMETER)
	    if (!aemHost) return " endPoint/host is not provided"
		
		checkParameter = data?.getParameterByName(FIND_ALIAS_PARAMETER)
		if ( checkParameter == null ) return " required '${FIND_ALIAS_PARAMETER}' parameter is not found"
		
		checkParameter = data?.getParameterByName(AemSearchUtils.AEM_LOGIN_PARAMETER)
		if ( checkParameter == null ) return " required '${AemSearchUtils.AEM_LOGIN_PARAMETER}' parameter is not found"
		
		checkParameter = data?.getParameterByName(AemSearchUtils.AEM_PASSWORD_PARAMETER)
		if ( checkParameter == null ) return " required '${AemSearchUtils.AEM_PASSWORD_PARAMETER}' parameter is not found"
		
		checkParameter = data?.getParameterByName(AemSearchUtils.AEM_QUERY_BASE_PARAMETER)
		if ( checkParameter == null ) return " required '${AemSearchUtils.AEM_QUERY_BASE_PARAMETER}' parameter is not found"
		
		checkParameter = data?.getParameterByName(AemSearchUtils.AEM_QUERY_RESTRICTIONS_PARAMETER)
		if ( checkParameter == null ) log.warn " optional '${AemSearchUtils.AEM_QUERY_RESTRICTIONS_PARAMETER}' parameter is not found"
		
		return ""
	}

	/**
	* Prepare/Run Find execution for aem_product find type
	*/
    static void aem_productFind (Data data, TestCase testCase, String previousResponse, String previousExpected) {
	    Parameter aliasParameter = data?.getParameterByName(FIND_ALIAS_PARAMETER)
		if ( aliasParameter ) {
			// convert FIND_ALIAS_PARAMETER parameter name to Low case
			aliasParameter.name = aliasParameter.name.trim().toLowerCase()
			// try to find a product and reset data values
			def searchResult = AemSearchUtils.findProductInAem(data, aliasParameter.value)
			if ( searchResult!= "" )
			    // prepare soapui test step for execution
		        AemRequestUtils.setTestStepConfiguration(aliasParameter.value, data, testCase, previousResponse, previousExpected)
		}	
	}	
	
	/**
	* Validate pna_product find type
	*
	* @param data - Data object with test data for test step execution
	* @return Returns Error message text in case of found problems, empty string when OK
	*/
	static String pna_productValidate (Data data) {
	    
		Parameter checkParameter
		
		def pnaHost = data?.endpoint ?: data?.getParameterByName(PnaRequestUtils.PNA_HOST_PARAMETER)
		if (!pnaHost) return " endPoint/host is not provided"

        if (!data?.resourcePath) return " resource path is not provided"
		
		checkParameter = data?.getParameterByName(FIND_ALIAS_PARAMETER)
		if ( checkParameter == null ) return " required ${FIND_ALIAS_PARAMETER} parameter is not found"
		
		return ""
	}

	/**
	* Prepare/Run Find execution for pna_product find type
	*
    * @param data - Data object with test data for test step execution
    * @param testCase - TestCase object which contains test case for execution	
	* @param previousResponse - previous expected response from test case property
	* @param previousExpected - previous actual   response from test case property	
	*/
    static void pna_productFind (Data data, TestCase testCase, String previousResponse, String previousExpected) {
	    Parameter aliasParameter = data?.getParameterByName(FIND_ALIAS_PARAMETER)
		if ( aliasParameter ) {
			// convert FIND_ALIAS_PARAMETER parameter name to Low case
			aliasParameter.name = aliasParameter.name.trim().toLowerCase()
			
		    data?.method = "GET"
			data?.endpoint = data?.endpoint ?: data?.getParameterByName(PnaRequestUtils.PNA_HOST_PARAMETER)

		    PnaRequestUtils.setTestStepConfiguration(aliasParameter.value, data, testCase, previousResponse, previousExpected)
		}

	}

	
	/**
	 * GET Alias for object
	 *
	 * @param type - one of KNOWN_TYPES 
	 * @param messageExchange - SoapUI Message Exchange with REST response
	 * @return Returns found alias or NULL
	 */
    static String getObjectAlias(String type, MessageExchange messageExchange) {
	    // default value
	    String aliasValue = ""
	  
	    switch (type) {
	        case ["aem_product","pna_product"]:
				aliasValue = messageExchange?.modelItem?.testStep?.getPropertyValue(FIND_ALIAS_PARAMETER)
		        break
	    }
		
        //log.info " found alias ${aliasValue}"	  
	    return aliasValue
	}

	/**
	 * GET Result for object
	 *
	 * @param type - one of KNOWN_TYPES 
	 * @param messageExchange - SoapUI Message Exchange with REST response
	 * @return Returns found result or NULL
	 */	
    static String getFindResult(String type, MessageExchange messageExchange) {
        // default value is Null
	    String findResult
	  
	    switch (type) {
	        case "aem_product":
			    // take response
		        findResult = messageExchange?.responseContent?.trim()
		        break
			case "pna_product":
			    // request can be executed with some errors
				// so get http response status and assign find result if status OK
			    def httpResponseStatus = messageExchange?.responseHeaders["#status#"][0].trim()
			    if ( httpResponseStatus.contains("OK") ) findResult = messageExchange?.responseContent?.trim()
			    break
	    }
		
        //log.info " found result ${findResult} "	  
	    return findResult
	}	

	/**
	 * GET Type (or Format) for object
	 *
	 * @param type - one of KNOWN_TYPES 
	 * @param messageExchange - SoapUI Message Exchange with REST response
	 * @return Returns found type/format or EMPTY
	 */	
    static String getObjectType(String type) {
        // default value 
	    String objectType = ""
	  
	    switch (type) {
	        case ["aem_product","pna_product"]:
		        objectType = "JSON"
		        break
	    }
	  
	    // log.info " found object type ${objectType}"
	    return objectType
	}

	
}