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

import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.ReflectionUtils.*;
import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

/**
 * Created by roman.i on 30.09.2014.
 */
public final class PrintUtils {
    private PrintUtils() {
    }

    public static MapArray<String, String> getMapFromObject(Object obj) {
        if (obj == null)
            return new MapArray<>();
        return new MapArray<>(getFields(obj, Object.class),
            AnnotationsUtil::getElementName,
            field -> {
                Object value = getValueField(field, obj);
                if (value == null)
                    return null;
                if (isClass(value.getClass(), String.class, Integer.class, Boolean.class))
                    return value.toString();
                if (isClass(value.getClass(), Enum.class))
                    return getEnumValue((Enum) value);
                return null;
                // TODO
                /*if (field.isAnnotationPresent(Complex.class))
                    printObject(value)*/
            });
    }

    public static String printObjectAsArray(Object array) {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i <= getLength(array); i++)
            elements.add(get(array, i).toString());
        return print(elements);
    }
}