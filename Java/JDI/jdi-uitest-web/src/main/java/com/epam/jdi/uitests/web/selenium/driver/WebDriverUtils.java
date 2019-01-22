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

import com.epam.jdi.uitests.web.settings.WebSettings;

import java.io.IOException;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.web.selenium.driver.UnixProcessUtils.killProcessesTree;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

/**
 * Created by 12345 on 26.01.2015.
 */
public final class WebDriverUtils {


    private WebDriverUtils() {
    }

    /**
     * @throws IOException
     */
    public static void killAllRunWebBrowsers() {
        String os = System.getProperty("os.name");
        try {
            if (os.contains("Mac")) {
                killAllMacOSDriverProcesses();
            } else {
                killAllWindowsDriverProcesses();
            }
        }
        catch (Exception ignore){
            logger.info("Can't kill driver processes");
        }
    }

    private static void killAllMacOSDriverProcesses() {
        killMacOSDriverProcesses("firefox");
        killMacOSDriverProcesses("chrome");
    }

    /**
     *
     */
    private static void killAllWindowsDriverProcesses() throws IOException {
        killByName("chromedriver");
        killByName("geckodriver");
        killByName("IEDriverServer");
        killByName("MicrosoftWebDriver");
    }

    private static void killByName(String value) throws IOException {
        getRuntime().exec(format("taskkill /F /IM %s.exe /T", value));
    }

    private static void killMacOSDriverProcesses(String browserName) {
        String name = null;
        switch (browserName.toLowerCase()) {
            case "firefox":
                name = "geckodriver";
                break;
            case "chrome":
                name = "chromedriver";
                break;

        }
        if (name != null) {
            killAllMacOSDriverProcessesByName(name);
        }
    }

    /**
     * @param driverName
     */
    private static void killAllMacOSDriverProcessesByName(String driverName) {
        killProcessesTree(driverName);
    }

}