package com.epam.jdi.uitests.web.selenium.driver;
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

import com.epam.commons.*;
import org.openqa.selenium.os.*;

import java.io.*;

/**
 * Created by 12345 on 26.01.2015.
 */
public final class WebDriverUtils {

    private WebDriverUtils() {
    }

    //TODO Add OS type and current user check.
    //TODO Try to use C/C++ Library to work with processes.
    public static void killAllRunWebBrowsers() throws IOException {
        switch (PropertyReader.getProperty("driver").toLowerCase()) {
            case "chrome":
                WindowsUtils.killByName("chromedriver.exe");
                WindowsUtils.killByName("chrome.exe");
                break;
            case "firefox":
                WindowsUtils.killByName("geckodriver.exe");
                WindowsUtils.killByName("firefox.exe");
                break;
            case "ie":
            case "internetexplorer":
                WindowsUtils.killByName("IEDriverServer.exe");
                WindowsUtils.killByName("iexplore.exe");
                break;
            case "edge":
                WindowsUtils.killByName("MicrosoftWebDriver.exe");
                WindowsUtils.killByName("MicrosoftEdge.exe");
            default:
        }
    }
}