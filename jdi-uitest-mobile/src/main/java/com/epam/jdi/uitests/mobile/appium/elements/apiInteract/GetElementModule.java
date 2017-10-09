package com.epam.jdi.uitests.mobile.appium.elements.apiInteract;
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
import com.epam.jdi.uitests.core.interfaces.base.IAvatar;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.mobile.appium.elements.BaseElement;
import com.epam.jdi.uitests.mobile.appium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.any;
import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.mobile.WebSettings.getDriverFactory;
import static com.epam.jdi.uitests.mobile.appium.driver.AppiumDriverFactory.onlyOneElementAllowedInSearch;
import static com.epam.jdi.uitests.mobile.appium.driver.WebDriverByUtils.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/3/2015.
 */
public class GetElementModule implements IAvatar {
    private static final String FAILED_TO_FIND_ELEMENT_MESSAGE = "Can't find Element '%s' during %s seconds";
    private static final String FIND_TO_MUCH_ELEMENTS_MESSAGE = "Find %s elements instead of one for Element '%s' during %s seconds";
    private By byLocator;
    public By frameLocator;
    public JFuncTREx<WebElement, Boolean> localElementSearchCriteria = null;
    public WebElement rootElement;
    private String driverName = "";
    private BaseElement element;
    private WebElement webElement;
    private List<WebElement> webElements;

    public GetElementModule(BaseElement element) {
        this.element = element;
        driverName = !driverName.equals("") ? driverName : driverFactory.currentDriverName();
    }

    public GetElementModule(By byLocator, BaseElement element) {
        this(element);
        this.byLocator = byLocator;
    }

    public GetElementModule copy() {
        return copy(byLocator);
    }

    public GetElementModule copy(By byLocator) {
        GetElementModule clone = new GetElementModule(byLocator, element);
        clone.localElementSearchCriteria = localElementSearchCriteria;
        clone.frameLocator = frameLocator;
        clone.rootElement = rootElement;
        clone.driverName = driverName;
        clone.element = element;
        clone.webElement = webElement;
        clone.webElements = webElements;
        return clone;
    }

    public boolean hasLocator() {
        return byLocator != null;
    }
    public By getLocator() { return byLocator; }
    public void setWebElement(WebElement webElement) { this.webElement = webElement; }
    public boolean hasWebElement() { return webElement != null; }

    public WebDriver getDriver() {
        return (WebDriver) driverFactory.getDriver(driverName);
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public WebElement getElement() {
        logger.debug("Get Web Element: " + element);
        WebElement element = webElement != null
                ? webElement
                : timer().getResultByCondition(this::getElementAction, el -> el != null);
        logger.debug("One Element found");
        return element;
    }

    public List<WebElement> getElements() {
        logger.debug("Get Web elements: " + element);
        List<WebElement> elements = getElementsAction();
        logger.debug("Found %s elements", elements.size());
        return elements;
    }

    public <T> T findImmediately(Supplier<T> func, T ifError) {
        element.setWaitTimeout(0);
        JFuncTREx<WebElement, Boolean> temp = localElementSearchCriteria;
        localElementSearchCriteria = el -> true;
        T result;
        try {
            result = func.get();
        } catch (Exception | Error ex) {
            result = ifError;
        }
        localElementSearchCriteria = temp;
        element.restoreWaitTimeout();
        return result;
    }

    public Timer timer() {
        return new Timer(timeouts.getCurrentTimeoutSec() * 1000);
    }
    private List<WebElement> getElementsByCondition(JFuncTREx<WebElement, Boolean> condition) {
        List<WebElement> elements = timer().getResultByCondition(
                this::searchElements,
                els -> any(els, getSearchCriteria()));
        if (elements == null || elements.size() == 0)
            return new ArrayList<>();
        return elements.size() < 10 ? where(elements, condition) : elements;
    }

    private List<WebElement> getElementsAction() {
        if (webElements != null)
            return webElements;
        List<WebElement> result = getElementsByCondition(getSearchCriteria());
        timeouts.dropTimeouts();
        if (result == null)
            throw exception("Can't get Web Elements");
        return result;
    }

    private JFuncTREx<WebElement, Boolean> getSearchCriteria() {
        return localElementSearchCriteria != null ? localElementSearchCriteria : getDriverFactory().elementSearchCriteria;
    }

    public GetElementModule searchAll() {
        localElementSearchCriteria = el -> el != null;
        return this;
    }

    private WebElement getElementAction() {
        int timeout = timeouts.getCurrentTimeoutSec();
        List<WebElement> result = getElementsAction();
        switch (result.size()) {
            case 0:
                throw exception(FAILED_TO_FIND_ELEMENT_MESSAGE, element, timeout);
            case 1:
                return result.get(0);
            default:
                if (onlyOneElementAllowedInSearch)
                    throw exception(FIND_TO_MUCH_ELEMENTS_MESSAGE, result.size(), element, timeout);
                else
                    return result.get(0);
        }
    }

    private SearchContext getSearchContext(Object element) {
        Object p;
        BaseElement bElement;
        Element el;
        if (element == null || !isClass(element.getClass(), BaseElement.class)
                || ((p = (bElement = (BaseElement) element).getParent()) == null
                && bElement.avatar.frameLocator == null))
            return getDriver().switchTo().defaultContent();
        if (isClass(bElement.getClass(), Element.class) && (el = (Element) bElement).avatar.hasWebElement())
            return el.getWebElement();
        By locator = bElement.getLocator();
        SearchContext searchContext = containsRoot(locator)
                ? getDriver().switchTo().defaultContent()
                : getSearchContext(p);
        locator = containsRoot(locator)
                ? trimRoot(locator)
                : locator;
        By frame = bElement.avatar.frameLocator;
        if (frame != null)
            getDriver().switchTo().frame((WebElement)getDriver().findElement(frame));
        return locator != null
                ? searchContext.findElement(correctXPaths(locator))
                : searchContext;
    }

    private List<WebElement> searchElements()
    {
        SearchContext searchContext = containsRoot(getLocator())
                ? getDriver()
                : getSearchContext(element.getParent());
        By locator = containsRoot(getLocator())
                ? trimRoot(getLocator())
                : getLocator();
        if (frameLocator != null)
            getDriver().switchTo().frame((WebElement)getDriver().findElement(frameLocator));
        return searchContext.findElements(correctXPaths(locator));
    }

    public void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    @Override
    public String toString() {
        return shortLogMessagesFormat
                ? printFullLocator()
                : format("Locator: '%s'", getLocator())
                + (element.getParent() != null && isClass(element.getParent().getClass(), IBaseElement.class)
                ? format(", Context: '%s'", element.printContext())
                : "");
    }

    private String printFullLocator() {
        if (!hasLocator())
            return "No Locators";
        return element.printContext() + "; " + printShortBy(getLocator());
    }

    private String printShortBy(By by) {
        return String.format("%s='%s'", getByName(by), getByLocator(by));
    }
}