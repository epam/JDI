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


import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.selenium.elements.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.commons.Timer.sleep;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.*;
import static com.epam.jdi.uitests.web.selenium.driver.RunTypes.LOCAL;
import static java.lang.String.format;
import static java.lang.System.setProperty;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.ie.InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS;
import static org.openqa.selenium.remote.DesiredCapabilities.internetExplorer;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class SeleniumDriverFactory implements IDriver<WebDriver> {
    public Function<WebElement, Boolean> elementSearchCriteria = WebElement::isDisplayed;
    public RunTypes runType = LOCAL;
    private String currentDriverName = "";
    public boolean isDemoMode = false;
    public HighlightSettings highlightSettings = new HighlightSettings();
    private String driversPath = "src\\main\\resources";
    private MapArray<String, Supplier<WebDriver>> drivers = new MapArray<>();
    private MapArray<String, WebDriver> runDrivers = new MapArray<>();
    public SeleniumDriverFactory() {
        this(false, new HighlightSettings(), WebElement::isDisplayed);
    }
    public SeleniumDriverFactory(boolean isDemoMode) {
        this(isDemoMode, new HighlightSettings(), WebElement::isDisplayed);
    }

    public SeleniumDriverFactory(HighlightSettings highlightSettings) {
        this(false, highlightSettings, WebElement::isDisplayed);
    }

    public SeleniumDriverFactory(Function<WebElement, Boolean> elementSearchCriteria) {
        this(false, new HighlightSettings(), elementSearchCriteria);
    }

    public SeleniumDriverFactory(boolean isDemoMode, HighlightSettings highlightSettings,
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
    public void setCurrentDriver(String driverName) {
        currentDriverName = driverName;
    }

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
            case "chrome":
                return registerDriver(CHROME);
            case "firefox":
                return registerDriver(FIREFOX);
            case "ie":
            case "internetexplorer":
                return registerDriver(IE);
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
        switch (driverType) {
            case CHROME:
                return registerDriver(driverType,
                        () -> {
                            setProperty("webdriver.chrome.driver", getDriversPath() + "chromedriver.exe");
                            return webDriverSettings.apply(new ChromeDriver());
                        });
            case FIREFOX:
                return registerDriver(driverType,
                        () -> webDriverSettings.apply(new FirefoxDriver()));
            case IE:
                return registerDriver(driverType, () -> {
                    DesiredCapabilities capabilities = internetExplorer();
                    capabilities.setCapability(INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    setProperty("webdriver.ie.driver", getDriversPath() + "IEDriverServer.exe");
                    return webDriverSettings.apply(new InternetExplorerDriver(capabilities));
                });
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
            registerDriver(CHROME);
            return getDriver(CHROME.toString());
        } catch (Exception ex) {
            throw WebSettings.asserter.exception("Can't get WebDriver. " + LINE_BREAK + ex.getMessage());
        }
    }

    public Function<WebDriver, WebDriver> webDriverSettings = driver -> {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeouts.waitElementSec, SECONDS);
        return driver;
    };

    public WebDriver getDriver(String driverName) {
        if (!drivers.keys().contains(driverName))
            throw exception("Can't find driver with name '%s'", driverName);
        try {
            if (runDrivers.keys().contains(driverName))
                return runDrivers.get(driverName);
            WebDriver resultDriver = drivers.get(driverName).get();
            runDrivers.add(driverName, resultDriver);
            if (resultDriver == null)
                throw exception("Can't get Webdriver '%s'. This Driver name not registered", driverName);
            return resultDriver;
        } catch (Exception ex) {
            logger.info(format("Drivers: %s; Run: %s", drivers, runDrivers));
            throw exception("Can't get driver; Thread: " + Thread.currentThread().getId() + "Exception: " + ex.getMessage());
        }
    }

    public void reopenDriver() {
        reopenDriver(currentDriverName);
    }

    public void reopenDriver(String driverName) {
        if (runDrivers.keys().contains(driverName)) {
            runDrivers.get(driverName).close();
            runDrivers.removeByKey(driverName);
        }
        if (drivers.keys().contains(driverName))
            getDriver();
    }
    public void switchToDriver(String driverName) {
        if (drivers.keys().contains(driverName))
            currentDriverName = driverName;
        else
            throw exception("Can't switch to Webdriver '%s'. This Driver name not registered", driverName);
    }

    public void processDemoMode(BaseElement element) {
        if (isDemoMode)
            if (isClass(element.getClass(), Element.class))
                highlight((Element) element, highlightSettings);
    }

    public void highlight(IElement element) {
        highlight(element, highlightSettings);
    }

    public void highlight(IElement element, HighlightSettings settings) {
        HighlightSettings highlightSettings = settings;
        if (highlightSettings == null)
            highlightSettings = new HighlightSettings();
        String orig = ((Element) element).getWebElement().getAttribute("style");
        element.setAttribute("style", format("border: 3px solid %s; background-color: %s;", highlightSettings.getFrameColor(),
                highlightSettings.getBgColor()));
        sleep(highlightSettings.getTimeoutInSec() * 1000);
        element.setAttribute("style", orig);
    }

    public void runApplication() {

    }

    public void closeApplication() {
    }

    public void get(String s) {

    }

    public String getCurrentUrl() {
        return null;
    }

    public String getTitle() {
        return null;
    }

    public List<WebElement> findElements(By by) {
        return null;
    }

    public WebElement findElement(By by) {
        return null;
    }

    public String getPageSource() {
        return null;
    }

    public void close() {
        for (Pair<String, WebDriver> driver : runDrivers)
            driver.value.quit();
        runDrivers.clear();
    }

    public void quit() {
        close();
    }

    public Set<String> getWindowHandles() {
        return null;
    }

    public String getWindowHandle() {
        return null;
    }
}