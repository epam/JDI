package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.base.IComposite;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.google.gson.Gson;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * Created by Dmitry_Lebedev1 on 1/13/2016.
 */
public final class Utils {
    private Utils() { }

    public static Object getClassField(Class container, String fieldName) {
        Object result;
        try {
            result = container.getDeclaredField(fieldName).get(new Object());
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
            result = f.get(parent);
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    public static Object getClassField(String containerName) {
        Class[] containers = {WebPage.currentPage.getClass(), WebSite.currentSite};
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

    public static Object createFromJSON(String json, Type t) throws Exception {
        Object result = ((Class) t).newInstance();
        Map<String, String> mJson = new Gson().fromJson(json, Map.class);
        for (Map.Entry<String, String> e : mJson.entrySet()) {
            Field f = result.getClass().getDeclaredField(e.getKey());
            f.setAccessible(true);
            f.set(result, e.getValue());
        }
        return result;
    }

    public static void processForm(String formName, String json, FormActions action) throws Exception {
        IForm form = (IForm) getClassField(WebPage.currentPage, formName);
        Object entity = createFromJSON(json, getParameterizedTypeForm(form));
        switch (action) {
            case SUBMIT:
                form.submit(entity);
                break;
            case FILL:
                form.fill(entity);
                break;
            case CHECK:
                form.check(entity);
                break;
        }
    }

    public static List<Object> filterCompositeFields(Object o) throws IllegalAccessException {
        List<Object> containers = new ArrayList<>();
        for (Field f : o instanceof Class ? ((Class) o).getFields() : o.getClass().getFields()) {
                Object fData = f.get(o);
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
        return getClassField(fieldName, new ArrayList<>(filterCompositeFields(WebSite.currentSite)), expectedClass);
    }

    public static Object getClassFieldAnyway(String fieldName) throws IllegalAccessException {
        return getClassField(fieldName, new ArrayList<>(filterCompositeFields(WebSite.currentSite)), IElement.class);
    }
}
