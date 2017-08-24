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


import com.epam.commons.PrintUtils;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.ITextList;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
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
        return avatar.findImmediately(() ->
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
        return avatar.findImmediately(() -> where(getWebElements(), WebElement::isDisplayed).size() > 0, false);
    }

    public boolean waitDisplayedAction() {
        return timer().wait(() -> any(getWebElements(), el -> !el.isDisplayed()));
    }

    /**
     * @return Check is Element visible
     */
    public boolean isDisplayed() {
        return actions.isDisplayed(this::isDisplayedAction);
    }

    /**
     * @return Check is Element hidden
     */
    public boolean isHidden() {
        return actions.isDisplayed(() -> !isDisplayedAction());
    }

    /**
     * Waits while Element becomes visible
     */
    public void waitDisplayed() {
        actions.waitDisplayed(this::waitDisplayedAction);
    }

    /**
     * Waits while Element becomes invisible
     */
    public void waitVanished() {
        actions.waitVanished(this::waitVanishedAction);
    }

    public WebElement getElement(String name) {
        Selector selector = new Selector(getLocator());
        selector.setParent(getParent());
        return selector.getWebElement(name);
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

    /**
     * @param name Specify string by String mechanic
     * @return Get textList’s text by specified param
     */
    public final String getText(String name) {
        return invoker.doJActionResult(String.format("Get text for Element '%s' with name '%s'", this.toString(), name),
                () -> getTextAction(getElement(name)));
    }

    /**
     * @param index Specify string by Integer mechanic
     * @return Get textList’s text by specified param
     */
    public final String getText(int index) {
        return invoker.doJActionResult(String.format("Get text for Element '%s' with index '%s'", this.toString(), index),
                () -> getTextAction(getElement(index)));
    }

    /**
     * @param enumName Specify string by Enum mechanic
     * @return Get textList’s text by specified param
     */
    public final String getText(TEnum enumName) {
        return getText(getEnumValue(enumName));
    }

    /**
     * @return Returns strings count
     */
    public final int count() {
        return getElements().size();
    }

    protected String getValueAction() {
        return print(select(getWebElements(), WebElement::getText));
    }

    /**
     * @return Get value of Element
     */
    public final String getValue() {
        return invoker.doJActionResult("Get value", this::getValueAction);
    }

    /**
     * @return Wait while TextList’s text contains expected text. Returns Element’s text
     */
    public final List<String> waitText(String str) {
        if (timer().wait(() -> select(getWebElements(), WebElement::getText).contains(str)))
            return getLabels();
        throw exception("Wait Text Failed (%s)", toString());
    }

    /**
     * @return Return list of strings of TextList
     */
    public List<String> getTextList() {
        return invoker.doJActionResult("Get list of texts", () -> select(getWebElements(), WebElement::getText),
                PrintUtils::print);
    }

    /**
     * @return Return first String in list
     */
    public String getFirstText() {
        List<String> results = getTextList();
        return (results != null && results.size() > 0)
                ? results.get(0)
                : null;
    }

    /**
     * @return Return last String in list
     */
    public String getLastText() {
        List<String> results = getTextList();
        return (results != null && results.size() > 0)
                ? results.get(results.size() - 1)
                : null;
    }
}