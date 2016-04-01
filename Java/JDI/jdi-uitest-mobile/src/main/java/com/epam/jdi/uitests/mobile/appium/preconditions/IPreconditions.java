package com.epam.jdi.uitests.mobile.appium.preconditions;
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


import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.epam.jdi.uitests.mobile.WebSettings.domain;
import static com.epam.jdi.uitests.mobile.WebSettings.getDriver;

/**
 * Created by Roman_Iovlev on 10/27/2015.
 */
public interface IPreconditions {
    static boolean checkUrl(String uri, WebDriver driver) {
        return driver.getCurrentUrl().matches(".*/" + uri + "(\\?.*)?");
    }

    static void openUri(String uri, WebDriver driver) {
        //Timer timer = new Timer(1000, 100);
        driver.navigate().to(getUrlByUri(uri));
        /*if (!timer.timeoutPassed())
            driver.navigate().to(getUrlByUri(uri));*/
    }

    static String getUrlByUri(String uri) {
        return domain.replaceAll("/*$", "") + "/" + uri.replaceAll("^/*", "");
    }

    Function<WebDriver, Boolean> checkAction();
    Consumer<WebDriver> moveToAction();

    default void open(WebDriver driver) {
        moveToAction().accept(driver);
    }
    default void open() {
        open(getDriver());
    }
}