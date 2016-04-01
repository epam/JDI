package com.epam.web.matcher.junit;
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


import com.epam.commons.linqinterfaces.JAction;
import com.epam.commons.map.MapArray;
import com.epam.web.matcher.base.BaseMatcher;
import com.epam.web.matcher.base.DoScreen;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static com.epam.web.matcher.base.DoScreen.SCREEN_ON_FAIL;


/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public final class Assert {
    private Assert() { }
    private static DoScreen doScreen = SCREEN_ON_FAIL;

    private static BaseMatcher matcher = new Check().doScreenshot(doScreen);
    public static void setMatcher(BaseMatcher matcher) {
        Assert.matcher = matcher;
    }

    public static BaseMatcher ignoreCase() {
        return matcher.ignoreCase();
    }

    public static RuntimeException exception(String msg, Object... args) {
        return matcher.exception(msg, args);
    }

    public static <T> void areEquals(T actual, T expected, String failMessage) {
        matcher.areEquals(actual, expected, failMessage);
    }

    public static <T> void areEquals(T actual, T expected) {
        matcher.areEquals(actual, expected);
    }

    public static <T> void assertEquals(T actual, T expected, String failMessage) {
        matcher.areEquals(actual, expected, failMessage);
    }

    public static <T> void assertEquals(T actual, T expected) {
        matcher.areEquals(actual, expected);
    }

    public static void matches(String actual, String regEx, String failMessage) {
        matcher.matches(actual, regEx, failMessage);
    }

    public static void matches(String actual, String regEx) {
        matcher.matches(actual, regEx);
    }

    public static void contains(String actual, String expected, String failMessage) {
        matcher.contains(actual, expected, failMessage);
    }

    public static void contains(String actual, String expected) {
        matcher.contains(actual, expected);
    }

    public static void assertContains(String actual, String expected, String failMessage) {
        matcher.contains(actual, expected, failMessage);
    }

    public static void assertContains(String actual, String expected) {
        matcher.contains(actual, expected);
    }

    public static void isTrue(Boolean condition, String failMessage) {
        matcher.isTrue(condition, failMessage);
    }

    public static void isTrue(Boolean condition) {
        matcher.isTrue(condition);
    }

    public static void assertTrue(Boolean condition, String failMessage) {
        isTrue(condition, failMessage);
    }

    public static void assertTrue(Boolean condition) {
        isTrue(condition);
    }

    public static void isFalse(Boolean condition, String failMessage) {
        matcher.isFalse(condition, failMessage);
    }

    public static void isFalse(Boolean condition) {
        matcher.isFalse(condition);
    }

    public static void assertFalse(Boolean condition, String failMessage) {
        isFalse(condition, failMessage);
    }

    public static void assertFalse(Boolean condition) {
        isFalse(condition);
    }

    public static void isEmpty(Object obj, String failMessage) {
        matcher.isEmpty(obj, failMessage);
    }

    public static void isEmpty(Object obj) {
        matcher.isEmpty(obj);
    }

    public static void isNotEmpty(Object obj, String failMessage) {
        matcher.isNotEmpty(obj, failMessage);
    }

    public static void isNotEmpty(Object obj) {
        matcher.isNotEmpty(obj);
    }

    public static <T> void areSame(T actual, T expected, String failMessage) {
        matcher.areSame(actual, expected, failMessage);
    }

    public static <T> void areSame(T actual, T expected) {
        matcher.areSame(actual, expected);
    }

    public static <T> void assertSame(T actual, T expected, String failMessage) {
        matcher.areSame(actual, expected, failMessage);
    }

    public static <T> void assertSame(T actual, T expected) {
        matcher.areSame(actual, expected);
    }

    public static <T> void areDifferent(T actual, T expected, String failMessage) {
        matcher.areDifferent(actual, expected, failMessage);
    }

    public static <T> void areDifferent(T actual, T expected) {
        matcher.areDifferent(actual, expected);
    }

    public static <T> void assertNotSame(T actual, T expected, String failMessage) {
        matcher.areDifferent(actual, expected, failMessage);
    }

    public static <T> void assertNotSame(T actual, T expected) {
        matcher.areDifferent(actual, expected);
    }

    public static <T> void listEquals(Collection<T> actual, Collection<T> expected, String failMessage) {
        matcher.listEquals(actual, expected, failMessage);
    }

    public static <T> void listEquals(Collection<T> actual, Collection<T> expected) {
        matcher.listEquals(actual, expected);
    }

    public static <T> void arrayEquals(T actual, T expected, String failMessage) {
        matcher.arrayEquals(actual, expected, failMessage);
    }

    public static <T> void arrayEquals(T actual, T expected) {
        matcher.arrayEquals(actual, expected);
    }

    public static <T> void entityIncludeMapArray(MapArray<String, String> actual, T entity, String failMessage) {
        matcher.entityIncludeMapArray(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMapArray(MapArray<String, String> actual, T entity) {
        matcher.entityIncludeMapArray(actual, entity);
    }

    public static <T> void entityEqualsToMapArray(MapArray<String, String> actual, T entity, String failMessage) {
        matcher.entityEqualsToMapArray(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMapArray(MapArray<String, String> actual, T entity) {
        matcher.entityEqualsToMapArray(actual, entity);
    }

    public static <T> void entityIncludeMap(Map<String, String> actual, T entity, String failMessage) {
        matcher.entityIncludeMap(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMap(Map<String, String> actual, T entity) {
        matcher.entityIncludeMap(actual, entity);
    }

    public static <T> void entityEqualsToMap(Map<String, String> actual, T entity, String failMessage) {
        matcher.entityEqualsToMap(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMap(Map<String, String> actual, T entity) {
        matcher.entityEqualsToMap(actual, entity);
    }

    public static void isSortedByAsc(int[] array, String failMessage) {
        matcher.isSortedByAsc(array, failMessage);
    }

    public static void isSortedByAsc(int[] array) {
        matcher.isSortedByAsc(array);
    }

    public static void isSortedByDesc(int[] array, String failMessage) {
        matcher.isSortedByDesc(array, failMessage);
    }

    public static void isSortedByDesc(int[] array) {
        matcher.isSortedByDesc(array);
    }

    public static void isSortedByAsc(List<Integer> array, String failMessage) {
        matcher.isSortedByAsc(array, failMessage);
    }

    public static void isSortedByAsc(List<Integer> array) {
        matcher.isSortedByAsc(array);
    }

    public static void isSortedByDesc(List<Integer> array, String failMessage) {
        matcher.isSortedByDesc(array, failMessage);
    }

    public static void isSortedByDesc(List<Integer> array) {
        matcher.isSortedByDesc(array);
    }

    public static BaseMatcher.ListChecker eachElementOf(List<Object> list) {
        return matcher.eachElementOf(list);
    }

    public static BaseMatcher.ListChecker eachElementOf(Object[] array) {
        return matcher.eachElementOf(array);
    }

    public static BaseMatcher.ListChecker assertEach(List<Object> list) {
        return eachElementOf(list);
    }

    public static BaseMatcher.ListChecker assertEach(Object[] array) {
        return eachElementOf(array);
    }

    public static <T> void areEquals(Supplier<T> actual, T expected, String failMessage) {
        matcher.areEquals(actual, expected, failMessage);
    }

    public static <T> void areEquals(Supplier<T> actual, T expected) {
        matcher.areEquals(actual, expected);
    }
    public static <T> void assertEquals(Supplier<T> actual, T expected, String failMessage) {
        matcher.areEquals(actual, expected, failMessage);
    }

    public static <T> void assertEquals(Supplier<T> actual, T expected) {
        matcher.areEquals(actual, expected);
    }

    public static void matches(Supplier<String> actual, String regEx, String failMessage) {
        matcher.matches(actual, regEx, failMessage);
    }

    public static void matches(Supplier<String> actual, String regEx) {
        matcher.matches(actual, regEx);
    }

    public static void contains(Supplier<String> actual, String expected, String failMessage) {
        matcher.contains(actual, expected, failMessage);
    }

    public static void contains(Supplier<String> actual, String expected) {
        matcher.contains(actual, expected);
    }

    public static void assertContains(Supplier<String> actual, String expected, String failMessage) {
        matcher.contains(actual, expected, failMessage);
    }

    public static void assertContains(Supplier<String> actual, String expected) {
        matcher.contains(actual, expected);
    }

    public static void isTrue(BooleanSupplier condition, String failMessage) {
        matcher.isTrue(condition, failMessage);
    }

    public static void isTrue(BooleanSupplier condition) {
        matcher.isTrue(condition);
    }
    public static void assertTrue(BooleanSupplier condition, String failMessage) {
        matcher.isTrue(condition, failMessage);
    }

    public static void assertTrue(BooleanSupplier condition) {
        matcher.isTrue(condition);
    }

    public static void isFalse(BooleanSupplier condition, String failMessage) {
        matcher.isFalse(condition, failMessage);
    }

    public static void isFalse(BooleanSupplier condition) {
        matcher.isFalse(condition);
    }
    public static void assertFalse(BooleanSupplier condition, String failMessage) {
        matcher.isFalse(condition, failMessage);
    }

    public static void assertFalse(BooleanSupplier condition) {
        matcher.isFalse(condition);
    }

    public static void isEmpty(Supplier<Object> obj, String failMessage) {
        matcher.isEmpty(obj, failMessage);
    }

    public static void isEmpty(Supplier<Object> obj) {
        matcher.isEmpty(obj);
    }

    public static void isNotEmpty(Supplier<Object> obj, String failMessage) {
        matcher.isNotEmpty(obj, failMessage);
    }

    public static void isNotEmpty(Supplier<Object> obj) {
        matcher.isNotEmpty(obj);
    }

    public static <T> void areSame(Supplier<T> actual, T expected, String failMessage) {
        matcher.areSame(actual, expected, failMessage);
    }

    public static <T> void areSame(Supplier<T> actual, T expected) {
        matcher.areSame(actual, expected);
    }

    public static <T> void assertSame(Supplier<T> actual, T expected, String failMessage) {
        matcher.areSame(actual, expected, failMessage);
    }

    public static <T> void assertSame(Supplier<T> actual, T expected) {
        matcher.areSame(actual, expected);
    }

    public static <T> void areDifferent(Supplier<T> actual, T expected, String failMessage) {
        matcher.areDifferent(actual, expected, failMessage);
    }

    public static <T> void areDifferent(Supplier<T> actual, T expected) {
        matcher.areDifferent(actual, expected);
    }

    public static void assertNotSame(Supplier<Object> obj, Object obj2, String failMessage) {
        areDifferent(obj, obj2, failMessage);
    }

    public static void assertNotSame(Supplier<Object> obj, Object obj2) {
        areDifferent(obj, obj2);
    }

    public static void throwException(String actionName, JAction action, Class<Exception> exceptionClass, String exceptionText) {
        matcher.throwException(actionName, action, exceptionClass, exceptionText);
    }

    public static void throwException(String actionName, JAction action, String exceptionText) {
        matcher.throwException(actionName, action, exceptionText);
    }
    public static void throwException(String actionName, JAction action, Class<Exception> exceptionClass) {
        matcher.throwException(actionName, action, exceptionClass);
    }
    public static void throwException(JAction action, Class<Exception> exceptionClass, String exceptionText) {
        matcher.throwException(action, exceptionClass, exceptionText);
    }
    public static void throwException(JAction action, String exceptionText) {
        matcher.throwException(action, exceptionText);
    }
    public static void throwException(JAction action, Class<Exception> exceptionClass) {
        matcher.throwException(action, exceptionClass);
    }
    public static void hasNoExceptions(String actionName, JAction action) {
        matcher.hasNoExceptions(actionName, action);
    }
    public static void hasNoExceptions(JAction action) {
        matcher.hasNoExceptions(action);
    }

    public static <T> void listEquals(Supplier<Collection<T>> actual, Collection<T> expected, String failMessage) {
        matcher.listEquals(actual, expected, failMessage);
    }

    public static <T> void listEquals(Supplier<Collection<T>> actual, Collection<T> expected) {
        matcher.listEquals(actual, expected);
    }

    public static <T> void arrayEquals(Supplier<T> actual, T expected, String failMessage) {
        matcher.arrayEquals(actual, expected, failMessage);
    }

    public static <T> void arrayEquals(Supplier<T> actual, T expected) {
        matcher.arrayEquals(actual, expected);
    }


    public static <T> void entityIncludeMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage) {
        matcher.entityIncludeMapArray(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMapArray(Supplier<MapArray<String, String>> actual, T entity) {
        matcher.entityIncludeMapArray(actual, entity);
    }

    public static <T> void entityEqualsToMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage) {
        matcher.entityEqualsToMapArray(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMapArray(Supplier<MapArray<String, String>> actual, T entity) {
        matcher.entityEqualsToMapArray(actual, entity);
    }

    public static <T> void entityIncludeMap(Supplier<Map<String, String>> actual, T entity, String failMessage) {
        matcher.entityIncludeMap(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMap(Supplier<Map<String, String>> actual, T entity) {
        matcher.entityIncludeMap(actual, entity);
    }

    public static <T> void entityEqualsToMap(Supplier<Map<String, String>> actual, T entity, String failMessage) {
        matcher.entityEqualsToMap(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMap(Supplier<Map<String, String>> actual, T entity) {
        matcher.entityEqualsToMap(actual, entity);
    }
}