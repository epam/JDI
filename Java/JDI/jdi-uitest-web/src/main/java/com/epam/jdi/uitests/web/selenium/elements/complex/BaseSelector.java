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


import com.epam.jdi.uitests.core.interfaces.base.IVisible;
import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.base.IHasElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.ReflectionUtils.isInterface;
import static com.epam.commons.Timer.waitCondition;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.fillByTemplate;

/**
 * Created by Roman_Iovlev on 7/9/2015.
 */

abstract class BaseSelector<TEnum extends Enum> extends BaseElement implements IVisible, IHasElement {
    protected boolean isSelector;
    protected GetElementType allLabels = new GetElementType();

    BaseSelector() {
        super();
    }

    BaseSelector(By optionsNamesLocator) {
        super(optionsNamesLocator);
    }

    BaseSelector(By optionsNamesLocator, By allLabelsLocator) {
        super(optionsNamesLocator);
        this.allLabels = new GetElementType(allLabelsLocator, this);
    }

    protected TextList allLabels() {
        return allLabels.get(TextList.class);
    }

    protected void selectAction(String name) {
        Clickable cl = getElement(name);
        if (cl != null)
            cl.click();
    }
    protected Clickable getElement(String name) {
        if (!hasLocator() && allLabels() == null)
            throw exception("Can't find option '%s'. No optionsNamesLocator and allLabelsLocator found", name);
        if (hasLocator() && getLocator().toString().contains("%s"))
            return new GetElementType(fillByTemplate(getLocator(), name), this).get(Clickable.class);
        if (allLabels() != null)
            return allLabels().getLocator().toString().contains("%s")
                ? new GetElementType(fillByTemplate(allLabels().getLocator(), name), this).get(Clickable.class)
                : getFromList(allLabels().avatar.searchAll().getElements(), name);
        List<WebElement> elements = getAvatar().searchAll().getElements();
        WebElement element = elements.get(0);
        if (elements.size() == 1 && element.getTagName().equals("select"))
            if (getSelector().getOptions().size() > 0) {
                getSelector().selectByVisibleText(name);
                return null;
            }
            else throw exception("<select> tag has no <option> tags. Please Clarify element locator (%s)", this);
        if (elements.size() == 1 && element.getTagName().equals("ul"))
            elements = element.findElements(By.tagName("li"));
        return getFromList(elements, name);
    }

    private Clickable getFromList(List<WebElement> els, String name) {
        WebElement element = first(els, el -> el.getText().toLowerCase().trim().equals(name.toLowerCase().trim()));
        if (element == null)
            throw exception("Can't find option '%s'. Please fix allLabelsLocator", name);
        Clickable cl = new Clickable(element);
        cl.setParent(getParent());
        return cl;
    }
    public WebElement getWebElement() {
        if (avatar.hasWebElement())
            return avatar.getElement();
        Object parent = getParent();
        return parent != null && isInterface(parent.getClass(), IHasElement.class)
                ? ((IHasElement)parent).getWebElement()
                : null;
    }
    public void setWebElement(WebElement webElement) {
        Element element = new Element();
        element.setParent(getParent());
        element.setWebElement(webElement);
        setParent(element);
    }

    protected void selectAction(int num) {
        Clickable cl = getElement(num);
        if (cl != null)
            cl.click();
    }
    protected Clickable getElement(int num) {
        if (!hasLocator() && allLabels() == null)
            throw exception("Can't find option '%s'. No optionsNamesLocator and allLabelsLocator found", num);
        if (allLabels() != null)
            return selectFromList(allLabels().avatar.searchAll().getElements(), num);
        if (getLocator().toString().contains("%s"))
            return new GetElementType(fillByTemplate(getLocator(), num), this).get(Clickable.class);
        List<WebElement> elements = getAvatar().searchAll().getElements();
        WebElement element = elements.get(0);
        if (elements.size() == 1 && element.getTagName().equals("select"))
            if (getSelector().getOptions().size() > 0) {
                getSelector().selectByIndex(num - 1);
                return null;
            }
            else throw exception("<select> tag has no <option> tags. Please Clarify element locator (%s)", this);
        if (elements.size() == 1 && element.getTagName().equals("ul"))
            elements = element.findElements(By.tagName("li"));
        return selectFromList(elements, num);
    }

    private Clickable selectFromList(List<WebElement> els, int num) {
        if (num <= 0)
            throw exception("Can't get option with num '%s'. Number should be 1 or more", num);
        if (els == null)
            throw exception("Can't find option with num '%s'. Please fix allLabelsLocator", num);
        if (els.size() < num)
            throw exception("Can't find option with num '%s'. Find only '%s' options", num, els.size());
        return new Clickable(els.get(num - 1));
    }

