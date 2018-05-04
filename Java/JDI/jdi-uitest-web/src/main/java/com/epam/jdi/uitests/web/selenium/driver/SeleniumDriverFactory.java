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


import com.epam.commons.Timer;
import com.epam.commons.linqinterfaces.JFuncTREx;
import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.any;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.commons.StringUtils.correctPath;
import static com.epam.commons.Timer.sleep;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.web.selenium.driver.DownloadDriverManager.downloadDriver;
import static com.epam.jdi.uitests.web.selenium.driver.DownloadDriverManager.shouldDownloadDriver;
import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.*;
import static com.epam.jdi.uitests.web.selenium.driver.RunTypes.LOCAL;
import static com.epam.jdi.uitests.web.settings.WebSettings.getJSExecutor;
import static java.lang.String.format;
import static java.lang.System.setProperty;
import static java.lang.Thread.currentThread;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.openqa.selenium.ie.InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS;
import static org.openqa.selenium.remote.CapabilityType.PAGE_LOAD_STRATEGY;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public class SeleniumDriverFactory implements IDriver<WebDriver> {
    public static JFuncTREx<WebElement, Boolean> elementSearchCriteria = WebElement::isDisplayed;
    public static boolean onlyOneElementAllowedInSearch = true;
    public RunTypes runType = LOCAL;
    public String remoteHubUrl;
    static final String FOLDER_PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\driver\\";
    public static String currentDriverName = "CHROME";
    public boolean isDemoMode = false;
    public static String pageLoadStrategy = "normal";
    public HighlightSettings highlightSettings = new HighlightSettings();
    private String driversPath = FOLDER_PATH;
    private MapArray<String, Supplier<WebDriver>> drivers = new MapArray<>();
    private ThreadLocal<MapArray<String, WebDriver>> runDrivers = new ThreadLocal<>();

    public SeleniumDriverFactory() {
        this(false, new HighlightSettings(), WebElement::isDisplayed);
    }

    public SeleniumDriverFactory(boolean isDemoMode) {
        this(isDemoMode, new HighlightSettings(), WebElement::isDisplayed);
    }

    public SeleniumDriverFactory(HighlightSettings highlightSettings) {
        this(false, highlightSettings, WebElement::isDisplayed);
    }

    public SeleniumDriverFactory(JFuncTREx<WebElement, Boolean> elementSearchCriteria) {
        this(false, new HighlightSettings(), elementSearchCriteria);
    }

    public SeleniumDriverFactory(boolean isDemoMode, HighlightSettings highlightSettings,
                                 JFuncTREx<WebElement, Boolean> elementSearchCriteria) {
        this.isDemoMode = isDemoMode;
        this.highlightSettings = highlightSettings;
        SeleniumDriverFactory.elementSearchCriteria = elementSearchCriteria;
    }

    public String getDriverPath() {
        return correctPath(driversPath);
    }

    public void setDriverPath(String driverPath) {
        this.driversPath = driverPath;
    }

    static final String getChromeDriverPath(String folderPath) {
        return (checkOS().equals("win") ? folderPath + "\\chromedriver.exe" : folderPath + "\\chromedriver").replace("\\", File.separator);
    }

    static final String getIEDriverPath(String folderPath) {
        return (folderPath + "\\IEDriverServer.exe").replace("\\", File.separator);
    }

    static final String getFirefoxDriverPath(String folderPath) {
        return (checkOS().equals("win") ? folderPath + "\\geckodriver.exe" : folderPath + "\\geckodriver").replace("\\", File.separator);
    }

    static String checkOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {
            return "mac";
        } else if (osName.contains("win") || osName.contains("ms")) {
            return "win";
        } else {
            return "nix";
        }
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
        return runDrivers.get() != null && runDrivers.get().any();
    }

    // REGISTER DRIVER

    public String registerDriver(Supplier<WebDriver> driver) {
        return registerDriver("Driver" + Timer.nowMSecs(), driver);
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
    public void setRemoteHubUrl(String url) {
        remoteHubUrl = url;
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
                throw exception("Register driver failed. Unknown driver: " + driverName);
        }
    }

    public String registerDriver(DriverTypes driverType) {
        switch (runType) {
            case LOCAL:
                return registerLocalDriver(driverType);
            case REMOTE:
                return registerDriver("Remote " + driverType,
                        () -> new RemoteWebDriver(getRemoteURL(), getCapabilities(driverType)));
        }
        throw exception("Register driver failed. Unknown driver: " + driverType);
    }
    private URL getRemoteURL() {
        try {
            if (!isBlank(remoteHubUrl)) {
                String url = remoteHubUrl.replaceAll("/*$", "/");
                if (!url.contains("wd/hub"))
                    url += "wd/hub/";
                return new URL(url);
            }
            throw exception("You run tests in Remote mode, please specify 'remote.url' in test.properties");
        } catch(Exception ex) { throw exception("Can't get remote Url: " + ex.getMessage()); }
    }
    private Capabilities getCapabilities(DriverTypes driverType) {
        switch (driverType) {
            case CHROME: return defaultChromeOptions();
            case FIREFOX: return defaultFirefoxOptions();
            case IE: return defaultIEOptions();
        }
        throw exception("Get capabilities failed. Unknown driver: " + driverType);
    }

    // GET DRIVER

    protected String registerLocalDriver(DriverTypes driverType) {
        if (shouldDownloadDriver())
            downloadDriver(driverType);
        else setUpDrivers(driverType);
        return registerDriver(driverType, getDefaultDriver(driverType));
    }

    private Supplier<WebDriver> getDefaultDriver(DriverTypes driverType) {
        switch (driverType) {
            case CHROME: return () -> new ChromeDriver(defaultChromeOptions());
            case FIREFOX: return () -> new FirefoxDriver(defaultFirefoxOptions());
            case IE: return () -> new InternetExplorerDriver(defaultIEOptions());
        }
        throw exception("Unknown driver: " + driverType);
    }
    private Supplier<WebDriver> getDefaultDriver() {
        return () -> { setProperty("webdriver.chrome.driver",
            getChromeDriverPath(getDriverPath()));
            logger.info("USE DEFAULT DRIVER");
            return new ChromeDriver(defaultChromeOptions());
        };
    }
    private void setUpDrivers(DriverTypes driverType) {
        switch (driverType) {
            case CHROME: setProperty("webdriver.chrome.driver",
                getChromeDriverPath(getDriverPath())); break;
            case FIREFOX: setProperty("webdriver.gecko.driver",
                getFirefoxDriverPath(getDriverPath())); break;
            case IE: setProperty("webdriver.ie.driver",
                getIEDriverPath(getDriverPath())); break;
            default: throw exception("Setup driver failed. Wrong driver type: " + driverType);
        }
    }
    public static Function<MutableCapabilities, MutableCapabilities> modifyCapabilities =
        cap -> { cap.setCapability(PAGE_LOAD_STRATEGY, pageLoadStrategy); return cap; };

    public FirefoxOptions defaultFirefoxOptions() {
        FirefoxOptions cap = new FirefoxOptions();
        return (FirefoxOptions) modifyCapabilities.apply(cap);
    }
    public ChromeOptions defaultChromeOptions() {
        ChromeOptions cap = new ChromeOptions();
        return (ChromeOptions) modifyCapabilities.apply(cap);
    }
    public InternetExplorerOptions defaultIEOptions() {
        InternetExplorerOptions cap = new InternetExplorerOptions();
        cap.setCapability(INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        cap.setCapability("ignoreZoomSetting", true);
        //cap.setCapability("requireWindowFocus", true);
        return (InternetExplorerOptions) modifyCapabilities.apply(cap);
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

    public static Dimension browserSizes;

    private static void maximizeMacBrowser(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        driver.manage().window()
                .setSize(new Dimension(screenSize.width, screenSize.height));
    }
    private static WebDriver setupDriverTimeout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(timeouts.getCurrentTimeoutSec(), SECONDS);
        return driver;
    }

    public static Function<WebDriver, WebDriver> webDriverSettings =
        driver -> setupDriverTimeout(setupDriverSize(driver));

    private static WebDriver setupDriverSize(WebDriver driver) {
        if (browserSizes == null) {
            String driverName = driver.toString().toLowerCase();
            if (any(asList("chrome", "internetexplorer"), driverName::contains)
                    && System.getProperty("os.name").toLowerCase().contains("mac"))
                maximizeMacBrowser(driver);
            else driver.manage().window().maximize();
        } else driver.manage().window().setSize(browserSizes);
        return driver;
    }

    public WebDriver getDriver(String driverName) {
        if (!drivers.keys().contains(driverName))
            if (drivers.isEmpty())
                registerDriver("DEFAULT DRIVER", getDefaultDriver());
            else throw exception("Can't find driver with name '%s'", driverName);
        try {
            Lock lock = new ReentrantLock();
            lock.lock();
            if (runDrivers.get() == null || !runDrivers.get().keys().contains(driverName)) {
                MapArray<String, WebDriver> rDrivers = runDrivers.get();
                if (rDrivers == null)
                    rDrivers = new MapArray<>();
                WebDriver resultDriver = webDriverSettings.apply(drivers.get(driverName).get());
                if (resultDriver == null)
                    throw exception("Can't get WebDriver '%s'. This Driver name not registered", driverName);
                rDrivers.add(driverName, resultDriver);
                runDrivers.set(rDrivers);
            }
            WebDriver result = runDrivers.get().get(driverName);
            if (result.toString().contains("(null)")) {
                result = webDriverSettings.apply(drivers.get(driverName).get());
                runDrivers.get().update(driverName, result);
            }
            lock.unlock();
            return result;
        } catch (Exception ex) {
            throw exception("Can't get driver; Thread: " + currentThread().getId() + LINE_BREAK +
                    format("Drivers: %s; Run: %s", drivers, runDrivers) +
                    "Exception: " + ex.getMessage());
        }
    }

    public void reopenDriver() {
        reopenDriver(currentDriverName);
    }

    public void reopenDriver(String driverName) {
        MapArray<String, WebDriver> rDriver = runDrivers.get();
        if (rDriver.keys().contains(driverName)) {
            rDriver.get(driverName).close();
            rDriver.removeByKey(driverName);
            runDrivers.set(rDriver);
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
        WebElement webElement = ((Element) element).getHighLightElement();
        String orig = webElement.getAttribute("style");
        getJSExecutor().executeScript(format("arguments[0].setAttribute('%s',arguments[1]);", "style"),
                webElement, format("border: 3px solid %s; background-color: %s;", highlightSettings.getFrameColor(),
                        highlightSettings.getBgColor()));
        sleep(highlightSettings.getTimeoutInSec() * 1000);
        getJSExecutor().executeScript(format("arguments[0].setAttribute('%s',arguments[1]);", "style"),
                webElement, orig);
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
        for (Pair<String, WebDriver> driver : runDrivers.get())
            driver.value.quit();
        runDrivers.get().clear();
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