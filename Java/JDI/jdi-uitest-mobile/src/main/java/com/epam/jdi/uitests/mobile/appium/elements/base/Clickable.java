package com.epam.jdi.uitests.mobile.appium.elements.base;
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


import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/6/2015.
 */
public class Clickable extends Element implements IClickable {
    public Clickable() {
    }

    public Clickable(By byLocator) {
        super(byLocator);
    }

    public Clickable(WebElement webElement) {
        super(webElement);
    }

    protected void clickJSAction() {
        jsExecutor().executeScript("arguments[0].click();", getWebElement());
    }

    protected void clickAction() {
        getWebElement().click();
    }

    public final void click() {
        actions.click(this::clickAction);
    }

    public void clickByXY(int x, int y) {
        invoker.doJAction(format("Click on Element with coordinates (x,y) = (%s, %s)", x, y),
                () -> new Actions(getDriver())
                        .moveToElement(getWebElement(), x, y).click().build().perform());
    }
}