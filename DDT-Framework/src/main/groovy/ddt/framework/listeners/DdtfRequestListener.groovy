package ddt.framework.listeners

import com.eviware.soapui.impl.wsdl.submit.filters.AbstractRequestFilter
import com.eviware.soapui.impl.wsdl.testcase.WsdlTestRunContext
import com.eviware.soapui.model.iface.Request
import com.eviware.soapui.model.iface.SubmitContext
import ddt.framework.TestCaseUtils
import org.apache.http.client.CookieStore
import org.apache.http.client.protocol.ClientContext
import org.apache.http.impl.cookie.BasicClientCookie
import org.apache.http.protocol.BasicHttpContext
import org.apache.log4j.Logger

/**
 * Add User defined Cookie to HTTP Request
 */
class DdtfRequestListener extends AbstractRequestFilter {
    protected final Logger scriptLogger = Logger.getLogger("groovy.log")

    @Override
    public void filterRequest(SubmitContext context, Request request) {

        if (context instanceof WsdlTestRunContext) {

            def testCase = (context as WsdlTestRunContext).getTestCase()
            def endpoint = testCase?.getPropertyValue(TestCaseUtils.ENDPOINT_PROPERTY)
            def cookie = testCase?.getPropertyValue(TestCaseUtils.COOKIE_PROPERTY)

            if (context.httpMethod.URI.toString().startsWith(endpoint) && cookie) {
                scriptLogger.info "Step: ${testCase?.getPropertyValue(TestCaseUtils.DESCRIPTION_PROPERTY)}"


                // Evaluate Cookie as Map<String,String>
                def cookieMap
                try {
                    cookieMap = Eval.me(cookie as String) as Map<String, String>
                }
                catch (Exception e) {
                    scriptLogger.error "Cookies could not be evaluated as Map: ${cookie}"
                    scriptLogger.error "message: $e"
                    cookieMap = [:]
                }

                def httpContext = context.getProperty(SubmitContext.HTTP_STATE_PROPERTY)

                def cookieStore = (httpContext as BasicHttpContext).getAttribute(ClientContext.COOKIE_STORE)

                if (!isCookieExist(cookieStore as CookieStore, cookieMap.name))
                    addCookie(cookieStore as CookieStore, cookieMap)

            }
        }
    }

    private boolean isCookieExist(CookieStore cookieStore, String cookieName) {
        def cookies = (cookieStore as CookieStore).getCookies()
        cookies.each {
            scriptLogger.info "cookie: $it"
        }

        def oldCookie = cookies.findAll { it.name == cookieName }

        scriptLogger.info "Old Cookie = '$oldCookie'"
        if (oldCookie)
            return true

        return false
    }

    private void addCookie(CookieStore cookieStore, Map<String, String> cookie) {
        def newCookie = new BasicClientCookie(cookie.name, cookie.value)
        newCookie.version = cookie.version ? cookie.version.toInteger() : 0
        newCookie.domain = cookie.domain ?: ''
        newCookie.path = cookie.path ?: ''
        scriptLogger.info "Add Cookie = '$newCookie'"
        cookieStore.addCookie(newCookie)
    }

}
