package com.epam.jdi.uitests.mobile.appium.elements.complex;
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


import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.mobile.appium.elements.GetElementType;
import com.epam.jdi.uitests.mobile.appium.elements.base.Clickable;
import com.epam.jdi.uitests.mobile.appium.elements.base.Element;
import com.epam.jdi.uitests.mobile.appium.elements.common.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.function.Function;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * RadioButtons control implementation
 *
 * @author Alexeenko Yan
 */
public class Dropdown<TEnum extends Enum> extends Selector<TEnum> implements IDropDown<TEnum> {
    protected GetElementType element;
    protected GetElementType expander;
    protected GetElementType elementByName;

    public Dropdown() {
        super();
    }

    public Dropdown(By selectLocator) {
        super(selectLocator);
    }

    public Dropdown(By selectLocator, By optionsNamesLocator) {
        this(selectLocator, optionsNamesLocator, null);
    }

    public Dropdown(By selectLocator, By optionsNamesLocator, By allOptionsNamesLocator) {
        super(optionsNamesLocator, allOptionsNamesLocator);
        element = new GetElementType(selectLocator, this);
        expander = new GetElementType(selectLocator, this);
    }

    public void setUp(By root, By value, By list, By expand, By elementByName) {
        if (root != null) {
            Element el = new Element(root);
            el.setParent(getParent());
            setParent(el);
        }
        if (value != null) {
            element = new GetElementType(value, this);
            if (expander == null) expander = element;
        }
        if (list != null)
            allLabels = new GetElementType(list, this);
        if (expand != null) {
            expander = new GetElementType(expand, this);
            if (element == null) element = expander;
        }
        if (elementByName != null)
            this.elementByName = new GetElementType(elementByName, this);
    }

    protected Label element() {
        if (element == null)
            throw exception("'Value' element for dropdown not defined");
        return element.get(Label.class);
    }
    protected Clickable expander() {
        if (expander == null)
            throw exception("'Expand' element for dropdown not defined");
        return expander.get(Label.class);
    }

    protected void expandAction(String name) {
        if (element().isDisplayed()) {
            setWaitTimeout(0);
            timer().wait(() -> {
                if (!isDisplayedAction(name))
                    expander().click();
                return isDisplayedAction(name);
            });
            restoreWaitTimeout();
        }
    }

    protected void expandAction(int index) {
        if (!isDisplayedAction(index))
            element().click();
    }

    @Override
    protected void selectAction(String name) {
        if (element() != null) {
            expandAction(name);
            if (elementByName != null)
                elementByName.get(Selector.class).select(name);
            else
                super.selectAction(name);
        } else
            new Select(getWebElement()).selectByVisibleText(name);
    }

    @Override
    protected void selectAction(int index) {
        if (element() != null) {
            expandAction(index);
            super.selectAction(index);
        } else
            new Select(getWebElement()).selectByIndex(index);
    }

    @Override
    protected boolean isDisplayedAction(String name) {
        WebElement element;
        try {
            element = elementByName != null
                    ? elementByName.get(Selector.class).getWebElement(name)
                    : getWebElement(name);
        } catch (Exception | Error ex) {
            return false;
        }
        return element != null && element.isDisplayed();
    }
    @Override
    protected String getValueAction() {
        return getTextAction();
    }

    @Override
    protected String getSelectedAction() {
        return getTextAction();
    }

    /**
     * Waits while Element becomes visible
     */
    @Override
    public void waitDisplayed() {
        element().waitDisplayed();
    }

    /**
     * Waits while Element becomes invisible
     */
    @Override
    public void waitVanished() {
        element().waitVanished();
    }

    public void wait(Function<WebElement, Boolean> resultFunc) {
        element().wait(resultFunc);
    }

    public <T> T wait(Function<WebElement, T> resultFunc, Function<T, Boolean> condition) {
        return element().wait(resultFunc, condition);
    }

    public void wait(Function<WebElement, Boolean> resultFunc, int timeoutSec) {
        element().wait(resultFunc, timeoutSec);
    }

    public <T> T wait(Function<WebElement, T> resultFunc, Function<T, Boolean> condition, int timeoutSec) {
        return element().wait(resultFunc, condition, timeoutSec);
    }

    @Override
    protected List<String> getOptionsAction() {
        boolean isExpanded = isDisplayedAction(1);
        if (!isExpanded) element().click();
        List<String> result = super.getOptionsAction();
        if (!isExpanded) element().click();
        return result;
    }

    protected void clickAction() {
        element().click();
    }

    protected String getTextAction() {
        return element().getText();
    }

    /**
     * Expanding DropDown
     */
    public final void expand() {
        actions.expand(() -> expandAction(1));
    }
    public final void expand(String name) {
        actions.expand(() -> expandAction(name));
    }
    public final void expand(int index) {
        actions.expand(() -> expandAction(index));
    }


    /**
     * Closing DropDown
     */
    public final void close() {
        if (isDisplayedAction(1)) element().click();
    }

    /**
     * Click on Element
     */
    public final void click() {
        actions.click(this::clickAction);
    }

    /**
     * @return Get Element’s text
     */
    public final String getText() {
        return actions.getText(this::getTextAction);
    }

    /**
     * @param text Specify expected text
     * @return Wait while Element’s text contains expected text. Returns Element’s text
     */
    public final String waitText(String text) {
        return actions.waitText(text, this::getTextAction);
    }

    /**
     * @param regEx Specify expected regular expression Text
     * @return Wait while Element’s text matches regEx. Returns Element’s text
     */
    public final String waitMatchText(String regEx) {
        return actions.waitMatchText(regEx, this::getTextAction);
    }

    /**
     * @param attributeName Specify attribute name
     * @param value         Specify attribute value
     *                      Sets attribute value for Element
     */
    public void setAttribute(String attributeName, String value) {
        element().setAttribute(attributeName, value);
    }

    public WebElement getWebElement() {
        return new Element(getLocator()).getWebElement();
    }

    public String getAttribute(String name) {
        return element().getAttribute(name);
    }

    /**
     * @param name  Specify attribute name
     * @param value Specify attribute value
     * Waits while attribute gets expected value. Return false if this not happens
     */
    public void waitAttribute(String name, String value) {
        element().waitAttribute(name, value);
    }

}