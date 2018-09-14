package com.epam.jdi.uitests.web.settings;
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


import com.codeborne.selenide.SelenideElement;
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.common.*;
import com.epam.jdi.uitests.core.interfaces.complex.*;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITable;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.web.selenium.TestNGCheck;
import com.epam.jdi.uitests.web.selenium.driver.DriverTypes;
import com.epam.jdi.uitests.web.selenium.driver.ScreenshotMaker;
import com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.base.J;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import com.epam.jdi.uitests.web.selenium.elements.complex.*;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGLogger;
import com.epam.web.matcher.base.BaseMatcher;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.allure.events.StepFinishedEvent;
import ru.yandex.qatools.allure.events.StepStartedEvent;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static com.epam.commons.PropertyReader.fillAction;
import static com.epam.jdi.uitests.core.settings.JDIPropertiesReader.getProperties;
import static com.epam.jdi.uitests.web.selenium.driver.DownloadDriverManager.driverVersion;
import static com.epam.jdi.uitests.web.selenium.driver.DownloadDriverManager.platform;
import static com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory.*;
import static com.epam.web.matcher.base.BaseMatcher.screenshotAction;
import static com.epam.web.matcher.base.BaseMatcher.setLogAction;
import static com.epam.web.matcher.testng.Assert.setMatcher;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;
import static ru.yandex.qatools.allure.Allure.LIFECYCLE;

/**
 * Created by Roman_Iovlev on 11/13/2015.
 */
public class WebSettings extends JDISettings {
    public static String DOMAIN;
    public static String killBrowser;
    public static boolean hasDomain() {
        return DOMAIN != null && DOMAIN.contains("://");
    }

    public static WebDriver getDriver() {
        return getDriverFactory().getDriver();
    }

    public static SeleniumDriverFactory getDriverFactory() {
        if (driverFactory == null)
            driverFactory = new SeleniumDriverFactory();
        return (SeleniumDriverFactory) driverFactory;
    }

    public static String useDriver(DriverTypes driverName) {
        return getDriverFactory().registerDriver(driverName);
    }

    public static String useDriver(Supplier<WebDriver> driver) {
        return getDriverFactory().registerDriver(driver);
    }
    public static String useDriver(String name, Supplier<WebDriver> driver) {
        return getDriverFactory().registerDriver(name, driver);
    }
    public static JavascriptExecutor getJSExecutor() {
        try {
            if (!initialized)
            initFromProperties();
            if (driverFactory.getDriver() instanceof JavascriptExecutor)
                return (JavascriptExecutor) driverFactory.getDriver();
            else
                throw new ClassCastException("JavaScript Executor doesn't support");
        } catch (Throwable ex) {
            throw new RuntimeException("Can't Get JS Executor: " + ex.getMessage());
        }
    }

    public static synchronized void init() {
        try {
            logger = new TestNGLogger("JDI Logger");
            asserter = new TestNGCheck().setUpLogger(logger);
            setMatcher((BaseMatcher) asserter);
            asserter.doScreenshot("screen_on_fail");
            screenshotAction = ScreenshotMaker::doScreenshotGetMessage;
            timeouts = new WebTimeoutSettings();
            MapInterfaceToElement.init(defaultInterfacesMap());
            driverFactory = new SeleniumDriverFactory();
        } catch (Throwable ex) {
            throw new RuntimeException("Can't init JDI Settings: " + ex.getMessage());
        }
    }
    public static boolean initialized = false;

    public static synchronized void initFromProperties() throws IOException {
        try{
            init();
            getProperties(jdiSettingsPath);
            fillAction(p -> DOMAIN = p, "domain");
            fillAction(driverFactory::setDriverPath, "drivers.folder");
            fillAction(p -> driverVersion = p, "driver.version");
            fillAction(p -> platform = p, "os.platform");
            fillAction(p -> driverVersion =
                    p.toLowerCase().equals("true") || p.toLowerCase().equals("1")
                    ? "latest" : "none", "driver.getLatest");
            fillAction(p -> asserter.doScreenshot(p), "screenshot.strategy");
            killBrowser = "afterAndBefore";
            fillAction(p -> killBrowser = p, "browser.kill");
            fillAction(p -> {
                p = p.toLowerCase();
                if (p.equals("soft"))
                    p = "any, multiple";
                if (p.equals("strict"))
                    p = "visible, single";
                if (p.split(",").length == 2) {
                    List<String> params = asList(p.split(","));
                    if (params.contains("visible") || params.contains("displayed"))
                        elementSearchCriteria = WebElement::isDisplayed;
                    if (params.contains("any") || params.contains("all"))
                        elementSearchCriteria = Objects::nonNull;
                    if (params.contains("single"))
                        onlyOneElementAllowedInSearch = true;
                    if (params.contains("multiple"))
                        onlyOneElementAllowedInSearch = false;
                }
            }, "search.element.strategy" );
            fillAction(p -> {
                String[] split = null;
                if (p.split(",").length == 2)
                    split = p.split(",");
                if (p.toLowerCase().split("x").length == 2)
                    split = p.toLowerCase().split("x");
                if (split != null)
                    browserSizes = new Dimension(parseInt(split[0].trim()), parseInt(split[1].trim()));
            }, "browser.size");
            fillAction(p -> pageLoadStrategy = p, "page.load.strategy");
            initialized = true;
            setLogAction(s -> {
                LIFECYCLE.fire(new StepStartedEvent(s));
                logger.step(s);
                LIFECYCLE.fire(new StepFinishedEvent());
            });
            JDISettings.initFromProperties();
        } catch (Throwable ex) {
            throw new RuntimeException("Can't init JDI from properties: " + ex.getMessage());
        }
    }
    
    public static synchronized void initFromProperties(String propertiesPath) throws IOException {
    	jdiSettingsPath = propertiesPath;
    	initFromProperties();
    }

    private static Object[][] defaultInterfacesMap() {
        return new Object[][]{
                {IElement.class, Element.class},
                {SelenideElement.class, J.class},
                {WebElement.class, J.class},
                {IButton.class, Button.class},
                {IClickable.class, Clickable.class},
                {IComboBox.class, ComboBox.class},
                {ILink.class, Link.class},
                {ISelector.class, Selector.class},
                {IText.class, Text.class},
                {IImage.class, Image.class},
                {ITextArea.class, TextArea.class},
                {ITextField.class, TextField.class},
                {ILabel.class, Label.class},
                {IDropDown.class, Dropdown.class},
                {IDropList.class, DropList.class},
                {ITable.class, Table.class},
                {ICheckBox.class, CheckBox.class},
                {IRadioButtons.class, RadioButtons.class},
                {ICheckList.class, CheckList.class},
                {ITextList.class, TextList.class},
                {ITabs.class, Tabs.class},
                {IMenu.class, Menu.class},
                {IFileInput.class, FileInput.class},
                {IDatePicker.class, DatePicker.class},
                {IPagination.class, Pagination.class},
                {ITypeAhead.class, TypeAhead.class}
        };
    }
}