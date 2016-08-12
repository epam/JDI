package ddt.framework

import ddt.framework.model.Data
import groovy.util.logging.Log4j

/**
 * PNA Search logic and preparation for GET Request.
 */
@Log4j
@Deprecated
class PnaSearchUtils {
    private static final String PNA_RESOURCE = "/PriceAvailability?"

	@Deprecated
    static findProductInPna(Data data, String alias, String[] restrictions, Properties commonProperties) {
        def pnaHost = commonProperties?.getProperty(TestCaseUtils.ESB_HOST_PROPERTY)

        if (pnaHost) {
            def uri = new StringBuilder(PNA_RESOURCE)

            restrictions.each {
                def restriction = it.trim().split(/\s+/, 3)
                def rule = restriction[0]
                def value = restriction[2]

                def propertyMatcher = (rule =~ SearchUtils.PROPERTY_PATTERN)
                if (propertyMatcher) {
                    def propertyName = propertyMatcher.group(1)
                    uri.append("$propertyName=$value&")
                }
            }

            log.info "Getting PnA product/sku: ${uri.toString()}"
            /*def json = new JsonSlurper().parse(new InputStreamReader(new URL(uri.toString()).openStream()))
            if (json instanceof Map) {
                log.info "No products were found"
                updateDataToRequest(data, "SKIP")
                return ''
            }*/

            updateDataToRequest(data,
                    data?.method ?: "PNA",
                    data?.endpoint ?: pnaHost,
                    uri.toString(),
                    data?.expectedResponse?.body ?: AssertionUtils.ANNOTATION_SKIP,
                    data?.status ?: AssertionUtils.ANNOTATION_SKIP,
                    data?.contentType ?: AssertionUtils.ANNOTATION_SKIP)

            /*def product = new Product((json as List)[0])
            log.info "Found product: $product"
            EvalUtils.PRODUCT_VARIABLES.put(alias, product)
            log.info "PRODUCTS: ${EvalUtils.PRODUCT_VARIABLES}"*/
            return alias
        } else {
            log.error "ESB host is not found"
            updateDataToRequest(data, "SKIP")
            return ''
        }
    }

	@Deprecated
    static private updateDataToRequest(Data data, method, endpoint, resourcePath, expectedResult, status, contentType) {
        data?.method = method
        data?.endpoint = endpoint
        data?.resourcePath = resourcePath
        data?.expectedResponse?.body = expectedResult
        data?.status = status
        data?.contentType = contentType
    }

	@Deprecated
    static private updateDataToRequest(Data data, method) {
        data?.method = method
    }
}


