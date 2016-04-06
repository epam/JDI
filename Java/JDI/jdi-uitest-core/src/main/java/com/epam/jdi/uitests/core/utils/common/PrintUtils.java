package com.epam.jdi.uitests.core.utils.common;
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

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.annotations.AnnotationsUtil;
import com.epam.jdi.uitests.core.annotations.Complex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.ReflectionUtils.*;
import static java.lang.Integer.parseInt;
import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

/**
 * Created by roman.i on 30.09.2014.
 */
public final class PrintUtils {
    private PrintUtils() {
    }

    private static String printObject(Object obj) {
        List<String> result = new ArrayList<>();
        for (Field field : getFields(obj, Object.class)) {
            Object value = getValueField(field, obj);
            String strValue = null;
            if (value == null)
                strValue = "#NULL#";
            else if (isClass(value.getClass(), String.class))
                strValue = (String) value;
            else if (isClass(value.getClass(), Enum.class))
                strValue = value.toString();
            else if (field.isAnnotationPresent(Complex.class))
                strValue = "#(#" + printObject(value) + "#)#";
            if (strValue != null)
                result.add(String.format("%s#:#%s", AnnotationsUtil.getElementName(field), strValue));
        }
        return print(result, "#;#", "%s");
    }

    public static MapArray<String, String> objToSetValue(Object obj) {
        return (obj == null)
                ? new MapArray<>()
                : parseObjectAsString(printObject(obj));
    }

    public static String processValue(String input, List<String> values) {
        if (input.equals("#NULL#"))
            return null;
        return input.matches("#VAL\\d*")
                ? values.get(parseInt(input.substring(4)) - 1)
                : input;
    }

    public static MapArray<String, String> parseObjectAsString(String objString) {
        if (objString == null)
            return null;
        MapArray<String, String> result = new MapArray<>();
        List<String> values = new ArrayList<>();
        int i = 1;
        String str = objString;
        int from;
        while ((from = str.indexOf("#(#")) > 0) {
            int to = str.indexOf("#)#");
            values.add(str.substring(from + 3, to));
            str = str.replaceAll("#\\(#.*#\\)#", "#VAL" + i++);
        }
        String[] fields = str.split("#;#");
        for (String field : fields) {
            String[] splitField = field.split("#:#");
            if (splitField.length == 2)
                result.add(splitField[0], processValue(splitField[1], values));
        }
        return result;
    }

    public static String printObjectAsArray(Object array) {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i <= getLength(array); i++)
            elements.add(get(array, i).toString());
        return print(elements);
    }
}