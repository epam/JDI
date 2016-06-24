package com.epam.jdi.uitests.win.winium.elements.common;
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


import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.win.winium.elements.base.Clickable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Image control implementation
 *
 * @author Alexeenko Yan
 */
public class Image extends Clickable implements IImage {
    public Image() {
    }

    public Image(By byLocator) {
        super(byLocator);
    }

    public Image(WebElement webElement) {
        super(webElement);
    }

    public String getSource() {
        return invoker.doJActionResult("Get image source for Element " + this,
                () -> getWebElement().getAttribute("src"));
    }

    public String getAlt() {
        return invoker.doJActionResult("Get image title for Element " + this,
                () -> getWebElement().getAttribute("alt"));
    }

}