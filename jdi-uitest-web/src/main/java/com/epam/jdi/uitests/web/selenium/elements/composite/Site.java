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
 * without even the implied warranty ofMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */

package com.epam.jdi.uitests.web.selenium.elements.composite;

import com.epam.jdi.uitests.web.selenium.driver.DriverTypes;
import com.epam.jdi.uitests.web.selenium.elements.CascadeInit;
import com.epam.jdi.uitests.web.selenium.preconditions.IPreconditions;
import com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState;
import org.openqa.selenium.WebDriver;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.web.WebSettings.getDriverFactory;
import static com.epam.jdi.uitests.web.WebSettings.useDriver;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class Site {
    public static <T> void Init(Class<T> site) {
        CascadeInit.initPages(site, getDriverFactory().currentDriverName);
    }
    protected String driverName;

    public Site() { CascadeInit.initPages(this, getDriverFactory().currentDriverName); }
    public Site(DriverTypes driver) {
        driverName = useDriver(driver);
        CascadeInit.initPages(this, driverName);
    }
    public Site(String driver) {
        driverName = useDriver(driver);
        CascadeInit.initPages(this, driverName);
    }
    public Site(Supplier<WebDriver> driver) {
        driverName = useDriver(driver);
        CascadeInit.initPages(this, driverName);
    }

    public void isInState(IPreconditions precondition) {
        PreconditionsState.isInState(precondition, getDriverFactory().getDriver(driverName));
    }

}