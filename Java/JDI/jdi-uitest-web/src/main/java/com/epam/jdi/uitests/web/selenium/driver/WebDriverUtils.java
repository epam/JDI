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

import java.io.IOException;

import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static java.lang.Runtime.getRuntime;
import static java.lang.String.format;

/**
 * Created by 12345 on 26.01.2015.
 */
public final class WebDriverUtils {

    private WebDriverUtils() {
    }

    private static void killMacOSBrowsersByName(String driverName) {
        try {
            Process process = new ProcessBuilder(
                "/usr/bin/pkill",
                "-f",
                ".*" + driverName + ".*")
                .start();
            process.waitFor();
        }
        catch (IOException | InterruptedException e1){
            e1.printStackTrace();
        }
    }
    private static void killByName(String name) throws IOException {
        getRuntime().exec(format("taskkill /F /IM %s.exe /T", name));
    }

    //TODO Add OS type and current user check.
    //TODO Try to use C/C++ Library to work with processes.
    public static void killAllRunWebBrowsers() throws IOException {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Mac")) {
                killMacOSBrowsersByName("Firefox");
                killMacOSBrowsersByName("Chrome");
            } else {
                killByName("chromedriver.exe");
                killByName("geckodriver.exe");
                killByName("IEDriverServer.exe");
                killByName("MicrosoftWebDriver.exe");
            }
        } catch (Exception ignore) { }
    }
}