package com.epam.jdi.uitests.web.testng.testRunner;
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
import com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.web.settings.WebSettings.initFromProperties;

/** Created by Roman_Iovlev on 9/3/2015. */
public class TestNGBase {
  protected static Timer timer;

  @BeforeSuite(alwaysRun = true)
  public static void jdiSetUp() throws IOException {
    initFromProperties();
    logger.info("Init test run");
    if (WebSettings.killBrowser.toLowerCase().contains("before")){
        WebDriverUtils.killWebBrowserByName(WebSettings.getDriverFactory().currentDriverName());
        //killAllRunWebBrowsers();
    }
    timer = new Timer();
  }

  @AfterSuite(alwaysRun = true)
  public static void jdiTearDown() throws IOException {
    LocalDateTime date =
        Instant.ofEpochMilli(21 * 3600000 + getTestRunTime())
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    String formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss.S").format(date);

    logger.info("Test run finished. " + LINE_BREAK + "Total test run time: " + formattedTime);

    if (WebSettings.killBrowser.toLowerCase().contains("after")) {
        WebDriverUtils.killWebBrowserByName(WebSettings.getDriverFactory().currentDriverName());
        //killAllRunWebBrowsers();
    }
  }

  public static long getTestRunTime() {
    return timer.timePassedInMSec();
  }
}
