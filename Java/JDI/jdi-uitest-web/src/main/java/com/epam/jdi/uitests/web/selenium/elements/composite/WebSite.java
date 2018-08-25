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
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class WebSite extends Application {

    public static <T> void init(String driverName, Class<T>... sites) {
        for (Class<T> site : sites) {
            if (site.isAnnotationPresent(JSite.class)) {
                String value = site.getAnnotation(JSite.class).value();
                if (isNotBlank(value)) DOMAIN = value;
            }
            new WebCascadeInit().initStaticPages(site, driverName);
        }
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
     */
    public static void open(){
        WebPage page = new WebPage(DOMAIN);
        page.setName(currentSite.getSimpleName());
        page.open();
    }
    public static void shouldBeOpened(){
        if (!getDriver().getCurrentUrl().contains(DOMAIN))
            open();
    }
}
