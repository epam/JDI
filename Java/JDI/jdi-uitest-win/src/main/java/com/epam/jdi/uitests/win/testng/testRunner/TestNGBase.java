package com.epam.jdi.uitests.win.testng.testRunner;
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


import com.epam.commons.Timer;
import com.epam.jdi.uitests.win.winium.driver.DriverTypes;
import com.epam.jdi.uitests.win.settings.WinSettings;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.win.settings.WinSettings.initFromProperties;
import static com.epam.jdi.uitests.win.settings.WinSettings.useDriver;
import static com.epam.jdi.uitests.win.winium.driver.WebDriverUtils.killAllRunWebDrivers;

/**
 * Created by Roman_Iovlev on 9/3/2015.
 */
public class TestNGBase {
    protected static Timer timer;

    public static long getTestRunTime() {
        return timer.timePassedInMSec();
    }

    @BeforeSuite(alwaysRun = true)
    public static void jdiSetUp() throws Exception {
        WinSettings.initFromProperties();
        logger.info("Init test run");
        killAllRunWebDrivers();
        if (!driverFactory.hasDrivers())
            useDriver(DriverTypes.WINIUMDESKTOP);
        timer = new Timer();
    }

    @AfterSuite(alwaysRun = true)
    public static void jdiTearDown() {
        logger.info("Test run finished. " + LINE_BREAK + "Total test run time: "
                + new SimpleDateFormat("HH:mm:ss.S").format(new Date(21 * 3600000 + getTestRunTime())));
        killAllRunWebDrivers();
    }
}