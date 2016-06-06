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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.LinqUtils.any;
import static java.lang.String.format;
import static java.lang.reflect.Modifier.isStatic;
import static java.util.Arrays.asList;

/**
 * Created by roman.i on 25.09.2014.
 */
public final class ReflectionUtils {
    private ReflectionUtils() { }
    public static boolean isClass(Field field, Class<?> expected) {
        return isClass(field.getType(), expected);
    }

    public static boolean isClass(Class<?> t, Class<?> expected) {
        if (expected == Object.class)
            return true;
        Class<?> type = t;
        while (type != null && type != Object.class)
            if (type == expected) return true;
            else type = type.getSuperclass();
        return false;
    }

    public static boolean isClass(Class<?> type, Class<?>... expected) {
        for (Class<?> expectedType : expected) {
            Class<?> actualType = type;
            if (expectedType == Object.class) return true;
            while (actualType != null && actualType != Object.class)
                if (actualType == expectedType) return true;
                else actualType = actualType.getSuperclass();
        }
        return false;
    }

    public static boolean isInterface(Field field, Class<?> expected) {
        return isInterface(field.getType(), expected);
    }

    public static boolean isInterface(Class<?> type, Class<?> expected) {
        if (type == null || expected == null || type == Object.class)
            return false;
        if (type == expected)
            return true;
        List<Class> interfaces = asList(type.getInterfaces());
        return any(interfaces, i -> isInterface(i, expected)) || isInterface(type.getSuperclass(), expected);
    }

    public static List<Field> getFields(Object obj, Class<?>... types) {
        return getFields(obj, types, Object.class);
    }
    public static List<Field> getFields(Object obj, Class<?>[] types, Class<?>... stopTypes) {
        return LinqUtils.where(getFieldsDeep(obj.getClass(), stopTypes), field -> !isStatic(field.getModifiers()) && isExpectedClass(field, types));
        //return LinqUtils.where(obj.getClass().getDeclaredFields(), field -> !isStatic(field.getModifiers()) && (isClass(field, type) || isInterface(field, type)));
    }

    private static List<Field> getFieldsDeep(Class<?> typr) {
        return getFieldsDeep(typr, Object.class);
    }
    private static List<Field> getFieldsDeep(Class<?> type, Class<?>... types) {
        if (asList(types).contains(type))
            return new ArrayList<>();
        List<Field> result = new ArrayList<>(asList(type.getDeclaredFields()));
        result.addAll(getFieldsDeep(type.getSuperclass(), types));
        return result;
    }

    public static List<Field> getStaticFields(Class<?> obj, Class<?>[] types) {
        //return LinqUtils.where(getFieldsDeep(obj.getClass(), stopTypes), field -> isStatic(field.getModifiers()) && (isExpectedClass(field, types)));
        return LinqUtils.where(obj.getDeclaredFields(), field -> isStatic(field.getModifiers()) && (isExpectedClass(field, types)));
    }

    public static <T> T getFirstField(Object obj, Class<?>... types) {
        return (T) getValueField(LinqUtils.first(obj.getClass().getDeclaredFields(), field -> isExpectedClass(field, types)), obj);
    }
    public static boolean isExpectedClass(Field field, Class<?>... types) {
        for (Class<?> type : types)
            if (isClass(field, type) || isInterface(field, type))
                return true;
        return false;
    }

    public static Object getValueField(Field field, Object obj) {
        field.setAccessible(true);
        try {
            return field.get(obj);
        } catch (Exception ex) {
            throw new RuntimeException(format("Can't get field '%s' value", field.getName()));
        }
    }
}