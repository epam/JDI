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
import java.util.List;

import static com.epam.commons.TryCatchUtil.tryGetResult;
import static java.util.Arrays.asList;

/**
 * Created by Roman_Iovlev on 7/8/2015.
 */
public final class EnumUtils {

    private EnumUtils() {
    }

    public static String getEnumValue(Enum enumWithValue) {
        Class<?> type = enumWithValue.getClass();
        Field[] fields = type.getDeclaredFields();
        Field field;
        switch (fields.length) {
            case 0:
                return enumWithValue.toString();
            case 1:
                field = fields[0];
                break;
            default:
                try {
                    field = type.getField("value");
                } catch (NoSuchFieldException ex) {
                    return enumWithValue.toString();
                }
                break;
        }
        return tryGetResult(() -> field.get(enumWithValue).toString());
    }

    public static <T extends Enum> List<T> getAllEnumValues(Class<T> enumValue) {
        return asList(getAllEnumValuesAsArray(enumValue));
    }

    public static <T extends Enum> T[] getAllEnumValuesAsArray(Class<T> enumValue) {
        return enumValue.getEnumConstants();
    }

    public static <T extends Enum> List<String> getAllEnumNames(Class<T> enumValue) {
        return LinqUtils.select(getAllEnumValuesAsArray(enumValue), EnumUtils::getEnumValue);
    }

    public static <T extends Enum> String[] getAllEnumNamesAsArray(Class<T> enumValue) {
        return LinqUtils.toStringArray(getAllEnumNames(enumValue));
    }
}