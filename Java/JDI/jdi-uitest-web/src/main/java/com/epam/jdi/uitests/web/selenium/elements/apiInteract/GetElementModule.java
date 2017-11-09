package com.epam.jdi.uitests.web.selenium.elements.apiInteract;
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
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.any;
import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory.elementSearchCriteria;
import static com.epam.jdi.uitests.web.selenium.driver.SeleniumDriverFactory.onlyOneElementAllowedInSearch;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/3/2015.
 */
public class GetElementModule implements IAvatar {
    private static final String FAILED_TO_FIND_ELEMENT_MESSAGE
            = "Can't find Element '%s' during %s seconds";
    private static final String FIND_TO_MUCH_ELEMENTS_MESSAGE
            = "Find %s elements instead of one for Element '%s' during %s seconds";
    private By byLocator;
    public By frameLocator;
    public JFuncTREx<WebElement, Boolean> localElementSearchCriteria = null;
    private String driverName = "";
    private BaseElement element;
    private WebElement webElement;
    private List<WebElement> webElements;

    public GetElementModule(BaseElement element) {
        this.element = element;
        if (driverName.equals(""))
            driverName = driverFactory.currentDriverName();
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
    public boolean hasWebElement() { if (webElement == null) return false; try { webElement.getTagName(); return true; } catch (Exception ex) {return false; } }

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
                : getElementAction();
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

    public Timer timer(int sec) {
        return new Timer(sec * 1000);
    }
    public Timer timer() {
        return timer(timeouts.getCurrentTimeoutSec());
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
        return localElementSearchCriteria != null ? localElementSearchCriteria : elementSearchCriteria;
    }

    public GetElementModule searchAll() {
        localElementSearchCriteria = Objects::nonNull;
        return this;
    }
    private List<WebElement> getOneOrMoreElements() {
        List<WebElement> result = webElements != null
            ? webElements
            : searchElements();
        if (result.size() == 1)
            return result;
        return where(result, el -> getSearchCriteria().invoke(el));
    }

    private WebElement getElementAction() {
        int timeout = timeouts.getCurrentTimeoutSec();
        List<WebElement> result = getOneOrMoreElements();
        switch (result.size()) {
            case 0:
                throw exception(FAILED_TO_FIND_ELEMENT_MESSAGE, element, timeout);
            case 1:
                return result.get(0);
            default:
                if (onlyOneElementAllowedInSearch)
                    throw exception(FIND_TO_MUCH_ELEMENTS_MESSAGE, result.size(), element, timeout);
                return result.get(0);
        }
    }

    private SearchContext getSearchContext(Object element) {
        if (element == null || !isClass(element.getClass(), BaseElement.class))
            return getDriver().switchTo().defaultContent();
        BaseElement bElement = (BaseElement) element;
        if (bElement.useCache && isClass(bElement.getClass(), Element.class)) {
            Element el = (Element) bElement;
            if (el.avatar.hasWebElement())
                return el.avatar.webElement;
        }
        Object p = bElement.getParent();
        if (p == null && bElement.avatar.frameLocator == null)
            return getDriver().switchTo().defaultContent();
        By locator = bElement.getLocator();
        SearchContext searchContext = containsRoot(locator)
                ? getDriver().switchTo().defaultContent()
                : getSearchContext(p);
        locator = containsRoot(locator)
                ? trimRoot(locator)
                : locator;
        By frame = bElement.avatar.frameLocator;
        if (frame != null)
            getDriver().switchTo().frame(getDriver().findElement(frame));
        return locator != null
                ? searchContext.findElement(correctXPaths(locator))
                : searchContext;
    }

    private List<WebElement> searchElements() {
        SearchContext searchContext = containsRoot(getLocator())
                ? getDriver()
                : getSearchContext(element.getParent());
        By locator = containsRoot(getLocator())
                ? trimRoot(getLocator())
                : getLocator();
        if (frameLocator != null)
            getDriver().switchTo().frame(getDriver().findElement(frameLocator));
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
