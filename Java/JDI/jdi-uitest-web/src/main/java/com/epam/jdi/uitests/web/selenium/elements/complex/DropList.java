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


import com.epam.jdi.uitests.core.interfaces.complex.IDropList;
import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Select control implementation
 *
 * @author Alexeenko Yan
 * @author Belousov Andrey
 */
public class DropList<TEnum extends Enum> extends MultiSelector<TEnum> implements IDropList<TEnum> {
    private GetElementType button = new GetElementType();

    public DropList() {
        super();
    }

    public DropList(By valueLocator) {
        super(valueLocator);
    }

    public DropList(By valueLocator, By optionsNamesLocator) {
        this(valueLocator, optionsNamesLocator, null);
    }

    public DropList(By valueLocator, By optionsNamesLocator, By allOptionsNamesLocator) {
        super(optionsNamesLocator, allOptionsNamesLocator);
        this.button = new GetElementType(valueLocator);
    }

    protected Clickable button() {
        return button.get(new Clickable(), getAvatar());
    }

    protected void expandAction(String name) {
        if (!isDisplayedAction(name)) button().click();
    }

    protected void expandAction(int index) {
        if (!isDisplayedAction(index)) button().click();
    }

    @Override
    protected void selectListAction(String... names) {
        if (names == null || names.length == 0)
            return;
        if (button() != null) {
            expandAction(names[0]);
            super.selectListAction(names);
        } else
            for (String name : names)
                getSelector().selectByVisibleText(name);
    }

    @Override
    protected void selectListAction(int... indexes) {
        if (indexes == null || indexes.length == 0)
            return;
        if (button() != null) {
            expandAction(indexes[0]);
            super.selectListAction(indexes);
        } else
            for (int index : indexes)
                getSelector().selectByIndex(index);
    }

    @Override
    protected void clearAction() {
        if (button() != null)
            expandAction(1);
        super.clearAction();
    }

    @Override
    protected String getValueAction() {
        return getTextAction();
    }

    protected String getTextAction() {
        String getValue = getWebElement().getAttribute("value");
        String getText = getWebElement().getText();
        return getText.equals("") && getValue != null ? getValue : getText;
    }

    @Override
    public void waitDisplayed() {
        button().waitDisplayed();
    }

    @Override
    public void waitVanished() {
        button().waitVanished();
    }

    public void wait(Function<WebElement, Boolean> resultFunc) {
        button().wait(resultFunc);
    }

    public <T> T wait(Function<WebElement, T> resultFunc, Function<T, Boolean> condition) {
        return button().wait(resultFunc, condition);
    }

    public void wait(Function<WebElement, Boolean> resultFunc, int timeoutSec) {
        button().wait(resultFunc, timeoutSec);
    }

    public <T> T wait(Function<WebElement, T> resultFunc, Function<T, Boolean> condition, int timeoutSec) {
        return button().wait(resultFunc, condition, timeoutSec);
    }

    public void setAttribute(String attributeName, String value) {
        button().setAttribute(attributeName, value);
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

    public WebElement getWebElement() {
        return new Element(getLocator()).getWebElement();
    }

    public String getAttribute(String name) {
        return button().getAttribute(name);
    }

    public void waitAttribute(String name, String value) {
        button().waitAttribute(name, value);
    }
}