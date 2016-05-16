package com.epam.jdi.uitests.web.selenium.elements.common;
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


import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Text Field control implementation
 *
 * @author Alexeenko Yan
 * @author Shubin Konstantin
 * @author Zharov Alexandr
 */
public class TextField extends Text implements ITextField {
    public TextField() {
        super();
    }

    public TextField(By byLocator) {
        super(byLocator);
    }

    public TextField(WebElement webElement) {
        super(webElement);
    }

    protected void setValueAction(String value) {
        newInput(value);
    }

    protected void inputAction(CharSequence text) {
        getWebElement().sendKeys(text);
    }

    protected void clearAction() {
        getWebElement().clear();
    }

    protected void focusAction() {
        getWebElement().click();
    }

    public final void setValue(String value) {
        actions.setValue(value, this::setValueAction);
    }

    public final void input(CharSequence text) {
        actions.input(text, this::inputAction);
    }

    public void newInput(CharSequence text) {
        clear();
        input(text);
    }

    public final void clear() {
        actions.clear(this::clearAction);
    }

    public final void focus() {
        actions.focus(this::focusAction);
    }

}