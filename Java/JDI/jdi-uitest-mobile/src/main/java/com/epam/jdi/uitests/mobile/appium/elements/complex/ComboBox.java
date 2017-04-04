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
import com.epam.jdi.uitests.mobile.appium.elements.common.Label;
import com.epam.jdi.uitests.mobile.appium.elements.common.TextField;
import org.openqa.selenium.By;

import static com.epam.web.matcher.junit.Assert.exception;

/**
 * ComboBox control implementation
 *
 * @author Alexeenko Yan
 */
public class ComboBox<TEnum extends Enum> extends Dropdown<TEnum> implements IComboBox<TEnum> {
    private GetElementType textField = new GetElementType();
    public By labelLocator;

    public ComboBox() {
        super();
    }

    public ComboBox(By valueLocator) {
        super(valueLocator);
        textField = new GetElementType(valueLocator, this);
    }

    public ComboBox(By selectorLocator, By optionsNamesLocatorTemplate) {
        super(selectorLocator, optionsNamesLocatorTemplate);
        textField = new GetElementType(selectorLocator, this);
    }

    public ComboBox(By selectorLocator, By optionsNamesLocatorTemplate, By valueLocator) {
        super(selectorLocator, optionsNamesLocatorTemplate);
        textField = new GetElementType(valueLocator, this);
    }

    public ComboBox(By selectorLocator, By optionsNamesLocatorTemplate, By valueLocator, By allOptionsNamesLocator) {
        super(selectorLocator, optionsNamesLocatorTemplate, allOptionsNamesLocator);
        textField = new GetElementType(valueLocator, this);
    }

    public String label() {
        if (labelLocator == null)
            throw exception("Label locator no specified");
        Label label = new Label(labelLocator);
        label.setParent(getParent());
        return label.getText();
    }

    protected TextField textField() {
        return textField.get(TextField.class);
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

    /**
     * @param text Specify text to input to TextField
     *             Input text in textfield
     */
    public final void input(CharSequence text) {
        actions.input(text, this::inputAction);
    }

    /**
     * @param text Specify text to send keys to TextField
     *             Input text in textfield
     */
    public void sendKeys(CharSequence text) {
        input(text);
    }

    /**
     * @param text Specify text to input to TextField
     *             Clear and input text in textfield
     */
    public void newInput(CharSequence text) {
        clear();
        input(text);
    }

    /**
     * Clear textfield
     */
    public final void clear() {
        actions.clear(this::clearAction);
    }

    /**
     * Focus(click) on textfield
     */
    public final void focus() {
        actions.focus(this::focusAction);
    }

}