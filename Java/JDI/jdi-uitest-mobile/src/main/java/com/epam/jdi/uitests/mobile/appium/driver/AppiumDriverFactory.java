package com.epam.jdi.uitests.mobile.appium.driver;
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


import com.epam.commons.PropertyReader;
import com.epam.commons.TryCatchUtil;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.mobile.WebSettings;
import com.epam.jdi.uitests.mobile.appium.elements.base.Element;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.commons.Timer.sleep;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.mobile.appium.driver.DriverTypes.ANDROID;
import static com.epam.jdi.uitests.mobile.appium.driver.DriverTypes.IOS;
import static com.epam.jdi.uitests.mobile.appium.driver.RunTypes.LOCAL;
import static java.lang.String.format;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class AppiumDriverFactory implements IDriver<WebDriver> {
    public Function<WebElement, Boolean> elementSearchCriteria = WebElement::isDisplayed;
    public RunTypes runType = LOCAL;
    public String currentDriverName = "";
    public boolean isDemoMode = false;
    public HighlightSettings highlightSettings = new HighlightSettings();
    private String driversPath = "src\\main\\resources";
    private String androidAppPath = "http://127.0.0.1:4723/wd/hub";
    private MapArray<String, Supplier<WebDriver>> drivers = new MapArray<>();
    private MapArray<String, WebDriver> runDrivers = new MapArray<>();
    public AppiumDriverFactory() {
        this(false, new HighlightSettings(), WebElement::isDisplayed);
    }

    public AppiumDriverFactory(boolean isDemoMode, HighlightSettings highlightSettings,
                               Function<WebElement, Boolean> elementSearchCriteria) {
        this.isDemoMode = isDemoMode;
        this.highlightSettings = highlightSettings;
        this.elementSearchCriteria = elementSearchCriteria;
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
    public void setCurrentDriver(String driverName) { currentDriverName = driverName; }

    public boolean hasDrivers() {
        return drivers.any();
    }
    public boolean hasRunDrivers() {
        return runDrivers.any();
    }

    // REGISTER DRIVER

    public String registerDriver(Supplier<WebDriver> driver) {
        return registerDriver("Driver" + drivers.size() + 1, driver);
    }

    public void setRunType(String runType) {
        switch (runType.toLowerCase()) {
            case "local":
                this.runType = LOCAL;
                break;
            case "remote":
                this.runType = RunTypes.REMOTE;
                break;
        }
    }

    private String getDriversPath() {
        return ((driversPath.contains(":\\"))
                ? driversPath
                : asserter.silent(() -> new File(driversPath).getCanonicalPath())).replaceAll("/*$", "") + "\\";
    }

    public String registerDriver(String driverName) {
        switch (driverName.toLowerCase()) {
            case "android":
                return registerDriver(ANDROID);
            case "ios":
                return registerDriver(IOS);
            default:
                throw exception("Unknown driver: " + driverName);
        }
    }

    public String registerDriver(DriverTypes driverType) {
        switch (runType) {
            case LOCAL:
                return registerLocalDriver(driverType);
            case REMOTE:
                return registerDriver("Remote " + driverType,
                        () -> new RemoteWebDriver(SauceLabRunner.getSauceUrl(), SauceLabRunner.getSauceDesiredCapabilities(driverType)));
        }
        throw exception("Unknown driver: " + driverType);
    }

    // GET DRIVER

    private String registerLocalDriver(DriverTypes driverType) {
        DesiredCapabilities capabilities;
        switch (driverType) {
            case ANDROID:
                Properties p = TryCatchUtil.tryGetResult(() -> PropertyReader.getProperties("test.properties"));
                File app = new File(p.getProperty("appFilePath"));
                capabilities = new DesiredCapabilities();
                capabilities.setCapability("deviceName", p.getProperty("deviceName"));
                capabilities.setCapability("platformVersion", p.get("platformVersion"));
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("appPackage", p.getProperty("appPackage"));
                capabilities.setCapability("appActivity", p.getProperty("appActivity"));
                URL androidAppUrl = TryCatchUtil.tryGetResult(() -> new URL(androidAppPath));
                return registerDriver(driverType, () -> new AndroidDriver<>(androidAppUrl, capabilities));
        }
        throw exception("Unknown driver: " + driverType);
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
            registerDriver(ANDROID);
            return getDriver(ANDROID.toString());
        } catch (Exception ex) {
            throw WebSettings.asserter.exception("Can't get WebDriver. " + LINE_BREAK + ex.getMessage());
        }
    }

    public WebDriver getDriver(String driverName) {
        try {
            if (runDrivers.keys().contains(driverName))
                return runDrivers.get(driverName);
            WebDriver resultDriver = drivers.get(driverName).get();
            runDrivers.add(driverName, resultDriver);
            if (resultDriver == null)
                throw exception("Can't get Webdriver '%s'. This Driver name not registered", driverName);
            resultDriver.manage().window().maximize();
            resultDriver.manage().timeouts().implicitlyWait(timeouts.waitElementSec, SECONDS);
            return resultDriver;
        } catch (Exception ex) {
            throw exception("Can't get driver");
        }
    }

    public void highlight(IElement element) {
        highlight(element, highlightSettings);
    }

    public void highlight(IElement element, HighlightSettings highlightSettings) {
        HighlightSettings settings = highlightSettings != null
                ? highlightSettings
                : new HighlightSettings();
        String orig = ((Element) element).getWebElement().getAttribute("style");
        element.setAttribute("style", format("border: 3px solid %s; background-color: %s;", highlightSettings.getFrameColor(),
                highlightSettings.getBgColor()));
        sleep(highlightSettings.getTimeoutInSec() * 1000);
        element.setAttribute("style", orig);
    }

    public void get(String s) {

    }

}