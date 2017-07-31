package com.epam.http.requests;

import com.epam.http.annotations.*;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.commons.LinqUtils.where;
import static com.epam.http.ExceptionHandler.exception;
import static java.lang.reflect.Modifier.isStatic;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class ServiceInit {/*
    public static <T> T init(Class<T> c){
        String domain = null;
        if (c.isAnnotationPresent(ServiceDomain.class))
            domain = c.getAnnotation(ServiceDomain.class).value();
        List<Field> methods = where(c.getDeclaredFields(),
                f -> f.getType().equals(RestMethod.class) && isStatic(f.getModifiers()));
        for (Field method: methods) {
            try {
                method.setAccessible(true);
                if (method.get(null) == null)
                    method.set(null, new RestMethod(getMethodParams(method, domain)));
            } catch (IllegalAccessException ex) {
                throw exception("Can't init method %s for class %s", method.getName(), c.getName()); }
        }
    }*/
    public static <T> T init(Class<T> c) {
        String domain = null;
        if (c.isAnnotationPresent(ServiceDomain.class))
            domain = c.getAnnotation(ServiceDomain.class).value();
        List<Field> methods = where(c.getDeclaredFields(),
                f -> f.getType().equals(RestMethod.class));
        for (Field method: methods) {
            try {
                method.setAccessible(true);
                if (isStatic(method.getModifiers()) && method.get(null) == null)
                    method.set(null, new RestMethod(getMethodParams(method, domain)));
                if (!isStatic(method.getModifiers()) && method.get(getService(c)) == null)
                    method.set(getService(c), new RestMethod(getMethodParams(method, domain)));
            } catch (IllegalAccessException ex) {
                throw exception("Can't init method %s for class %s", method.getName(), c.getName()); }
        }
        return getService(c);
    }
    private static Object service;
    private static <T> T getService(Class<T> c) {
        if (service != null) return (T) service;
        try {
            return (T) (service = c.newInstance());
        } catch (IllegalAccessException|InstantiationException ex) {
            throw exception(
                "Can't instantiate class %s, Service class should have empty constructor",
                c.getName()); }
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
            return new RestMethodData(getUrlFromDomain(domain, m.value()),
                    types.length == 1 ? types[0] : null);
        }
        if (method.isAnnotationPresent(GET.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(GET.class).value()), RestMethodTypes.GET);
        if (method.isAnnotationPresent(POST.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(POST.class).value()), RestMethodTypes.POST);
        if (method.isAnnotationPresent(PUT.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(PUT.class).value()), RestMethodTypes.PUT);
        if (method.isAnnotationPresent(DELETE.class))
            return new RestMethodData(getUrlFromDomain(domain,
                    method.getAnnotation(DELETE.class).value()), RestMethodTypes.DELETE);
        return new RestMethodData();
    }
}
