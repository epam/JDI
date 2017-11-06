package com.epam.jdi.http.cucumber;

import com.epam.http.PerformanceResult;
import com.epam.http.annotations.*;
import com.epam.http.requests.MethodData;
import com.epam.http.requests.RestMethod;
import com.epam.http.requests.RestMethodTypes;

import com.epam.http.requests.RestResponse;
import com.jayway.restassured.http.ContentType;

import static com.epam.http.ExceptionHandler.exception;
import static com.epam.http.requests.RestMethodTypes.*;


public class Utils {
    public static ThreadLocal<IRestService> service = new ThreadLocal<>();
    public static ThreadLocal<String> domainUrl = new ThreadLocal<>();
    public static ThreadLocal<PerformanceResult> performanceResult = new ThreadLocal<>();
    public static ThreadLocal<RestResponse> restResponse = new ThreadLocal<>();

    public static RestMethod getRestMethod(String restMethodType) {
        MethodData mad = getMethodData(restMethodType);
        String url = getUrlFromDomain(domainUrl.get(), mad.getUrl(), restMethodType);
        return new RestMethod(url, mad.getType());
    }

    private static MethodData getMethodData(String type) {
        for (RestMethodTypes methodType : RestMethodTypes.values()) {
            if(type.equalsIgnoreCase(methodType.name()))
                return new MethodData(type.toLowerCase(), methodType);
        }
        return new MethodData(null, GET);
    }
    private static String getUrlFromDomain(String domain, String uri, String methodName) {
        if (uri == null)
            return null;
        if (uri.contains("://"))
            return uri;
        if (domain == null)
            throw exception(
            "Can't instantiate method '%s' for domain '%s'. " +
                    "Domain undefined and method url not contains '://'",
                    methodName, domain);
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    public static void setRequestContentType(RestMethod method, String requestContType) {
        for (ContentType contentType : ContentType.values()) {
            if(requestContType.equalsIgnoreCase(contentType.name()))
                method.spec.contentType(contentType);
        }
    }

    public static <T> String getDomain(Class<T> c) {
        return c.isAnnotationPresent(ServiceDomain.class)
                ? c.getAnnotation(ServiceDomain.class).value()
                : null;
    }
}
