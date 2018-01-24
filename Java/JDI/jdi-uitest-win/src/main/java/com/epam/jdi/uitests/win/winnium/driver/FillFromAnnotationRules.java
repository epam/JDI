package com.epam.jdi.uitests.win.winnium.driver;

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
