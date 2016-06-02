package com.epam.web.matcher.base;
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

import com.epam.commons.LinqUtils;
import com.epam.commons.Timer;
import com.epam.commons.linqinterfaces.JAction;
import com.epam.commons.linqinterfaces.JFuncREx;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.IAsserter;
import org.slf4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.web.matcher.base.DoScreen.NO_SCREEN;
import static com.epam.web.matcher.base.PrintUtils.objToSetValue;
import static com.epam.web.matcher.base.PrintUtils.printObjectAsArray;
import static java.lang.String.format;
import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;
import static java.util.Arrays.asList;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public abstract class BaseMatcher implements IAsserter, IChecker {
    private static Logger logger = getLogger("JDI Logger");
    public void setLogger(Logger logger) { BaseMatcher.logger = logger; }
    private static long waitTimeout = 0;
    private static DoScreen defaultDoScreenType = NO_SCREEN;
    //CHECKSTYLE OFF
    private static String FOUND = "FOUND";
    //CHECKSTYLE ON
    private DoScreen doScreenshot = defaultDoScreenType;
    private String checkMessage = "";
    private boolean ignoreCase;
    private boolean isListCheck;
    private long timeout() { return waitTimeout; }

    public BaseMatcher() {
    }

    public BaseMatcher(String checkMessage) {
        this.checkMessage = getCheckMessage(checkMessage);
    }
    public BaseMatcher check(String checkMessage) {
        this.checkMessage = getCheckMessage(checkMessage);
        return this;
    }


    public static void setDefaultTimeout(long timeout) {
        waitTimeout = timeout;
    }

    public static void setDefaultDoScreenType(DoScreen doScreen) {
        defaultDoScreenType = doScreen;
    }

    protected abstract Consumer<String> throwFail();

    public BaseMatcher doScreenshot(DoScreen doScreenshot) {
        this.doScreenshot = doScreenshot;
        return this;
    }

    public BaseMatcher doScreenshot() {
        return doScreenshot(DoScreen.DO_SCREEN_ALWAYS);
    }

    public BaseMatcher ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

    public BaseMatcher setWait(int timeoutSec) {
        waitTimeout = timeoutSec * 1000L;
        return this;
    }

    private String getCheckMessage(String checkMessage) {
        if (checkMessage == null || checkMessage.equals(""))
            return "";
        String firstWord = checkMessage.split(" ")[0];
        return (!firstWord.equalsIgnoreCase("check") || firstWord.equalsIgnoreCase("verify"))
                ? "Check that " + checkMessage
                : checkMessage;
    }

    private void assertAction(String defaultMessage, Boolean result, String failMessage) {
        assertAction(defaultMessage, () -> result ? FOUND : "Check failed", failMessage, false);
    }

    private void waitAction(String defaultMessage, BooleanSupplier result, String failMessage) {
        assertAction(defaultMessage, () -> result.getAsBoolean() ? FOUND : "Check failed", failMessage, true);
    }

    protected String doScreenshotGetMessage() { return "Screenshots switched off"; }

    private void assertAction(String defaultMessage, Supplier<String> result, String failMessage, boolean wait) {
        if (!isListCheck && defaultMessage != null)
            logger.info(getBeforeMessage(defaultMessage));
        if (!isListCheck && doScreenshot == DoScreen.DO_SCREEN_ALWAYS)
            logger.info(doScreenshotGetMessage());
        String resultMessage = wait
                ? new Timer(timeout()).getResultByCondition(result::get, r -> r != null && r.equals(FOUND))
                : result.get();
        if (resultMessage == null) {
            assertException("Assert Failed by Timeout. Wait %s seconds", timeout() / 1000);
            return;
        }
        if (!resultMessage.equals(FOUND)) {
            if (doScreenshot == DoScreen.SCREEN_ON_FAIL)
                logger.info(doScreenshotGetMessage());
            assertException(failMessage == null ? defaultMessage + " failed" : failMessage);
        }
    }

    private String getBeforeMessage(String defaultMessage) {
        return (checkMessage != null && !checkMessage.equals(""))
                ? checkMessage
                : defaultMessage;
    }

    // For Framework
    public RuntimeException exception(String failMessage, Object... args) {
        assertException(failMessage, args);
        return new RuntimeException(failMessage);
    }

    protected void assertException(String failMessage, Object... args) {
        String failMsg = args.length > 0 ? format(failMessage, args) : failMessage;
        if (doScreenshot == DoScreen.SCREEN_ON_FAIL)
            logger.info(doScreenshotGetMessage());
        logger.error(failMsg);
        throwFail().accept(failMsg);
    }

    public <TResult> TResult silent(JFuncREx<TResult> func) {
        try {
            return func.invoke();
        } catch (Exception ex) {
            throw exception(ex.getMessage());
        }
    }
    //CHECKSTYLE OFF
    private String toUtf8(String text) {
        return silent(() -> new String(text.getBytes(), "UTF-8"));
    }
    //CHECKSTYLE ON

    // Asserts
    public <T> void areEquals(T actual, T expected, String failMessage) {
        boolean result;
        if (expected.getClass() == String.class) {
            String actualString = toUtf8(actual.toString());
            result = ignoreCase
                    ? actualString.equalsIgnoreCase((String) expected)
                    : actualString.equals(expected);
        } else result = actual.equals(expected);
        assertAction(format("Check that '%s' equals to '%s'", actual, expected), result, failMessage);
    }

    public  <T> void areEquals(T actual, T expected) { areEquals(actual, expected, null); }

    public void matches(String actual, String regEx, String failMessage) {
        String actualString = toUtf8(actual);
        boolean result = ignoreCase
                ? actualString.toLowerCase().matches(regEx.toLowerCase())
                : actualString.matches(regEx);
        assertAction(format("Check that '%s' matches to regEx '%s'", actual, regEx), result, failMessage);
    }

    public void matches(String actual, String expected) { matches(actual, expected, null); }

    public void contains(String actual, String expected, String failMessage) {
        String actualString = toUtf8(actual);
        boolean result = ignoreCase
                ? actualString.toLowerCase().contains(expected.toLowerCase())
                : actualString.contains(expected);
        assertAction(format("Check that '%s' contains '%s'", actual, expected), result, failMessage);
    }

    public void contains(String actual, String expected) { contains(actual, expected, null); }
    public void contains(String actual, List<String> expected) {
        for (String value : expected)
            contains(actual, value, null);
    }

    public void isTrue(Boolean condition, String failMessage) {
        assertAction(format("Check that condition '%s' is True", condition), condition, failMessage);
    }

    public void isTrue(Boolean condition) { isTrue(condition, null); }


    public void isFalse(Boolean condition, String failMessage) {
        assertAction(format("Check that condition '%s' is False", condition), !condition, failMessage);
    }

    public void throwException(String actionName, JAction action, Class<Exception> exceptionClass, String exceptionText) {
        try {
            action.invoke();
        } catch (Exception ex) {
            if (exceptionClass != null)
                areEquals(ex.getClass(), exceptionClass);
            if (exceptionText != null)
                areEquals(ex.getMessage(), exceptionText);
        }
        throw exception("Action '%s' throws no exceptions. Expected (%s, %s)", exceptionClass == null ? "" : exceptionClass.getName(), exceptionText);
    }
    public void throwException(JAction action, Class<Exception> exceptionClass, String exceptionText) {
        throwException(!checkMessage.equals("") ? checkMessage : "Action", action, exceptionClass, exceptionText);
    }
    public void hasNoExceptions(String actionName, JAction action) {
        try {
            action.invoke();
        } catch (Exception ex) {
            throw exception(actionName + " throws exception: " + ex.getMessage());
        }
    }

    public void hasNoExceptions(JAction action) {
        hasNoExceptions(!checkMessage.equals("") ? checkMessage : "Action", action);
    }

    private boolean isObjEmpty(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof String)
            return obj.toString().equals("");
        if (isInterface(obj.getClass(), Collection.class))
            return ((Collection) obj).isEmpty();
        return obj.getClass().isArray() && getLength(obj) == 0;
    }

    public void isEmpty(Object obj, String failMessage) {
        assertAction("Check that Object is empty", isObjEmpty(obj), failMessage);
    }


    public void isNotEmpty(Object obj, String failMessage) {
        assertAction("Check that Object is NOT empty", !isObjEmpty(obj), failMessage);
    }

    public <T> void areSame(T actual, T expected, String failMessage) {
        assertAction("Check that Objects are the same", actual == expected, failMessage);
    }

    public <T> void areDifferent(T actual, T expected, String failMessage) {
        assertAction("Check that Objects are different", actual != expected, failMessage);
    }

    public <T> void listEquals(Collection<T> actual, Collection<T> expected, String failMessage) {
        assertAction("Check that Collections are equal",
                () -> actual != null && expected != null && actual.size() == expected.size()
                        ? FOUND
                        : "listEquals failed because one of the Collections is null or empty",
                failMessage, false);
        assertAction(null, () -> {
            T notEqualElement = LinqUtils.first(actual, el -> !expected.contains(el));
            return (notEqualElement != null)
                    ? String.format("Collections '%s' and '%s' not equals at element '%s'",
                    print(LinqUtils.select(actual, Object::toString)), print(LinqUtils.select(expected, Object::toString)), notEqualElement)
                    : FOUND;
        }, failMessage, false);
    }

    private <T> void entityIncludeMap(Map<String, String> actual, T entity, String failMessage, boolean shouldEqual) {
        entityIncludeMapArray(MapArray.toMapArray(actual), entity, failMessage, shouldEqual);
    }

    private <T> void entityIncludeMapArray(MapArray<String, String> actual, T entity, String failMessage, boolean shouldEqual) {
        MapArray<String, String> expected = objToSetValue(entity).where((k, value) -> value != null);
        assertAction("Check that Collections are equal",
                () -> actual != null && expected != null && (!shouldEqual || actual.size() == expected.size())
                        ? FOUND
                        : "listEquals failed because one of the Collections is null or empty",
                failMessage, false);
        assertAction(null, () -> {
            String notEqualElement = expected.first((name, value) -> actual.get(name).equals(value));
            return (notEqualElement != null)
                    ? String.format("Collections '%s' and '%s' not equals at element '%s'",
                    print(select(actual, Object::toString)), print(select(expected, Object::toString)), notEqualElement)
                    : FOUND;
        }, failMessage, false);
    }

    public <T> void entityIncludeMapArray(MapArray<String, String> actual, T entity, String failMessage) {
        entityIncludeMapArray(actual, entity, failMessage, false);
    }

    public <T> void entityEqualsToMapArray(MapArray<String, String> actual, T entity, String failMessage) {
        entityIncludeMapArray(actual, entity, failMessage, true);
    }

    public <T> void entityIncludeMap(Map<String, String> actual, T entity, String failMessage) {
        entityIncludeMap(actual, entity, failMessage, false);
    }

    public <T> void entityEqualsToMap(Map<String, String> actual, T entity, String failMessage) {
        entityIncludeMap(actual, entity, failMessage, true);
    }

    public <T> void arrayEquals(T actual, T expected, String failMessage) {
        assertAction("Check that Collections are equal",
                () -> actual != null && expected != null && actual.getClass().isArray() && expected.getClass().isArray()
                        ? FOUND
                        : "arrayEquals failed because one of the Objects is not Array or empty",
                failMessage, false);
        assertAction("Check that Collections are equal",
                () -> getLength(actual) == getLength(expected)
                        ? FOUND
                        : format("arrayEquals failed because arrays have different lengths: '%s' and '%s'", getLength(actual), getLength(expected)),
                failMessage, false);
        assertAction(null, () -> {
            for (int i = 0; i < getLength(actual); i++)
                if (!get(actual, i).equals(get(expected, i)))
                    return String.format("Arrays not equals at index '%s'. '%s' != '%s'. Arrays: '%s' and '%s'",
                            i, get(actual, i), get(expected, i), printObjectAsArray(actual), printObjectAsArray(expected));
            return FOUND;
        }, failMessage, false);
    }

    public void isSortedByAsc(int[] array, String failMessage) {
        if (array == null || array.length == 0) {
            assertException("isSortedByAsc failed because array have no elements or null");
            return;
        }
        assertAction("Check tat array sorted by ascending",
                () -> {
                    for (int i = 1; i < array.length; i++)
                        if (array[i] < array[i - 1])
                            return format("Array not sorted by ascending. a[%s] > a[%s]", i - 1, i);
                    return FOUND;
                }, failMessage, false);
    }

    public void isSortedByDesc(int[] array, String failMessage) {
        if (array == null || array.length == 0) {
            assertException("isSortedByAsc failed because array have no elements or null");
            return;
        }
        assertAction("Check tat array sorted by ascending",
                () -> {
                    for (int i = 1; i < array.length; i++)
                        if (array[i] > array[i - 1])
                            return format("Array not sorted by ascending. a[%s] < a[%s]", i - 1, i);
                    return FOUND;
                }, failMessage, false);

    }

    // ListProcessor
    public <T> ListChecker eachElementOf(Collection<T> list) {
        return new ListChecker<>(list);
    }

    public <T> ListChecker eachElementOf(T[] array) {
        return new ListChecker<>(asList(array));
    }

    // Asserts Wait
    public <T> void areEquals(Supplier<T> actual, T expected, String failMessage) {
        BooleanSupplier resultAction = (ignoreCase && expected.getClass() == String.class)
                ? () -> toUtf8(actual.get().toString()).equals(expected)
                : () -> toUtf8(actual.get().toString()).equalsIgnoreCase((String) expected);
        waitAction(format("Check that '%s' equals to '%s'", "result", expected), resultAction, failMessage);
    }