    protected abstract boolean isSelectedAction(String name);

    protected abstract boolean isSelectedAction(int num);

    protected boolean isSelectedAction(WebElement el) {
        if (isSelector)
            return el.isSelected();
        String attr = el.getAttribute("checked");
        return attr != null && attr.equals("true");
    }

    /**
     * @param name Specify name using string
     * Wait while option (from text) is selected. Return false if this not happens
     */
    public final void waitSelected(String name) {
        actions.isSelected(name, n -> waitCondition(() -> isSelectedAction(n)));
    }

    /**
     * @param name Specify name using enum
     * Wait while option (from enum) is selected. Return false if this not happens
     */
    public final void waitSelected(TEnum name) {
        waitSelected(getEnumValue(name));
    }

    /**
     * @param name Specify name using string
     * @return Is option selected?
     */
    public final boolean isSelected(String name) {
        return actions.isSelected(name, this::isSelectedAction);
    }

    /**
     * @param name Specify name using enum
     * @return Is option selected?
     */
    public final boolean isSelected(TEnum name) {
        return isSelected(getEnumValue(name));
    }

    protected List<String> getOptionsAction() {
        return select(getElements(), WebElement::getText);
    }

    protected abstract String getValueAction();

    protected void setValueAction(String value) {
        selectAction(value);
    }

    /**
     * @return Get value of Element
     */
    public final String getValue() {
        return actions.getValue(this::getValueAction);
    }

    /**
     * @param value Specify element value
     *              Set value to Element
     */
    public final void setValue(String value) {
        actions.setValue(value, this::setValueAction);
    }

    /**
     * @return Get labels of all options
     */
    public final List<String> getOptions() {
        return getOptionsAction();
    }

    protected Select getSelector() {
        isSelector = true;
        return new Select(new Element(getLocator()).getWebElement());
    }

    protected List<WebElement> getElements() {
        if (!hasLocator() && allLabels() == null)
            throw exception("Can't check is element displayed or not. No optionsNamesLocator and allLabelsLocator found");
        if (allLabels() != null)
            try { return allLabels().avatar.searchAll().getElements(); }
            catch(Exception | Error ignore) { return new ArrayList<>(); }
        if (getLocator().toString().contains("%s"))
            throw exception("Can't check is element displayed or not. Please specify allLabelsLocator or correct optionsNamesLocator (should not contain '%s')");
        try { return getElementsFromTag(); }
        catch(Exception | Error ignore) { return new ArrayList<>(); }
    }

    public List<WebElement> getElementsFromTag() {
        List<WebElement> elements;
        try {
            elements = getAvatar().searchAll().getElements();
        } catch (Exception | Error ex) {
            return new ArrayList<>();
        }
        WebElement element = elements.get(0);
        if (elements.size() == 1)
            switch (element.getTagName()) {
                case "select":
                    return getSelector().getOptions();
                case "ul":
                    return element.findElements(By.tagName("li"));
            }
        return elements;
    }

    public WebElement getWebElement(String name) {
        return getElement(name).getWebElement();
    }

    protected boolean isDisplayedAction(String name) {
        WebElement element = getWebElement(name);
        return element != null && element.isDisplayed();
    }

    protected boolean isDisplayedAction(int num) {
        return isDisplayedInList(getElements(), num);
    }

    private boolean isDisplayedInList(List<WebElement> els, int num) {
        if (num <= 0)
            throw exception("Can't get option with num '%s'. Number should be 1 or more", num);
        if (els == null)
            throw exception("Can't find option with num '%s'. Please fix allLabelsLocator", num);
        if (els.size() < num)
            throw exception("Can't find option with num '%s'. Find '%s' options", num, els.size());
        return els.get(num - 1).isDisplayed();
    }

    protected boolean isDisplayedAction() {
        List<WebElement> els = avatar.findImmediately(this::getElements, null);
        return els != null && !els.isEmpty() && els.get(0).isDisplayed();
    }

    protected boolean waitDisplayedAction() {
        return timer().wait(() -> {
            List<WebElement> els = getElements();
            return els != null && !els.isEmpty() && els.get(0).isDisplayed();
        });
    }

    protected boolean waitVanishedAction() {
        return timer().wait(() -> !isDisplayedAction());
    }

    /**
     * @return Check is Element visible
     */
    public boolean isDisplayed() {
        return actions.isDisplayed(this::isDisplayedAction);
    }
    public boolean isDisplayed(String name) {
        return actions.isDisplayed(() -> isDisplayedAction(name));
    }
    public boolean isDisplayed(int num) {
        return actions.isDisplayed(() -> isDisplayedAction(num));
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
        actions.waitVanished(() -> timer().wait(() -> !isDisplayedAction()));
    }
}