package com.epam.jdi.uitests.win.winium.driver;

import org.openqa.selenium.By;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.PrintUtils.print;
import static java.lang.String.format;

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


import org.openqa.selenium.By;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.PrintUtils.print;
import static java.lang.String.format;

/**
 * Created by roman.i on 30.09.2014.
 */
public final class WebDriverByUtils {

    private WebDriverByUtils() { }

    public static Function<String, By> getByFunc(By by) {
        return first(getMapByTypes(), key -> by.toString().contains(key));
    }

    private static String getBadLocatorMsg(String byLocator, Object... args) {
        return "Bad locator template '" + byLocator + "'. Args: " + print(select(args, Object::toString), ", ", "'%s'") + ".";
    }

    public static By fillByTemplate(By by, Object... args) {
        String byLocator = getByLocator(by);
        if (!byLocator.contains("%"))
            throw new RuntimeException(getBadLocatorMsg(byLocator, args));
        try {
            byLocator = format(byLocator, args);
        } catch (Exception ex) {
            throw new RuntimeException(getBadLocatorMsg(byLocator, args));
        }
        return getByFunc(by).apply(byLocator);
    }

    public static boolean containsRoot(By by) {
        return by != null && by.toString().contains(": *root*");
    }
    public static By trimRoot(By by) {
        String byLocator = getByLocator(by).replace("*root*", " ").trim();
        return getByFunc(by).apply(byLocator);
    }

    public static By fillByMsgTemplate(By by, Object... args) {
        String byLocator = getByLocator(by);
        try {
            byLocator = MessageFormat.format(byLocator, args);
        } catch (Exception ex) {
            throw new RuntimeException(getBadLocatorMsg(byLocator, args));
        }
        return getByFunc(by).apply(byLocator);
    }

    public static By copyBy(By by) {
        String byLocator = getByLocator(by);
        return getByFunc(by).apply(byLocator);
    }


    public static String getByLocator(By by) {
        String byAsString = by.toString();
        int index = byAsString.indexOf(": ") + 2;
        return byAsString.substring(index);
    }

    public static String getByName(By by) {
        Matcher m = Pattern.compile("By\\.(?<locator>.*):.*").matcher(by.toString());
        if (m.find())
            return m.group("locator");
        throw new RuntimeException("Can't get By name for: " + by);
    }

    public static By getByFromString(String stringLocator) {
        if (stringLocator == null || stringLocator.equals(""))
            throw new RuntimeException("Can't get By locator from string empty or null string");
        String[] split = stringLocator.split("(^=)*=.*");
        if (split.length == 1)
            return By.cssSelector(split[0]);
        switch (split[0]) {
            case "css": return By.cssSelector(split[1]);
            case "xpath": return By.xpath(split[1]);
            case "class": return By.className(split[1]);
            case "name": return By.name(split[1]);
            case "id": return By.id(split[1]);
            case "tag": return By.tagName(split[1]);
            case "link": return By.partialLinkText(split[1]);
            default: throw new RuntimeException(
                    String.format("Can't get By locator from string: %s. Bad suffix: %s. (available: css, xpath, class, id, name, link, tag)",
                            stringLocator, split[0]));
        }
    }

    private static Map<String, Function<String, By>> getMapByTypes() {
        Map<String, Function<String, By>> map = new HashMap<>();
        map.put("By.cssSelector", By::cssSelector);
        map.put("By.className", By::className);
        map.put("By.id", By::id);
        map.put("By.linkText", By::linkText);
        map.put("By.name", By::name);
        map.put("By.partialLinkText", By::partialLinkText);
        map.put("By.tagName", By::tagName);
        map.put("By.xpath", By::xpath);
        return map;
    }
}
