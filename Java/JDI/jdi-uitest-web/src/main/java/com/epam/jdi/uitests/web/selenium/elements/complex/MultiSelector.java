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


import com.epam.commons.EnumUtils;
import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.core.interfaces.base.IMultiSelector;
import com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.commons.LinqUtils.*;
import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by roman.i on 03.10.2014.
 */

public abstract class MultiSelector<TEnum extends Enum> extends BaseSelector<TEnum> implements IMultiSelector<TEnum> {
    private String separator = ", ";

    public MultiSelector() {
        super();
    }

    public MultiSelector(By optionsNamesLocator) {
        super(optionsNamesLocator);
    }

    public MultiSelector(By optionsNamesLocator, By allLabelsLocator) {
        super(optionsNamesLocator, allLabelsLocator);
    }

    protected void clearAction() {
        if (!hasLocator() && allLabels() == null)
            throw exception("Can't clear options. No optionsNamesLocator and allLabelsLocator found");
        if (getLocator().toString().contains("%s"))
            throw exception("Can't clear options. Specify allLabelsLocator or fix optionsNamesLocator (should not contain '%s')");
        if (allLabels() != null) {
            clearElements(allLabels().getWebElements());
            return;
        }
        List<WebElement> elements = getAvatar().searchAll().getElements();
        WebElement element = elements.get(0);
        if (elements.size() == 1 && element.getTagName().equals("select"))
            if (getSelector().getOptions().size() > 0) {
                getSelector().deselectAll();
                return;
            }
            else throw exception("<select> tag has no <option> tags. Please Clarify element locator (%s)", this);
        if (elements.size() == 1 && element.getTagName().equals("ul"))
            elements = element.findElements(By.tagName("li"));
        clearElements(elements);
    }

    private void clearElements(List<WebElement> els) {
        foreach(where(els, el -> isSelectedAction(el.getText())), WebElement::click);
    }

    protected WebElement getElement(String name) {
        if (!hasLocator() && allLabels() == null)
            throw exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
        if (getLocator().toString().contains("%s"))
            return new GetElementModule(WebDriverByUtils.fillByTemplate(getLocator(), name), this).getElements().get(0);
        if (allLabels() != null)
            return getElement(allLabels().getWebElements(), name);
        return getElement(getElementsFromTag(), name);
    }

    private WebElement getElement(List<WebElement> els, String name) {
        if (els == null)
            throw exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
        List<WebElement> elements = where(els, el -> el.getText().equals(name));
        if (elements != null && elements.size() == 1)
            return elements.get(0);
        throw exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
    }

    protected WebElement getElement(int index) {
        if (!hasLocator() && allLabels() == null)
            throw exception("Can't get option. No optionsNamesLocator and allLabelsLocator found");
        if (getLocator().toString().contains("%s"))
            throw exception("Can't get options. Specify allLabelsLocator or fix optionsNamesLocator (should not contain '%s')");
        if (allLabels() != null)
            return getElement(allLabels().getWebElements(), index);
        return getElement(getElementsFromTag(), index);
    }

    private WebElement getElement(List<WebElement> els, int index) {
        if (index <= 0)
            throw exception("Can't get option with index '%s'. Index should be 1 or more", index);
        if (index > els.size())
            throw exception("Can't get option with index '%s'. Found only %s options", index, els.size());
        return els.get(index - 1);
    }

    protected boolean isSelectedAction(String name) {
        return isSelectedAction(getElement(name));
    }

    protected boolean isSelectedAction(int index) {
        return isSelectedAction(getElement(index));
    }

    protected void selectListAction(String... names) {
        foreach(names, this::selectAction);
    }

    protected void selectListAction(int... indexes) {
        for (int i : indexes) selectAction(i);
    }

    protected String getValueAction() {
        return print(areSelected());
    }

    @Override
    protected void setValueAction(String value) {
        selectListAction(value.split(separator));
    }

    public IMultiSelector<TEnum> setValuesSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    public final void select(String... names) {
        actions.select(this::selectListAction, names);
    }

    public final void select(TEnum... names) {
        select(toStringArray(LinqUtils.select(names, EnumUtils::getEnumValue)));
    }

    public final void select(int... indexes) {
        actions.select(this::selectListAction, indexes);
    }

    public final void check(String... names) {
        clear();
        select(names);
    }

    public final void check(TEnum... names) {
        clear();
        select(names);
    }

    public final void check(int... indexes) {
        clear();
        select(indexes);
    }

    public final void uncheck(String... names) {
        checkAll();
        select(names);
    }

    public final void uncheck(TEnum... names) {
        checkAll();
        select(names);
    }

    public final void uncheck(int... indexes) {
        checkAll();
        select(indexes);
    }

    public final List<String> areSelected() {
        return actions.areSelected(this::getNames, this::isSelectedAction);
    }

    public final void waitSelected(TEnum... names) {
        waitSelected(toStringArray(LinqUtils.select(names, EnumUtils::getEnumValue)));
    }

    public final void waitSelected(String... names) {
        actions.waitSelected(n -> timer().wait(() -> isSelectedAction(n)), names);
    }

    public final List<String> areDeselected() {
        return actions.areDeselected(this::getNames, n -> timer().wait(() -> isSelectedAction(n)));
    }

    public final void waitDeselected(TEnum... names) {
        waitDeselected(toStringArray(LinqUtils.select(names, EnumUtils::getEnumValue)));
    }

    public final void waitDeselected(String... names) {
        actions.waitDeselected(n -> timer().wait(() -> isSelectedAction(n)), names);
    }

    public void clear() {
        invoker.doJAction("Clear Options", this::clearAction);
    }

    public void checkAll() {
        foreach(where(getOptions(), label -> !isSelectedAction(label)), this::selectAction);
    }

}