package com.epam.commons;
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.LinqUtils.toList;
import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static java.lang.String.format;
import static java.util.Arrays.asList;

/**
 * Created by roman.i on 30.09.2014.
 */
public final class PrintUtils {
    private PrintUtils() {
    }

    public static String print(Collection<String> list) {
        return print(list, ", ", "%s");
    }

    public static String print(Collection<String> list, String separator) {
        return print(list, separator, "%s");
    }

    public static <T extends Enum> String printEnum(List<T> enums) {
        return (enums != null) ? String.join(", ", select(enums, el -> format("%s", el))) : "";
    }

    public static String print(Collection<String> list, String separator, String format) {
        return (list != null) ? String.join(separator, select(list, el -> format(format, el))) : "";
    }

    public static String print(String[] list) {
        return print(list, ", ", "%s");
    }

    public static String print(String[] list, String separator) {
        return print(list, separator, "%s");
    }

    public static String print(String[] list, String separator, String format) {
        return print(asList(list), separator, format);
    }
    public static <T> String print(Map<String, T> map, String separator, String format) {
        return print(toList(map, (k, v) -> MessageFormat.format(format, k, v)), separator, "%s");
    }
    public static <T> String print(Map<String, T> map, String separator) {
         return print(map, separator, "{0}: {1}");
    }
    public static <T> String print(Map<String, T> map) {
        return print(map, "; ", "{0}: {1}");
    }

    public static String print(int[] list) {
        return print(list, ", ", "%s");
    }

    public static String print(int[] list, String separator) {
        return print(list, separator, "%s");
    }

    public static String print(int[] list, String separator, String format) {
        List<String> result = new ArrayList<>();
        for (int i : list)
            result.add(Integer.toString(i));
        return print(result, separator, format);
    }

    public static String print(boolean[] list) {
        return print(list, ", ", "%s");
    }

    public static String print(boolean[] list, String separator) {
        return print(list, separator, "%s");
    }

    public static String print(boolean[] list, String separator, String format) {
        List<String> result = new ArrayList<>();
        for (boolean i : list)
            result.add(Boolean.toString(i));
        return print(result, separator, format);
    }

    public static String printFields(Object obj) {
        return printFields(obj, "; ");
    }

    public static String printFields(Object obj, String separator) {
        String className = obj.getClass().getSimpleName();
        String params = print(select(getFields(obj, String.class),
                field -> format("%s: '%s'", field.getName(), getValueField(field, obj))), separator, "%s");
        return format("%s(%s)", className, params);
    }
}