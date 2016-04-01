package com.epam.jdi.uitests.mobile.appium.elements.common;
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


import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import com.epam.jdi.uitests.mobile.appium.elements.base.Clickable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Checkbox control implementation
 *
 * @author Alexeenko Yan
 * @author Belousov Andrey
 */
public class CheckBox extends Clickable implements ICheckBox {
    private Function<WebElement, Boolean> isSelected = WebElement::isSelected;
    private Function<WebElement, Boolean> isChecked = el -> el.getAttribute("checked") != null;
    private Function<WebElement, Boolean> isCheckedFunc = el -> {
        if (isSelected.apply(el)) {
            isCheckedFunc = isSelected;
            return true;
        }
        if (isChecked.apply(el)) {
            isCheckedFunc = isChecked;
            return true;
        }
        return false;
    };

    public CheckBox() {
    }

    public CheckBox(By byLocator) {
        super(byLocator);
    }

    public CheckBox(WebElement webElement) {
        super(webElement);
    }

    protected String getValueAction() {
        return isChecked() + "";
    }

    protected void setValueAction(String value) {
        switch (value.toLowerCase()) {
            case "true":
            case "1":
            case "check":
                check();
                break;
            case "false":
            case "0":
            case "uncheck":
                uncheck();
                break;
        }
    }

    protected void checkAction() {
        if (!isCheckedAction())
            clickAction();
        if (!isCheckedAction())
            throw exception("Can't check element. Verify locator for click or isCheckedAction");
    }

    protected void uncheckAction() {
        if (isCheckedAction())
            clickAction();
        if (isCheckedAction())
            throw exception("Can't uncheck element. Verify locator for click or isCheckedAction");
    }

    protected boolean isCheckedAction() {
        return isCheckedFunc.apply(getWebElement());
    }

    public final void check() {
        actions.check(this::checkAction);
    }

    public final void uncheck() {
        actions.uncheck(this::uncheckAction);
    }

    public final boolean isChecked() {
        return actions.isChecked(this::isCheckedAction);
    }

    public final String getValue() {
        return actions.getValue(this::getValueAction);
    }

    public final void setValue(String value) {
        actions.setValue(value, this::setValueAction);
    }
}