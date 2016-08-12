package ddt.framework

import ddt.framework.model.Data
import ddt.framework.model.search.Condition
import groovy.util.logging.Log4j
import org.apache.commons.lang3.StringUtils
import org.apache.jackrabbit.commons.JcrUtils

import javax.jcr.Node
import javax.jcr.Repository
import javax.jcr.Session
import javax.jcr.SimpleCredentials
import javax.jcr.query.Query
import java.util.function.Predicate
import java.util.stream.Collectors
import java.util.stream.Stream
import java.util.stream.StreamSupport
import java.util.regex.Pattern

/**
 * search in AEM repository and preparation for GET Request.
 */
@Log4j
class AemSearchUtils {
    
	final static String AEM_HOST_PARAMETER = "aemHost"
    final static String AEM_LOGIN_PARAMETER = "aemLogin"
    final static String AEM_PASSWORD_PARAMETER = "aemPassword"
	final static String AEM_QUERY_BASE_PARAMETER = "aemQueryBase"          // something like PRODUCT_SEARCH_TEMPLATE
	final static String AEM_QUERY_RESTRICTIONS_PARAMETER = "aemQueryRestrictions"   // comma delemited string of conditions - something like comment below
	              // HAS_SPECIFICATIONS eq true, HAS_MULTIPLE_SKUS eq true, PROPERTY(mktg_isClearance) eq false, PROPERTY(pim_imageLinks) ne null

    final static Pattern PROPERTY_PATTERN = ~/^PROPERTY\((.+)\)/				  
				  
    private static final OPERATORS = ["lt": "<", "le": "<=", "eq": "=", "ne": "!=", "gt": ">", "ge": ">="]

//    private static
//    final String PRODUCT_SEARCH_TEMPLATE = "SELECT * FROM [oak:Unstructured] AS s WHERE ISDESCENDANTNODE([/etc/commerce/products/canadian-tire-goods/])"


    /**
	 * Find product in AEM and prepare Data for GET Request
	 *
	 * @param data - Data object with test data for test step execution
	 * @param alias - Object alias
	 */
    static String findProductInAem(Data data, String alias) {
	
        def aemHost = data?.endpoint ?: data?.getParameterByName(AEM_HOST_PARAMETER)?.value
        def aemLogin = data?.getParameterByName(AEM_LOGIN_PARAMETER)?.value
        def aemPassword = data?.getParameterByName(AEM_PASSWORD_PARAMETER)?.value
		def aemQueryBase = data?.getParameterByName(AEM_QUERY_BASE_PARAMETER)?.value ?: ""
		def aemRestrictions = data?.getParameterByName(AEM_QUERY_RESTRICTIONS_PARAMETER)?.value?.split(',') ?: []


        if (aemHost) {
            def conditions = []
			// query Base should contain something like PRODUCT_SEARCH_TEMPLATE
			def queryAemString = aemQueryBase
            def queryBuilder = new StringBuilder(queryAemString)
            aemRestrictions.each {
                def restriction = it.trim().split(/\s+/, 3)
                def rule = restriction[0]
                def operator = restriction[1]
                def value = restriction[2]

                def propertyMatcher = (rule =~ PROPERTY_PATTERN)
                if (propertyMatcher) {
                    def propertyName = propertyMatcher.group(1)
                    queryBuilder.append(" AND [$propertyName]")
                    if ("null".equals(value)) {
                        switch (operator) {
                            case "eq":
                                queryBuilder.append(" is null")
                                break
                            case "ne":
                                queryBuilder.append(" is not null")
                                break
                        }
                    } else {
                        queryBuilder.append(" ${OPERATORS[operator]} $value")
                    }
                } else {
                    log.info "Add condition: ${new Condition(rule, operator, value).toString()}"
                    conditions.add(new Condition(rule, operator, value))
                }
            }
            log.info "Quering JCR server: ${queryBuilder.toString()}"
            Node foundProduct = findProductInAem(aemHost, aemLogin, aemPassword, queryBuilder.toString(), conditions)
            if (foundProduct != null) {
                log.info "Found product: $aemHost${foundProduct.path}.infinity.json"

                updateDataToRequest(data,
                        data?.method ?: "GET",
                        data?.endpoint ?: aemHost,
                        "${foundProduct.path}.infinity.json",
                        data?.expectedResponse?.body ?: AssertionUtils.ANNOTATION_SKIP,
                        data?.status ?: AssertionUtils.ANNOTATION_SKIP,
                        data?.contentType ?: AssertionUtils.ANNOTATION_SKIP)

                return alias
            } else {
                log.warn "No products were found"
                updateDataToRequest(data, "SKIP")
                return ''
            }
        } else {
            log.error "AEM host is not found"
            updateDataToRequest(data, "SKIP")
            return ''
        }
    }	
	
