package com.epam.http.requests;

import com.epam.http.annotations.*;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.commons.LinqUtils.where;
import static com.epam.http.ExceptionHandler.exception;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class ServiceInit {
    public static void init(Class<?> c){
        String domain = null;
        if (c.isAnnotationPresent(ServiceDomain.class))
            domain = c.getAnnotation(ServiceDomain.class).domain();
        List<Field> methods = where(c.getDeclaredFields(), f -> f.getType().equals(RestMethod.class));
        for (Field method: methods) {
            try {
                method.set(null, new RestMethod(getMethodParams(method, domain)));
            } catch (IllegalAccessException ex) {
                throw exception("Can't init method %s for class %s", method.getName(), c.getName()); }
        }
    }

    private static String getUrlFromDomain(String domain, String uri) {
        if (domain == null || uri == null)
            return uri;
        if (uri.contains("://"))
            return uri;
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    private static RestMethodData getMethodParams(Field method, String domain) {
        if (method.isAnnotationPresent(Method.class)) {
            Method m = method.getAnnotation(Method.class);
            RestMethodTypes[] types = m.types();
            return new RestMethodData(getUrlFromDomain(domain, m.uri()),
                    types.length == 1 ? types[0] : null);
        }
        if (method.isAnnotationPresent(GET.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(GET.class).uri()), RestMethodTypes.GET);
        if (method.isAnnotationPresent(POST.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(POST.class).uri()), RestMethodTypes.POST);
        if (method.isAnnotationPresent(PUT.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(PUT.class).uri()), RestMethodTypes.PUT);
        if (method.isAnnotationPresent(DELETE.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(DELETE.class).uri()), RestMethodTypes.DELETE);
        return new RestMethodData();
    }
}
