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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static com.epam.web.matcher.base.DoScreen.DO_SCREEN_ALWAYS;


/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public final class ScreenAssert {
    private ScreenAssert() { }
    private static BaseMatcher getAssert() {
        return new Check().doScreenshot(DO_SCREEN_ALWAYS);
    }

    public static BaseMatcher ignoreCase() {
        return getAssert().ignoreCase();
    }

    public static RuntimeException exception(String msg, Object... args) {
        return getAssert().exception(msg, args);
    }

    public static <T> void areEquals(T actual, T expected, String failMessage) {
        getAssert().areEquals(actual, expected, failMessage);
    }

    public static <T> void areEquals(T actual, T expected) {
        getAssert().areEquals(actual, expected);
    }

    public static <T> void assertEquals(T actual, T expected, String failMessage) {
        getAssert().areEquals(actual, expected, failMessage);
    }

    public static <T> void assertEquals(T actual, T expected) {
        getAssert().areEquals(actual, expected);
    }

    public static void matches(String actual, String regEx, String failMessage) {
        getAssert().matches(actual, regEx, failMessage);
    }

    public static void matches(String actual, String regEx) {
        getAssert().matches(actual, regEx);
    }

    public static void contains(String actual, String expected, String failMessage) {
        getAssert().contains(actual, expected, failMessage);
    }

    public static void contains(String actual, String expected) {
        getAssert().contains(actual, expected);
    }

    public static void assertContains(String actual, String expected, String failMessage) {
        getAssert().contains(actual, expected, failMessage);
    }

    public static void assertContains(String actual, String expected) {
        getAssert().contains(actual, expected);
    }

    public static void isTrue(Boolean condition, String failMessage) {
        getAssert().isTrue(condition, failMessage);
    }

    public static void isTrue(Boolean condition) {
        getAssert().isTrue(condition);
    }

    public static void assertTrue(Boolean condition, String failMessage) {
        isTrue(condition, failMessage);
    }

    public static void assertTrue(Boolean condition) {
        isTrue(condition);
    }

    public static void isFalse(Boolean condition, String failMessage) {
        getAssert().isFalse(condition, failMessage);
    }

    public static void isFalse(Boolean condition) {
        getAssert().isFalse(condition);
    }

    public static void assertFalse(Boolean condition, String failMessage) {
        isFalse(condition, failMessage);
    }

    public static void assertFalse(Boolean condition) {
        isFalse(condition);
    }

    public static void isEmpty(Object obj, String failMessage) {
        getAssert().isEmpty(obj, failMessage);
    }

    public static void isEmpty(Object obj) {
        getAssert().isEmpty(obj);
    }

    public static void isNotEmpty(Object obj, String failMessage) {
        getAssert().isNotEmpty(obj, failMessage);
    }

    public static void isNotEmpty(Object obj) {
        getAssert().isNotEmpty(obj);
    }

    public static <T> void areSame(T actual, T expected, String failMessage) {
        getAssert().areSame(actual, expected, failMessage);
    }

    public static <T> void areSame(T actual, T expected) {
        getAssert().areSame(actual, expected);
    }

    public static <T> void assertSame(T actual, T expected, String failMessage) {
        getAssert().areSame(actual, expected, failMessage);
    }

    public static <T> void assertSame(T actual, T expected) {
        getAssert().areSame(actual, expected);
    }

    public static <T> void areDifferent(T actual, T expected, String failMessage) {
        getAssert().areDifferent(actual, expected, failMessage);
    }

    public static <T> void areDifferent(T actual, T expected) {
        getAssert().areDifferent(actual, expected);
    }

    public static <T> void assertNotSame(T actual, T expected, String failMessage) {
        getAssert().areDifferent(actual, expected, failMessage);
    }

    public static <T> void assertNotSame(T actual, T expected) {
        getAssert().areDifferent(actual, expected);
    }

    public static <T> void listEquals(Collection<T> actual, Collection<T> expected, String failMessage) {
        getAssert().listEquals(actual, expected, failMessage);
    }

    public static <T> void listEquals(Collection<T> actual, Collection<T> expected) {
        getAssert().listEquals(actual, expected);
    }

    public static <T> void arrayEquals(T actual, T expected, String failMessage) {
        getAssert().arrayEquals(actual, expected, failMessage);
    }

    public static <T> void arrayEquals(T actual, T expected) {
        getAssert().arrayEquals(actual, expected);
    }

    public static <T> void entityIncludeMapArray(MapArray<String, String> actual, T entity, String failMessage) {
        getAssert().entityIncludeMapArray(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMapArray(MapArray<String, String> actual, T entity) {
        getAssert().entityIncludeMapArray(actual, entity);
    }

    public static <T> void entityEqualsToMapArray(MapArray<String, String> actual, T entity, String failMessage) {
        getAssert().entityEqualsToMapArray(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMapArray(MapArray<String, String> actual, T entity) {
        getAssert().entityEqualsToMapArray(actual, entity);
    }

    public static <T> void entityIncludeMap(Map<String, String> actual, T entity, String failMessage) {
        getAssert().entityIncludeMap(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMap(Map<String, String> actual, T entity) {
        getAssert().entityIncludeMap(actual, entity);
    }

    public static <T> void entityEqualsToMap(Map<String, String> actual, T entity, String failMessage) {
        getAssert().entityEqualsToMap(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMap(Map<String, String> actual, T entity) {
        getAssert().entityEqualsToMap(actual, entity);
    }

    public static void isSortedByAsc(int[] array, String failMessage) {
        getAssert().isSortedByAsc(array, failMessage);
    }

    public static void isSortedByAsc(int[] array) {
        getAssert().isSortedByAsc(array);
    }

    public static void isSortedByDesc(int[] array, String failMessage) {
        getAssert().isSortedByDesc(array, failMessage);
    }

    public static void isSortedByDesc(int[] array) {
        getAssert().isSortedByDesc(array);
    }

    public static void isSortedByAsc(List<Integer> array, String failMessage) {
        getAssert().isSortedByAsc(array, failMessage);
    }

    public static void isSortedByAsc(List<Integer> array) {
        getAssert().isSortedByAsc(array);
    }

    public static void isSortedByDesc(List<Integer> array, String failMessage) {
        getAssert().isSortedByDesc(array, failMessage);
    }

    public static void isSortedByDesc(List<Integer> array) {
        getAssert().isSortedByDesc(array);
    }

    public static BaseMatcher.ListChecker eachElementOf(List<Object> list) {
        return getAssert().eachElementOf(list);
    }

    public static BaseMatcher.ListChecker eachElementOf(Object[] array) {
        return getAssert().eachElementOf(array);
    }

    public static BaseMatcher.ListChecker assertEach(List<Object> list) {
        return eachElementOf(list);
    }

    public static BaseMatcher.ListChecker assertEach(Object[] array) {
        return eachElementOf(array);
    }

    public static <T> void areEquals(Supplier<T> actual, T expected, String failMessage) {
        getAssert().areEquals(actual, expected, failMessage);
    }

    public static <T> void areEquals(Supplier<T> actual, T expected) {
        getAssert().areEquals(actual, expected);
    }
    public static <T> void assertEquals(Supplier<T> actual, T expected, String failMessage) {
        getAssert().areEquals(actual, expected, failMessage);
    }

    public static <T> void assertEquals(Supplier<T> actual, T expected) {
        getAssert().areEquals(actual, expected);
    }

    public static void matches(Supplier<String> actual, String regEx, String failMessage) {
        getAssert().matches(actual, regEx, failMessage);
    }

    public static void matches(Supplier<String> actual, String regEx) {
        getAssert().matches(actual, regEx);
    }

    public static void contains(Supplier<String> actual, String expected, String failMessage) {
        getAssert().contains(actual, expected, failMessage);
    }

    public static void contains(Supplier<String> actual, String expected) {
        getAssert().contains(actual, expected);
    }

    public static void assertContains(Supplier<String> actual, String expected, String failMessage) {
        getAssert().contains(actual, expected, failMessage);
    }

    public static void assertContains(Supplier<String> actual, String expected) {
        getAssert().contains(actual, expected);
    }

    public static void isTrue(BooleanSupplier condition, String failMessage) {
        getAssert().isTrue(condition, failMessage);
    }

    public static void isTrue(BooleanSupplier condition) {
        getAssert().isTrue(condition);
    }
    public static void assertTrue(BooleanSupplier condition, String failMessage) {
        getAssert().isTrue(condition, failMessage);
    }

    public static void assertTrue(BooleanSupplier condition) {
        getAssert().isTrue(condition);
    }

    public static void isFalse(BooleanSupplier condition, String failMessage) {
        getAssert().isFalse(condition, failMessage);
    }

    public static void isFalse(BooleanSupplier condition) {
        getAssert().isFalse(condition);
    }
    public static void assertFalse(BooleanSupplier condition, String failMessage) {
        getAssert().isFalse(condition, failMessage);
    }

    public static void assertFalse(BooleanSupplier condition) {
        getAssert().isFalse(condition);
    }

    public static void isEmpty(Supplier<Object> obj, String failMessage) {
        getAssert().isEmpty(obj, failMessage);
    }

    public static void isEmpty(Supplier<Object> obj) {
        getAssert().isEmpty(obj);
    }

    public static void isNotEmpty(Supplier<Object> obj, String failMessage) {
        getAssert().isNotEmpty(obj, failMessage);
    }

    public static void isNotEmpty(Supplier<Object> obj) {
        getAssert().isNotEmpty(obj);
    }

    public static <T> void areSame(Supplier<T> actual, T expected, String failMessage) {
        getAssert().areSame(actual, expected, failMessage);
    }

    public static <T> void areSame(Supplier<T> actual, T expected) {
        getAssert().areSame(actual, expected);
    }

    public static <T> void assertSame(Supplier<T> actual, T expected, String failMessage) {
        getAssert().areSame(actual, expected, failMessage);
    }

    public static <T> void assertSame(Supplier<T> actual, T expected) {
        getAssert().areSame(actual, expected);
    }

    public static <T> void areDifferent(Supplier<T> actual, T expected, String failMessage) {
        getAssert().areDifferent(actual, expected, failMessage);
    }

    public static <T> void areDifferent(Supplier<T> actual, T expected) {
        getAssert().areDifferent(actual, expected);
    }

    public static void assertNotSame(Supplier<Object> obj, Object obj2, String failMessage) {
        areDifferent(obj, obj2, failMessage);
    }

    public static void assertNotSame(Supplier<Object> obj, Object obj2) {
        areDifferent(obj, obj2);
    }

    public static void throwException(String actionName, JAction action, Class<Exception> exceptionClass, String exceptionText) {
        getAssert().throwException(actionName, action, exceptionClass, exceptionText);
    }

    public static void throwException(String actionName, JAction action, String exceptionText) {
        getAssert().throwException(actionName, action, exceptionText);
    }
    public static void throwException(String actionName, JAction action, Class<Exception> exceptionClass) {
        getAssert().throwException(actionName, action, exceptionClass);
    }
    public static void throwException(JAction action, Class<Exception> exceptionClass, String exceptionText) {
        getAssert().throwException(action, exceptionClass, exceptionText);
    }
    public static void throwException(JAction action, String exceptionText) {
        getAssert().throwException(action, exceptionText);
    }
    public static void throwException(JAction action, Class<Exception> exceptionClass) {
        getAssert().throwException(action, exceptionClass);
    }
    public static void hasNoExceptions(String actionName, JAction action) {
        getAssert().hasNoExceptions(actionName, action);
    }
    public static void hasNoExceptions(JAction action) {
        getAssert().hasNoExceptions(action);
    }

    public static <T> void listEquals(Supplier<Collection<T>> actual, Collection<T> expected, String failMessage) {
        getAssert().listEquals(actual, expected, failMessage);
    }

    public static <T> void listEquals(Supplier<Collection<T>> actual, Collection<T> expected) {
        getAssert().listEquals(actual, expected);
    }

    public static <T> void arrayEquals(Supplier<T> actual, T expected, String failMessage) {
        getAssert().arrayEquals(actual, expected, failMessage);
    }

    public static <T> void arrayEquals(Supplier<T> actual, T expected) {
        getAssert().arrayEquals(actual, expected);
    }


    public static <T> void entityIncludeMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage) {
        getAssert().entityIncludeMapArray(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMapArray(Supplier<MapArray<String, String>> actual, T entity) {
        getAssert().entityIncludeMapArray(actual, entity);
    }

    public static <T> void entityEqualsToMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage) {
        getAssert().entityEqualsToMapArray(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMapArray(Supplier<MapArray<String, String>> actual, T entity) {
        getAssert().entityEqualsToMapArray(actual, entity);
    }

    public static <T> void entityIncludeMap(Supplier<Map<String, String>> actual, T entity, String failMessage) {
        getAssert().entityIncludeMap(actual, entity, failMessage);
    }

    public static <T> void entityIncludeMap(Supplier<Map<String, String>> actual, T entity) {
        getAssert().entityIncludeMap(actual, entity);
    }

    public static <T> void entityEqualsToMap(Supplier<Map<String, String>> actual, T entity, String failMessage) {
        getAssert().entityEqualsToMap(actual, entity, failMessage);
    }

    public static <T> void entityEqualsToMap(Supplier<Map<String, String>> actual, T entity) {
        getAssert().entityEqualsToMap(actual, entity);
    }
}