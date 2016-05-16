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


import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by Roman_Iovlev on 7/6/2015.
 */
public class Text extends Element implements IText {
    public Text() {
    }

    public Text(By byLocator) {
        super(byLocator);
    }

    public Text(WebElement webElement) {
        super(webElement);
    }

    protected String getTextAction() {
        String getValue = getWebElement().getAttribute("value");
        String getText = getWebElement().getText();
        return getText.equals("") && getValue != null ? getValue : getText;
    }

    protected String getValueAction() {
        return getTextAction();
    }

    public final String getValue() {
        return actions.getValue(this::getValueAction);
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
}