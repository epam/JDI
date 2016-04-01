package com.epam.jdi.uitests.mobile.appium.driver;
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


import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.os.WindowsUtils;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.TryCatchUtil.tryGetResult;

/**
 * Created by 12345 on 26.01.2015.
 */
public final class WebDriverUtils {
    private WebDriverUtils() { }
    public static void killAllRunWebDrivers() {
        try {
            String pid = getPid();
            while (pid != null) {
                killPID(pid);
                pid = getPid();
            }
        } catch (Exception ignore) {
            // Ignore in case of not windows Operation System or any other errors
        }
    }

    private static String getPid() {
        return first(where(tryGetResult(WindowsUtils::procMap), el -> el.getKey() != null
                && (el.getKey().contains("Android") && el.getKey().contains("Appium"))));
    }

    private static void killPID(String processID) {
        new CommandLine("taskkill", "/f", "/t", "/pid", processID).execute();
    }
}