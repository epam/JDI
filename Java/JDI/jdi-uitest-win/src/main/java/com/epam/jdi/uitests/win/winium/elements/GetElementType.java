package com.epam.jdi.uitests.win.winium.elements;
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


import com.epam.jdi.uitests.win.winium.elements.apiInteract.GetElementModule;
import org.openqa.selenium.By;

/**
 * Created by Roman_Iovlev on 9/28/2015.
 */
public class GetElementType {
    private By locator;

    public GetElementType() {
        this(null);
    }

    public GetElementType(By locator) {
        this.locator = locator;
    }

    public <T extends BaseElement> T get(T element, GetElementModule avatar) {
        return locator == null
                ? null
                : (T) element.setAvatar(locator, avatar);
    }
}