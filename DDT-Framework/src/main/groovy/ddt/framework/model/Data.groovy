package ddt.framework.model

import org.apache.commons.lang3.text.StrSubstitutor

/**
 * Data model for test data. Data object contains all data fields for one test step.
 * The substituteRequest method is used to resolve variable references in data fields.
 */
class Data {
    String description
    String endpoint
    String method
    String resourcePath
    Body expectedResponse
    String mediaType
    String status
    String contentType
    String expectedAssertionResults
    Body requestBody
    Headers requestHeaders

    Parameter[] parameters

    /**
     * Compare object with input parameter.
     * @param o - object for comparison.
     * @return Return true if input parameter is the same object or if it has the same class and all fields are equal. Else return false.
     */
    boolean equalsTo(o) {
        if (this.is(o)) return true
        if (getClass() != o?.class) return false

        Data data = o as Data

        if (expectedAssertionResults != data?.expectedAssertionResults) return false
        if (contentType != data?.contentType) return false
        if (description != data?.description) return false
        if (endpoint != data?.endpoint) return false
        if (!expectedResponse.equalsTo(data?.expectedResponse)) return false
        if (mediaType != data?.mediaType) return false
        if (method != data?.method) return false
        if (!compareParameters(parameters, data?.parameters)) return false
        if (!requestBody.equalsTo(data?.requestBody)) return false
        if (resourcePath != data?.resourcePath) return false
        if (status != data?.status) return false
        if (!requestHeaders?.equalsTo(data?.requestHeaders)) return false

        return true
    }

    /**
     * Compare two Parameter arrays.
     * @param a - the first Parameter array for comparison.
     * @param b - the second Parameter array for comparison.
     * @return Return true if both arrays have the same length and each elements are equals. Else return false.
     */
    static boolean compareParameters(Parameter[] a, Parameter[] b) {
        if (a?.length != b?.length)
            return false

        a?.eachWithIndex { Parameter parameter, int i ->
            if (!parameter?.equalsTo(b[i])) return false
        }

        return true
    }

    /**
     * Substitute variable references in data fields with StrSubstitutor.
     * @param substitutor - StrSubstitutor with defined map of values, prefix and suffix for variables interpolation.
     */
    void substituteRequest(StrSubstitutor substitutor) {
        description = substitutor?.replace(description)?.trim()
        endpoint = substitutor?.replace(endpoint)?.trim()
        method = substitutor?.replace(method)?.trim()
        resourcePath = substitutor?.replace(resourcePath)?.trim()

        mediaType = substitutor?.replace(mediaType)?.trim()
        status = substitutor?.replace(status)?.trim()
        contentType = substitutor?.replace(contentType)?.trim()
        expectedAssertionResults = substitutor?.replace(expectedAssertionResults)?.trim()

        requestBody?.with {
            // Allow spaces before and after request body
            body = substitutor?.replace(body)
            evalScript = substitutor?.replace(evalScript)?.trim()
        }

        requestHeaders?.with {
            // Allow spaces before and after request Headers
            headers = substitutor?.replace(headers)
            evalScript = substitutor?.replace(evalScript)?.trim()
        }

        parameters?.each { parameter ->
            parameter?.with {
                name = substitutor?.replace(name)?.trim()
                // Allow spaces before and after parameter value
                value = substitutor?.replace(value)
                evalScript = substitutor?.replace(evalScript)?.trim()
            }
        }
    }

    void substituteResponse(StrSubstitutor substitutor) {
        expectedResponse?.with {
            body = substitutor?.replace(body)?.trim()
            evalScript = substitutor?.replace(evalScript)?.trim()
        }
    }
	

    /**
     * Search for parameter by its Name
	 *
	 * @param searchName - parameter name that needs to be found 
	 * @return Returns the first found entry
     */	 
	Parameter getParameterByName(String searchName) {
	    // default value is NULL
	    Parameter foundParameter
		
        for(parameterItem in parameters) {
			    if ( parameterItem?.name?.trim()?.toLowerCase() == searchName.trim().toLowerCase() ) {
			        foundParameter = parameterItem
					break
				}
		}	
			
		return foundParameter
	}
	
}
