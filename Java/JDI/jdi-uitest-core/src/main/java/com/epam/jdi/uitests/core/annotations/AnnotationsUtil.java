package com.epam.jdi.uitests.core.annotations;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */

import com.epam.jdi.uitests.core.annotations.functions.CancelButton;
import com.epam.jdi.uitests.core.annotations.functions.CloseButton;
import com.epam.jdi.uitests.core.annotations.functions.Functions;
import com.epam.jdi.uitests.core.annotations.functions.OkButton;

import java.lang.reflect.Field;

/**
 * Created by roman.i on 25.09.2014.
 */
public class AnnotationsUtil {
    protected AnnotationsUtil() {
    }

    public static String getElementName(Field field) {
        return field.isAnnotationPresent(Name.class)
            ? field.getAnnotation(Name.class).value()
            : splitCamelCase(field.getName());
    }

    public static Functions getFunction(Field field) {
        if (field.isAnnotationPresent(OkButton.class))
            return Functions.OK_BUTTON;
        if (field.isAnnotationPresent(CloseButton.class))
            return Functions.CLOSE_BUTTON;
        if (field.isAnnotationPresent(CancelButton.class))
            return Functions.CANCEL_BUTTON;
        return Functions.NONE;
    }

    private static String splitCamelCase(String camel) {
        String result = (camel.charAt(0) + "").toUpperCase();
        for (int i = 1; i < camel.length() - 1; i++)
            result += ((isCapital(camel.charAt(i)) && !isCapital(camel.charAt(i - 1))) ? " " : "") + camel.charAt(i);
        return result + camel.charAt(camel.length() - 1);
    }

    private static boolean isCapital(char ch) {
        return 'A' < ch && ch < 'Z';
    }

}