/*
    public <T> ListWaitChecker eachElementOf(Supplier<Collection<T>> list) { return new ListWaitChecker<>(list); }
    public <T> ListWaitChecker eachElementOfArray(Supplier<T[]> array) {
        Supplier<Collection<T>> list = () -> asList(array.invoke());
        return new ListWaitChecker<>(list);
    }

    public class ListWaitChecker<T> {
        Supplier<Collection<T>> list;
        private ListWaitChecker(Supplier<Collection<T>> list) {
            this.list = list;
        }

        private void beforeListCheck(String defaultMessage, String expected, String failMessage) {
            assertAction(format(defaultMessage, print(select(list, Object::toString)), expected),
                    () -> list != null && list.size() > 0
                            ? FOUND
                            : "list check failed because list is null or empty",
                    failMessage);
            isListCheck = true;
        }

        public void areEquals(Object expected, String failMessage) {
            beforeListCheck("Check that each item of list '%s' equals to '%s'", expected.toString(), failMessage);
            for (Object el : list)
                BaseChecker.this.areEquals(el, expected, failMessage);
        }
        public void areEquals(Object expected) { areEquals(expected, null); }
        public void matches(String regEx, String failMessage) {
            beforeListCheck("Check that each item of list '%s' matches to regEx '%s'", regEx, failMessage);
            for (Object el : list)
                BaseChecker.this.matches((String) el, regEx, failMessage);
        }
        public void matches(String regEx) { matches(regEx, null); }

        public void contains(String expected, String failMessage) {
            beforeListCheck("Check that each item of list '%s' contains '%s'", expected, failMessage);
            for (Object el : list)
                BaseChecker.this.contains((String)el, expected, failMessage);
        }
        public void contains(String expected) { contains(expected, null); }

        public void areSame(Object expected, String failMessage) {
            beforeListCheck("Check that all items of list '%s' are same with '%s'", expected.toString(), failMessage);
            for (Object el : list)
                BaseChecker.this.areSame(el, expected, failMessage);
        }

        public void areSame(Object actual, Object expected) {
            areSame(expected, null);
        }

        public void areDifferent(Object expected, String failMessage) {
            beforeListCheck("Check that all items of list '%s' are different with '%s'", expected.toString(), failMessage);
            for (Object el : list)
                BaseChecker.this.areDifferent(el, expected, failMessage);
        }

        public void areDifferent(Object actual, Object expected) {
            areDifferent(expected, null);
        }

    }*/

    public <T> void areEquals(Supplier<T> actual, T expected) {
        areEquals(actual, expected, null);
    }

    public void matches(Supplier<String> actual, String regEx, String failMessage) {
        BooleanSupplier resultAction = ignoreCase
                ? () -> toUtf8(actual.get()).matches(regEx)
                : () -> toUtf8(actual.get()).toLowerCase().matches(regEx.toLowerCase());
        waitAction(format("Check that '%s' matches to regEx '%s", "result", regEx), resultAction, failMessage);
    }

    public void matches(Supplier<String> actual, String regEx) {
        matches(actual, regEx, null);
    }

    public void contains(Supplier<String> actual, String expected, String failMessage) {
        BooleanSupplier resultAction = (ignoreCase && expected.getClass() == String.class)
                ? () -> toUtf8(actual.get()).contains(expected)
                : () -> toUtf8(actual.get()).toLowerCase().contains(expected.toLowerCase());
        waitAction(format("Check that '%s' contains '%s'", "result", expected), resultAction, failMessage);
    }

    public void contains(Supplier<String> actual, String expected) {
        contains(actual, expected, null);
    }

    public void isTrue(BooleanSupplier condition, String failMessage) {
        waitAction(format("Check that condition '%s' is True", "result"), condition, failMessage);
    }

    public void isTrue(BooleanSupplier condition) {
        isTrue(condition, null);
    }

    public void isFalse(BooleanSupplier condition, String failMessage) {
        waitAction(format("Check that condition '%s' is False", "result"), () -> !condition.getAsBoolean(), failMessage);
    }

    public void isFalse(BooleanSupplier condition) {
        isFalse(condition, null);
    }

    public void isEmpty(Supplier<Object> obj, String failMessage) {
        waitAction("Check that Object is empty", () -> isObjEmpty(obj), failMessage);
    }

    public void isEmpty(Supplier<Object> obj) {
        isEmpty(obj, null);
    }

    public void isNotEmpty(Supplier<Object> obj, String failMessage) {
        waitAction("Check that Object is NOT empty", () -> !isObjEmpty(obj), failMessage);
    }

    public void isNotEmpty(Supplier<Object> obj) {
        isNotEmpty(obj, null);
    }

    public <T> void areSame(Supplier<T> actual, T expected, String failMessage) {
        waitAction("Check that Objects are the same", () -> actual == expected, failMessage);
    }

    public <T> void areSame(Supplier<T> actual, T expected) {
        areSame(actual, expected, null);
    }

    public <T> void areDifferent(Supplier<T> actual, T expected, String failMessage) {
        waitAction("Check that Objects are different", () -> actual != expected, failMessage);
    }

    public <T> void areDifferent(Supplier<T> actual, T expected) {
        areDifferent(actual, expected, null);
    }

    public <T> void listEquals(Supplier<Collection<T>> actual, Collection<T> expected, String failMessage) {
        assertAction("Check that Collections are equal",
                () -> actual.get() != null && expected != null && actual.get().size() == expected.size()
                        ? FOUND
                        : "listEquals failed because one of the Collections is null or empty",
                failMessage, true);
        assertAction(null, () -> {
            T notEqualElement = first(actual.get(), el -> !expected.contains(el));
            return (notEqualElement != null)
                    ? String.format("Collections '%s' and '%s' not equals at element '%s'",
                    print(select(actual.get(), Object::toString)), print(LinqUtils.select(expected, Object::toString)), notEqualElement)
                    : FOUND;
        }, failMessage, true);
    }

    public <T> void listEquals(Supplier<Collection<T>> actual, Collection<T> expected) {
        listEquals(actual, expected, null);
    }

    private <T> void entityIncludeMap(Supplier<Map<String, String>> actual, T entity, String failMessage, boolean shouldEqual) {
        entityIncludeMapArray(() -> MapArray.toMapArray(actual.get()), entity, failMessage, shouldEqual);
    }
    private <T> void entityIncludeMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage, boolean shouldEqual) {
        MapArray<String, String> expected = objToSetValue(entity).where((k, value) -> value != null);
        assertAction("Check that Collections are equal",
                () -> {
                    MapArray<String, String> actualMap = actual.get();
                    return actualMap != null && expected != null && (!shouldEqual || actualMap.size() == expected.size())
                            ? FOUND
                            : "listEquals failed because one of the Collections is null or empty";
                },
                failMessage, false);
        assertAction(null, () -> {
            MapArray<String, String> actualMap = actual.get();
            String notEqualElement = expected.first((name, value) -> !actualMap.get(name).equals(value));
            return (notEqualElement != null)
                    ? String.format("Collections '%s' and '%s' not equals at element '%s'",
                    print(select(actualMap, Object::toString)), print(select(expected, Object::toString)), notEqualElement)
                    : FOUND;
        }, failMessage, false);
    }

    public <T> void entityIncludeMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage) {
        entityIncludeMapArray(actual, entity, failMessage, false);
    }

    public <T> void entityEqualsToMapArray(Supplier<MapArray<String, String>> actual, T entity, String failMessage) {
        entityIncludeMapArray(actual, entity, failMessage, true);
    }

    public <T> void entityIncludeMap(Supplier<Map<String, String>> actual, T entity, String failMessage) {
        entityIncludeMap(actual, entity, failMessage, false);
    }

    public <T> void entityEqualsToMap(Supplier<Map<String, String>> actual, T entity, String failMessage) {
        entityIncludeMap(actual, entity, failMessage, true);
    }

    public <T> void arrayEquals(Supplier<T> actual, T expected, String failMessage) {
        assertAction("Check that Collections are equal",
                () -> actual.get() != null && expected != null && actual.get().getClass().isArray() && expected.getClass().isArray()
                        && getLength(actual.get()) == getLength(expected)
                        ? FOUND
                        : "arrayEquals failed because one of the Objects is not Array or empty",
                failMessage, true);
        assertAction(null, () -> {
            for (int i = 0; i <= getLength(actual.get()); i++)
                if (!get(actual.get(), i).equals(get(expected, i)))
                    return String.format("Arrays not equals at index '%s'. '%s' != '%s'. Arrays: '%s' and '%s'",
                            i, get(actual.get(), i), get(expected, i), printObjectAsArray(actual), printObjectAsArray(expected));
            return FOUND;
        }, failMessage, true);
    }

    public <T> void arrayEquals(Supplier<T> actual, T expected) {
        arrayEquals(actual, expected, null);
    }

    public final class ListChecker<T> {
        Collection<T> list;

        private ListChecker(Collection<T> list) {
            this.list = list;
        }

        private void beforeListCheck(String defaultMessage, String expected, String failMessage) {
            assertAction(String.format(defaultMessage, print(LinqUtils.select(list, Object::toString)), expected),
                    () -> list != null && !list.isEmpty()
                            ? FOUND
                            : "list check failed because list is null or empty",
                    failMessage, false);
            isListCheck = true;
        }

        public void areEquals(Object expected, String failMessage) {
            beforeListCheck("Check that each item of list '%s' equals to '%s'", expected.toString(), failMessage);
            for (Object el : list)
                BaseMatcher.this.areEquals(el, expected, failMessage);
        }

        public void areEquals(Object expected) {
            areEquals(expected, null);
        }

        public void matches(String regEx, String failMessage) {
            beforeListCheck("Check that each item of list '%s' matches to regEx '%s'", regEx, failMessage);
            for (Object el : list)
                BaseMatcher.this.matches((String) el, regEx, failMessage);
        }

        public void matches(String regEx) {
            matches(regEx, null);
        }

        public void contains(String expected, String failMessage) {
            beforeListCheck("Check that each item of list '%s' contains '%s'", expected, failMessage);
            for (Object el : list)
                BaseMatcher.this.contains((String) el, expected, failMessage);
        }

        public void contains(String expected) {
            contains(expected, null);
        }

        public void areSame(Object expected, String failMessage) {
            beforeListCheck("Check that all items of list '%s' are same with '%s'", expected.toString(), failMessage);
            for (Object el : list)
                BaseMatcher.this.areSame(el, expected, failMessage);
        }

        public void areSame(Object expected) {
            areSame(expected, null);
        }

        public void areDifferent(Object expected, String failMessage) {
            beforeListCheck("Check that all items of list '%s' are different with '%s'", expected.toString(), failMessage);
            for (Object el : list)
                BaseMatcher.this.areDifferent(el, expected, failMessage);
        }

        public void areDifferent(Object expected) {
            areDifferent(expected, null);
        }

    }
}