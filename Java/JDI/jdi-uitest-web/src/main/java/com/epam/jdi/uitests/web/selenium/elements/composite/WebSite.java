package com.epam.jdi.uitests.web.selenium.elements.composite;
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


import com.epam.jdi.uitests.core.interfaces.Application;
import com.epam.jdi.uitests.web.selenium.elements.WebCascadeInit;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.CHROME;
import static com.epam.jdi.uitests.web.settings.WebSettings.*;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class WebSite extends Application {
    public static <T> void init(String driverName, Class<T>... sites) {

        for (Class<T> site : sites)
            new WebCascadeInit().initStaticPages(site, driverName);
        currentSite = sites[sites.length-1];
    }
    public static <T> void init(Class<T>... sites) {
        if (!getDriverFactory().hasDrivers())
            useDriver(CHROME);
        String driverName = getDriverFactory().currentDriverName();
        init(driverName, sites);
    }

    /**
     * Open page, defined in @JSite, without need to call WebSite.WebPage.open() method
     * Use this method only when you have one WebSite instance in test.
     * If you have multiple site instances, (e.g. YandexSite and GoogleSite in
     * com.epam.jdi.uitests.testing.simple.examples.MultipleSitesExample#twoSiteExample())
     * use open(Class<T> site) instead.
     *
     */
    public static void open(){
        getDriver().navigate().to(domain);
    }

    /**
     *
     * @param site
     * @param <T>
     *
     *     Use this method in cases like com.epam.jdi.uitests.testing.simple.examples.MultipleSitesExample#twoSiteExample()
     */
    public static <T> void open(Class<T> site){
        String domain = site.getAnnotation(JSite.class).value();
        getDriver().navigate().to(domain);
    }
}
