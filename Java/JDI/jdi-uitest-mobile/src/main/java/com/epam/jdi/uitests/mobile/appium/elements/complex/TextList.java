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


import com.epam.commons.PrintUtils;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.ITextList;
import com.epam.jdi.uitests.mobile.appium.elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.*;
import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.core.logger.LogLevels.DEBUG;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/3/2015.
 */
public class TextList<TEnum extends Enum> extends BaseElement implements ITextList<TEnum> {
    public TextList() {
    }

    public TextList(By byLocator) {
        super(byLocator);
    }

    public List<WebElement> getWebElements() {
        return invoker.doJActionResult("Get web elements " + this.toString(), avatar::getElements,
                els -> format("Got %s Element(s)", els.size()), DEBUG);
    }

    public boolean waitVanishedAction() {
        return actions.findImmediately(() ->
                timer().wait(() -> {
                    List<WebElement> elements = getWebElements();
                    if (elements == null || elements.size() == 0)
                        return true;
                    for (WebElement el : getWebElements())
                        if (el.isDisplayed()) return false;
                    return true;
                }), false);
    }

    protected boolean isDisplayedAction() {
        return actions.findImmediately(() -> where(getWebElements(), WebElement::isDisplayed).size() > 0, false);
    }

    public boolean waitDisplayedAction() {
        return timer().wait(() -> any(getWebElements(), el -> !el.isDisplayed()));
    }

    public boolean isDisplayed() {
        return actions.isDisplayed(this::isDisplayedAction);
    }

    public boolean isHidden() {
        return actions.isDisplayed(() -> !isDisplayedAction());
    }

    public void waitDisplayed() {
        actions.waitDisplayed(this::waitDisplayedAction);
    }

    public void waitVanished() {
        actions.waitVanished(this::waitVanishedAction);
    }

    public WebElement getElement(String name) {
        return first(getWebElements(), el -> el.getText().equals(name));
    }

    public WebElement getElement(int index) {
        return getWebElements().get(index);
    }

    public WebElement getElement(TEnum enumName) {
        return getElement(getEnumValue(enumName));
    }

    protected MapArray<String, WebElement> getElementsAction() {
        try {
            return new MapArray<>(getWebElements(), WebElement::getText, value -> value);
        } catch (Exception ex) {
            throw exception(ex.getMessage());
        }
    }

    protected List<String> getLabelsAction() {
        return (List<String>) getElementsAction().keys();
    }

    public final MapArray<String, WebElement> getElements() {
        return invoker.doJActionResult("Get elements", this::getElementsAction);
    }

    public final List<String> getLabels() {
        return invoker.doJActionResult("Get names", this::getLabelsAction);
    }

    protected String getTextAction(WebElement element) {
        return element.getText();
    }

    public final String getText(String name) {
        return invoker.doJActionResult(format("Get text for Element '%s' with name '%s'", this.toString(), name),
                () -> getTextAction(getElement(name)));
    }

    public final String getText(int index) {
        return invoker.doJActionResult(format("Get text for Element '%s' with index '%s'", this.toString(), index),
                () -> getTextAction(getElement(index)));
    }

    public final String getText(TEnum enumName) {
        return getText(getEnumValue(enumName));
    }

    public final int count() {
        return getElements().size();
    }

    protected String getValueAction() {
        return print(select(getWebElements(), WebElement::getText));
    }

    public final String getValue() {
        return invoker.doJActionResult("Get value", this::getValueAction);
    }

    public final List<String> waitText(String str) {
        if (timer().wait(() -> select(getWebElements(), WebElement::getText).contains(str)))
            return getLabels();
        else {
            throw exception("Wait Text Failed");
        }
    }

    public List<String> getTextList() {
        return invoker.doJActionResult("Get list of texts", () -> select(getWebElements(), WebElement::getText),
                PrintUtils::print);
    }

    public String getFirstText() {
        List<String> results = getTextList();
        return (results != null && results.size() > 0)
                ? results.get(0)
                : null;
    }

    public String getLastText() {
        List<String> results = getTextList();
        return (results != null && results.size() > 0)
                ? results.get(results.size() - 1)
                : null;
    }
}