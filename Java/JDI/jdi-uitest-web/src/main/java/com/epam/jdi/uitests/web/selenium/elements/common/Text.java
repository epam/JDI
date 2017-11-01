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
    {
        setPreposition("in");
    }

    public Text() {
    }

    public Text(By byLocator) {
        super(byLocator);
    }

    public Text(WebElement webElement) {
        super(webElement);
    }

    protected String getTextAction() {
        WebElement element = getWebElement();
        String getValue = element.getAttribute("value");
        if (getValue != null && !getValue.equals(""))
            return getValue;
        String getText = element.getText();
        return getText != null
                ? getText
                : getValue;
    }

    protected String getValueAction() {
        return getTextAction();
    }

    /**
     * @return Get value of Element
     */
    public final String getValue() {
        return actions.getValue(this::getValueAction);
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
}