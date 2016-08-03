package com.epam.jdi.uitests.web.selenium.elements.complex;
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
import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
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
        this.element = new GetElementType(selectLocator, this);
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
            if (!isDisplayedAction(name))
                expander().click();
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

    @Override
    public void waitDisplayed() {
        element().waitDisplayed();
    }

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

    public final void expand() {
        actions.expand(() -> expandAction(1));
    }
    public final void expand(String name) {
        expand(1);
    }
    public final void expand(int index) {
        expand(index);
    }

    public final void close() {
        if (isDisplayedAction(1)) element().click();
    }

    public final void click() {
        actions.click(this::clickAction);
    }

    public final String getText() {
        return actions.getText(this::getTextAction);
    }

    public final String waitText(String text) {
        return actions.waitText(text, this::getTextAction);
    }

    public final String waitMatchText(String regEx) {
        return actions.waitMatchText(regEx, this::getTextAction);
    }

    public void setAttribute(String attributeName, String value) {
        element().setAttribute(attributeName, value);
    }

    public WebElement getWebElement() {
        return new Element(getLocator()).getWebElement();
    }

    public String getAttribute(String name) {
        return element().getAttribute(name);
    }

    public void waitAttribute(String name, String value) {
        element().waitAttribute(name, value);
    }

}