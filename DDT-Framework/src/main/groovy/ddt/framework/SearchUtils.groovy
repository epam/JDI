package ddt.framework

import ddt.framework.model.Data
import groovy.util.logging.Log4j
import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern

/**
 *
 * @author mikhail_bazhenov
 */
@Log4j
@Deprecated
class SearchUtils {
    static final Pattern FIND_PATTERN = ~/^@FIND\(type=(.+),\s+alias=(.+);\s+(.+)\)/
    static final Pattern PROPERTY_PATTERN = ~/^PROPERTY\((.+)\)/

    private static final String AEM_PRODUCT_SEARCH_TYPE = "AEM_PRODUCT"
    private static final String PNA_PRODUCT_SEARCH_TYPE = "PNA_PRODUCT"
	
	private String productAlias = ""
	private String productName = ""
	

	@Deprecated
    void processFindAnnotation(Data data, Properties commonProperties) {
        log.info "Processing find annotation"

        def description = StringUtils.strip(data.description, '"')

        def matcher = (description =~ FIND_PATTERN)
        if (matcher) {
            def type = matcher.group(1)
            def alias = matcher.group(2)
            def restrictions = matcher.group(3).trim().split(",")

            switch (type) {
                case AEM_PRODUCT_SEARCH_TYPE:
				    productAlias = alias
                    productName = AemSearchUtils.findProductInAem(data, alias, restrictions, commonProperties)
                    break
                case PNA_PRODUCT_SEARCH_TYPE:
				    productAlias = alias
                    productName = PnaSearchUtils.findProductInPna(data, alias, restrictions, commonProperties)
                    break
                default:
                    log.warn "Incorrect Type $type in FIND annotation: $description"
                    productAlias = ""
	                productName = ""
                    break
            }
        } else {
            log.warn "Incorrect syntax in FIND annotation: $description"
            productAlias = ""
	        productName = ""
        }
    }
}
