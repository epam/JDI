package com.epam.jdi.uitests.win.winium.driver;
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
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.win.settings.WinSettings;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.commons.PropertyReader.fillAction;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.win.winium.driver.DriverTypes.WINIUMDESKTOP;
import static com.epam.jdi.uitests.win.winium.driver.DriverTypes.WINIUMMOBILE;
import static com.epam.jdi.uitests.win.winium.driver.DriverTypes.WINIUMSTORE;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class WiniumDriverFactory implements IDriver<WebDriver> {
    public Function<WebElement, Boolean> elementSearchCriteria = WebElement::isDisplayed;
    public String currentDriverName = "";
    public boolean isDemoMode = false;
    public HighlightSettings highlightSettings = new HighlightSettings();
    private String driversPath = "";
    private URL driverURL = new URL ("http://localhost:9999");
    //private String domain = "";
    private DesktopOptions options = new DesktopOptions();
    private MapArray<String, Supplier<WebDriver>> drivers = new MapArray<>();
    private MapArray<String, WebDriver> runDrivers = new MapArray<>();

    public WiniumDriverFactory() throws MalformedURLException {
    }
    public String getDriverPath() {
        return driversPath;
    }

    public void setDriverPath(String driverPath) {
        this.driversPath = driverPath;
    }

    public String currentDriverName() {
        return currentDriverName;
    }

    public void setCurrentDriver(String driverName) {
        currentDriverName = driverName;
    }

    // REGISTER DRIVER

    public String registerDriver(Supplier<WebDriver> driver) {
        return registerDriver("Driver" + drivers.size() + 1, driver);
    }

    private String getDriversPath() {
        return ((driversPath.contains(":\\"))
                ? driversPath
                : asserter.silent(() -> new File(driversPath).getCanonicalPath())).replaceAll("/*$", "") + "\\";
    }


    public String registerDriver(String driverName) {
        switch (driverName.toLowerCase()) {
            case "winiumdesktop":
                try {
                    return registerDriver(WINIUMDESKTOP);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "winiumphone":
                try {
                    return registerDriver(WINIUMMOBILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case "winiumstore":
                try {
                    return registerDriver(WINIUMSTORE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            default:
                throw exception("Unknown driver: " + driverName);
        }
    }

    public void setRunType(String s) {

    }

    public String registerDriver(DriverTypes driverType) throws IOException {
        currentDriverName = "Remote " + driverType;
        return registerDriver(currentDriverName,
                () -> {
                    try {
                        new ProcessBuilder(driversPath + "\\Winium.Desktop.Driver.exe").start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   // fillAction(p-> domain = p, "domain");

                    options.setApplicationPath(domain);
                    return new WiniumDriver(driverURL, options);
                });
    }


    public String registerDriver(DriverTypes driverType, Supplier<WebDriver> driver) {
        int numerator = 2;
        String driverName = driverType.toString();
        // TODO correct constant 100
        while (!drivers.add(driverName, driver) && numerator < 100)
            driverName = driverType.toString() + numerator++;
        currentDriverName = driverName;
        return driverName;
    }

    public String registerDriver(String driverName, Supplier<WebDriver> driver) {
        if (!drivers.add(driverName, driver))
            throw exception("Can't register WebDriver '%s'. Driver with same name already registered", driverName);
        currentDriverName = driverName;
        return driverName;
    }

    public WebDriver getDriver() {
        try {
            if (!currentDriverName.equals(""))
                return getDriver(currentDriverName);
            registerDriver(WINIUMDESKTOP);
            return getDriver(WINIUMDESKTOP.toString());
        } catch (Exception ex) {
            throw WinSettings.asserter.exception("Can't get WebDriver. " + LINE_BREAK + ex.getMessage());
        }
    }

    public boolean hasDrivers() {
        return drivers.any();
    }

    public boolean hasRunDrivers() {
        return runDrivers.any();
    }


    // GET DRIVER
    public WebDriver getDriver(String driverName) {
        try {
            if (runDrivers.keys().contains(driverName))
                return runDrivers.get(driverName);
            WebDriver resultDriver = drivers.get(driverName).get();
            runDrivers.add(driverName, resultDriver);
            if (resultDriver == null)
                throw exception("Can't get Webdriver '%s'. This Driver name not registered", driverName);
            return resultDriver;
        } catch (Exception ex) {
            throw exception("Can't get driver");
        }
    }

    public void highlight(IElement iElement) {

    }

    public void highlight(IElement iElement, HighlightSettings highlightSettings) {

    }
}