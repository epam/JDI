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


import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.mobile.appium.elements.GetElementType;
import com.epam.jdi.uitests.mobile.appium.elements.common.TextField;
import org.openqa.selenium.By;

/**
 * ComboBox control implementation
 *
 * @author Alexeenko Yan
 */
public class ComboBox<TEnum extends Enum> extends Dropdown<TEnum> implements IComboBox<TEnum> {
    private GetElementType textField = new GetElementType();

    public ComboBox() {
        super();
    }

    public ComboBox(By valueLocator) {
        super(valueLocator);
        textField = new GetElementType(valueLocator);
    }

    public ComboBox(By selectorLocator, By optionsNamesLocatorTemplate) {
        super(selectorLocator, optionsNamesLocatorTemplate);
        textField = new GetElementType(selectorLocator);
    }

    public ComboBox(By selectorLocator, By optionsNamesLocatorTemplate, By valueLocator) {
        super(selectorLocator, optionsNamesLocatorTemplate);
        textField = new GetElementType(valueLocator);
    }

    public ComboBox(By selectorLocator, By optionsNamesLocatorTemplate, By valueLocator, By allOptionsNamesLocator) {
        super(selectorLocator, optionsNamesLocatorTemplate, allOptionsNamesLocator);
        textField = new GetElementType(valueLocator);
    }

    protected TextField textField() {
        return textField.get(new TextField(), getAvatar());
    }

    @Override
    protected void setValueAction(String value) {
        newInput(value);
    }

    @Override
    protected String getTextAction() {
        return textField().getText();
    }

    protected void inputAction(CharSequence text) {
        textField().sendKeys(text);
    }

    protected void clearAction() {
        textField().clear();
    }

    protected void focusAction() {
        textField().focus();
    }

    public final void input(CharSequence text) {
        actions.input(text, this::inputAction);
    }

    public void sendKeys(CharSequence text) {
        input(text);
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