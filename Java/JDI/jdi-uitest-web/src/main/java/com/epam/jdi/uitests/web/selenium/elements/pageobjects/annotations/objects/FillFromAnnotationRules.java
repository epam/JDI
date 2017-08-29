package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import java.lang.reflect.Field;

import static com.epam.commons.ReflectionUtils.isInterface;

/**
 * Created by 12345 on 17.05.2016.
 */
public class FillFromAnnotationRules {
    public static boolean fieldHasAnnotation(Field field, Class annotationClass, Class interfaceClass) {
        return isInterface(field, interfaceClass) && field.isAnnotationPresent(annotationClass);
    }
}
