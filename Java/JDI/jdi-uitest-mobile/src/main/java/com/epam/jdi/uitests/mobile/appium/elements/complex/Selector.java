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


import com.epam.jdi.uitests.core.interfaces.complex.ISelector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.firstIndex;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by roman.i on 03.10.2014.
 */

public class Selector<TEnum extends Enum> extends BaseSelector<TEnum> implements ISelector<TEnum> {
    public Selector() {
        super();
    }

    public Selector(By optionsNamesLocatorTemplate) {
        super(optionsNamesLocatorTemplate);
    }

    public Selector(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) {
        super(optionsNamesLocatorTemplate, allOptionsNamesLocator);
    }

    public final void select(String name) {
        actions.select(name, this::selectAction);
    }

    public final void select(TEnum name) {
        select(getEnumValue(name));
    }

    public final void select(int index) {
        actions.select(index, this::selectAction);
    }

    public final String getSelected() {
        return actions.getSelected(this::getSelectedAction);
    }

    public final int getSelectedIndex() {
        return actions.getSelectedIndex(this::getSelectedIndexAction);
    }

    protected final boolean isSelectedAction(String name) {
        return getSelectedAction().equals(name);
    }

    protected final boolean isSelectedAction(int index) {
        return getSelectedIndexAction() == index;
    }

    protected String getValueAction() {
        return getSelected();
    }

    protected String getSelectedAction() {
        if (allLabels() != null)
            return getSelected(allLabels().getWebElements());
        if (getLocator().toString().contains("%s"))
            throw exception("Can't get Selected options. Override getSelectedAction or place locator to <select> tag");
        List<WebElement> els = getAvatar().searchAll().getElements();
        if (els.size() == 1)
            return getSelected(getSelector().getOptions());
        else
            return getSelected(els);
    }

    private String getSelected(List<WebElement> els) {
        WebElement element = first(els, this::isSelectedAction);
        if (element == null)
            throw exception("No elements selected. Override getSelectedAction or place locator to <select> tag");
        return element.getText();
    }

    protected int getSelectedIndexAction() {
        if (allLabels() != null) {
            return getSelectedIndex(allLabels().getWebElements());
        }
        if (getLocator().toString().contains("%s"))
            throw exception("Can't get Selected options. Override getSelectedAction or place locator to <select> tag");
        List<WebElement> els = getAvatar().searchAll().getElements();
        if (els.size() == 1)
            return getSelectedIndex(getSelector().getOptions());
        else
            return getSelectedIndex(els);
    }

    private int getSelectedIndex(List<WebElement> els) {
        int index = firstIndex(els, this::isSelectedAction) + 1;
        if (index == 0)
            throw exception("No elements selected. Override getSelectedAction or place locator to <select> tag");
        return index;
    }
}