	@Deprecated
    static String findProductInAem(Data data, String alias, String[] restrictions, Properties commonProperties) {

        def aemHost = data?.endpoint ?: commonProperties?.getProperty(TestCaseUtils.AEM_HOST_PROPERTY)
        def aemLogin = commonProperties?.getProperty(TestCaseUtils.AEM_LOGIN_PROPERTY)
        def aemPassword = commonProperties?.getProperty(TestCaseUtils.AEM_PASSWORD_PROPERTY)
		def aemQueryFrom = commonProperties?.getProperty(TestCaseUtils.AEM_QUERY_FROM_PROPERTY)
		def aemQueryWhere = commonProperties?.getProperty(TestCaseUtils.AEM_QUERY_WHERE_PROPERTY)

        if (aemHost) {
            def conditions = []
			def queryAemString = "SELECT * FROM ${aemQueryFrom} AS s WHERE ISDESCENDANTNODE(${aemQueryWhere})"
            def queryBuilder = new StringBuilder(queryAemString)
            restrictions.each {
                def restriction = it.trim().split(/\s+/, 3)
                def rule = restriction[0]
                def operator = restriction[1]
                def value = restriction[2]

                def propertyMatcher = (rule =~ SearchUtils.PROPERTY_PATTERN)
                if (propertyMatcher) {
                    def propertyName = propertyMatcher.group(1)
                    queryBuilder.append(" AND [$propertyName]")
                    if ("null".equals(value)) {
                        switch (operator) {
                            case "eq":
                                queryBuilder.append(" is null")
                                break
                            case "ne":
                                queryBuilder.append(" is not null")
                                break
                        }
                    } else {
                        queryBuilder.append(" ${OPERATORS[operator]} $value")
                    }
                } else {
                    log.info "Add condition: ${new Condition(rule, operator, value).toString()}"
                    conditions.add(new Condition(rule, operator, value))
                }
            }
            log.info "Quering JCR server: ${queryBuilder.toString()}"
            Node foundProduct = findProductInAem(aemHost, aemLogin, aemPassword, queryBuilder.toString(), conditions)
            if (foundProduct != null) {
                log.info "Found product: $aemHost${foundProduct.path}.infinity.json"

                updateDataToRequest(data,
                        data?.method ?: "AEM",
                        data?.endpoint ?: aemHost,
                        "${foundProduct.path}.infinity.json",
                        data?.expectedResponse?.body ?: AssertionUtils.ANNOTATION_SKIP,
                        data?.status ?: AssertionUtils.ANNOTATION_SKIP,
                        data?.contentType ?: AssertionUtils.ANNOTATION_SKIP)

                return alias
            } else {
                log.warn "No products were found"
                updateDataToRequest(data, "SKIP")
                return ''
            }
        } else {
            log.error "AEM host is not found"
            updateDataToRequest(data, "SKIP")
            return ''
        }
    }

    static private updateDataToRequest(Data data, method, endpoint, resourcePath, expectedResult, status, contentType) {
        data?.method = method
        data?.endpoint = endpoint
        data?.resourcePath = resourcePath
        data?.expectedResponse?.body = expectedResult
        data?.status = status
        data?.contentType = contentType
    }

    static private updateDataToRequest(Data data, method) {
        data?.method = method
    }

    static Node findProductInAem(
            final String host,
            final String login,
            final String password,
            final String queryString,
            final ArrayList<Condition> conditions) {
        log.info "Connecting to repository: $host/crx/server"
        Repository repository = JcrUtils.getRepository("$host/crx/server")

        if (login && password) {
            log.info "Login to repository..."
            Session session = repository.login(new SimpleCredentials(login, password.toCharArray()), "crx.default")

            def queryManager = session.getWorkspace().getQueryManager()
            def query = queryManager.createQuery(queryString, Query.JCR_SQL2)
            log.info "Execute SQL2 query..."
            def foundNodes = query.execute().nodes

            log.info "Found ${foundNodes.size} nodes in AEM"

            List<Predicate> predicates = conditions.collect { convertConditionToPredicate(it) }

            Stream<Node> nodeStream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(foundNodes, Spliterator.ORDERED), false)
            predicates.each { nodeStream = nodeStream.filter(it) }
            List<Node> result = nodeStream.collect(Collectors.toList())

            if (!result.isEmpty()) {
                log.info "Found ${result.size()} records in result"
                return result.get(0)
            } else {
                log.warn "No products found"
                return null
            }
        } else {
            log.error "AEM Credentials not found in properties"
            return null
        }
    }

    private static Predicate convertConditionToPredicate(Condition cond) {
        switch (cond.rule) {
            case "HAS_SPECIFICATIONS":
                return getSpecificationsPredicate(cond.operator, Boolean.valueOf(cond.value))
            case "HAS_MULTIPLE_SKUS":
                return getSkusPredicate(cond.operator, Boolean.valueOf(cond.value))
            default:
                return null
        }
    }

    private static Predicate getSpecificationsPredicate(String operator, boolean value) {
        switch (operator) {
            case "eq":
                return new Predicate<Node>() {
                    @Override
                    boolean test(Node node) {
                        if (value) {
                            return node.hasNode("specifications")
                        } else {
                            return !node.hasNode("specifications")
                        }
                    }
                }
            case "ne":
                return new Predicate<Node>() {
                    @Override
                    boolean test(Node node) {
                        if (value) {
                            return !node.hasNode("specifications")
                        } else {
                            return node.hasNode("specifications")
                        }
                    }
                }
            default:
                return null
        }
    }

    private static Predicate getSkusPredicate(String operator, boolean value) {
        switch (operator) {
            case "eq":
                return new Predicate<Node>() {
                    @Override
                    boolean test(Node node) {
                        if (value) {
                            return getSkusCount(node) >= 2
                        } else {
                            return getSkusCount(node) < 2
                        }
                    }
                }
            case "ne":
                return new Predicate<Node>() {
                    @Override
                    boolean test(Node node) {
                        if (value) {
                            return getSkusCount(node) < 2
                        } else {
                            return getSkusCount(node) >= 2
                        }
                    }
                }
            default:
                return null
        }
    }

    private static int getSkusCount(final Node node) {
        def childNodes = node.nodes
        int count = 0;
        while (childNodes.hasNext()) {
            def child = childNodes.nextNode();
            if (StringUtils.isNumeric(child.getName())) {
                ++count
            }
        }

        return count
    }
}
