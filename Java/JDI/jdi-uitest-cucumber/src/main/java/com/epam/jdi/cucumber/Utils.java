package com.epam.jdi.cucumber;

import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.web.selenium.elements.WebCascadeInit;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.annotations.AnnotationsUtil.getElementName;
import static com.epam.jdi.uitests.core.interfaces.Application.currentSite;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;
import static java.lang.String.join;
import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.asList;


/**
 * Created by Dmitry_Lebedev1 on 1/13/2016.
 */
public final class Utils {
    private Utils() {
    }

    public static Object getClassField(Class container, String fieldName) {
        Object result;
        try {
            result = getValueField(container.getDeclaredField(fieldName), new Object());
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public static Object getClassField(Object container, String fieldName) {
        Object result;
        try {
            Field f = container.getClass().getDeclaredField(fieldName);
            Object parent = Modifier.isStatic(f.getModifiers()) ? new Object() : container;
            result = getValueField(f, parent);
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public static Object getClassField(String containerName) {
        Class[] containers = {currentPage.getClass(), currentSite};
        Object resultElement = null;
        for (Class i : containers) {
            resultElement = getClassField(i, containerName);
            if (resultElement != null) break;
        }
        return resultElement;
    }

    public static Object getClassField(String containerName, ArrayList<Object> containers, Class expectedClass) {
        Object resultElement = null;
        for (Object i : containers) {
            resultElement = getClassField(i, containerName);
            if (resultElement == null)
                continue;
            if (!expectedClass.isInstance(resultElement))
                resultElement = null;
            if (resultElement != null)
                break;
        }
        return resultElement;
    }

    public static Type getParameterizedTypeForm(Object form) {
        Class<?> clazz = form.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        return parameterizedType.getActualTypeArguments()[0];
    }

    public static Object createFromGSON(String json, Type t) {
        return new Gson().fromJson(json, t);
    }

    public static Object createFromJSON(String json, IForm form) {
        Type t = getParameterizedTypeForm(form);
        try {
            Object result = ((Class) t).newInstance();
            Map<String, String> mJson = new Gson().fromJson(json, Map.class);
            for (Map.Entry<String, String> e : mJson.entrySet()) {
                Field f = result.getClass().getDeclaredField(e.getKey());
                f.setAccessible(true);
                f.set(result, e.getValue());
            }
            return result;
        } catch (Exception ex) { throw exception("Can't create object from Json. Exception: " + ex.getMessage()); }
    }

    public static List<Object> filterCompositeFields(Object o) throws IllegalAccessException {
        List<Object> containers = new ArrayList<>();
        for (Field f : o instanceof Class ? ((Class) o).getFields() : o.getClass().getFields()) {
            Object fData = getValueField(f, o);
            if (fData instanceof IComposite && !f.getName().equals("currentPage")) {
                containers.add(fData);
                containers.addAll(filterCompositeFields(fData));
            }
        }
        return containers;
    }

    // Find all composite elements on root pageobject,  then find one named element. If composite elements contains
    // more then one named elements, use first find.
    public static Object getClassFieldAnyway(String fieldName, Class expectedClass) throws IllegalAccessException {
        return getClassField(fieldName, new ArrayList<>(filterCompositeFields(currentSite)), expectedClass);
    }

    public static Object getClassFieldAnyway(String fieldName) throws IllegalAccessException {
        return getClassField(fieldName, new ArrayList<>(filterCompositeFields(currentSite)), IElement.class);
    }
    ////////////

    public static <T> T getElementByName(String... names) {
        return getElementByName(currentPage, names);
    }
    public static <T> T getElementByName(String name) {
        return getElementByName(currentPage, name);
    }
    public static <T> T getElementByName(Object location, String... names) {
        return getElementByName(location, join(".", names));
    }
    public static <T> T getElementByName(Object location, String name) {
        int index = name.indexOf(".");
        return index == -1
                ? getElementByNameSingle(name, location)
                : getElementByNameChained(name.substring(0, index),
                    name.substring(index+1), location);
    }
    private static <T> T getElementByNameSingle(String name, Object page) {
        try {
            List<Field> fields = getAllFields(page);
            Field expectedField = first(fields,
                    f -> getElementName(f).equals(name) || f.getName().equals(name));
            if (expectedField !=null)
                return (T) getValueField(expectedField, page);
            for(Field f: fields) {
                T result = getElementByNameSingle(name, getObjectFromField(f, page));
                if (result != null)
                    return result;
            }
        } catch (Exception ex) {
            throw  exception("Can't get element by Name: " + name);
        }
        return null;
    }
    private static Object getObjectFromField(Field f, Object clazz) {
        try {
            return getValueField(f, clazz);
        } catch (Exception ex) { throw exception("Can't get Object"); }
    }
    private static List<Field> getAllFields(Object page) {
        WebCascadeInit w = new WebCascadeInit();
        return page.getClass().getName().equals("java.lang.Class")
                ? getFields(asList(((Class) page).getDeclaredFields()),
                w.decorators(), f -> isStatic(f.getModifiers()))
                : getFields(page, w.decorators(), w.stopTypes());
    }
    private static <T> T getElementByNameChained(String name, String path, Object page) {
        try {
            List<Field> fields = getAllFields(page);
            Field expectedField = first(fields,
                    f -> getElementName(f).equals(name) || f.getName().equals(name));
            if (expectedField != null) {
                Object fieldAsObject = getValueField(expectedField, page);
                return (T) (path.equals("")
                        ? fieldAsObject
                        :getElementByName(fieldAsObject, path));
            }
            for(Field f: fields) {
                T result = getElementByName(getObjectFromField(f, page), path);
                if (result != null)
                    return path.equals("")
                            ? result
                            : getElementByName(result, path);
            }
        } catch (Exception ex) {
            throw  exception("Can't get element by Name: " + name);
        }
        return null;
    }

}
