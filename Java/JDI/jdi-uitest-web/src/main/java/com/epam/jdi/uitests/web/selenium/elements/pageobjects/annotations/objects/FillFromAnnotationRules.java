package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.Search;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.BiConsumer;

import static com.epam.commons.ReflectionUtils.isInterface;
import static java.util.Arrays.asList;

/**
 * Created by 12345 on 17.05.2016.
 */
public class FillFromAnnotationRules {

    public static boolean fieldHasAnnotation(Field field, Class annotationClass, Class interfaceClass) {
        boolean isAnnotation = field.isAnnotationPresent(annotationClass);
        return isAnnotation && isInterface(field, interfaceClass);
    }
}
