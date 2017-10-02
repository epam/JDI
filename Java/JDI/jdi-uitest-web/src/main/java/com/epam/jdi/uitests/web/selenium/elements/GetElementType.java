package com.epam.jdi.uitests.web.selenium.elements;
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


import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;

/**
 * Created by Roman_Iovlev on 9/28/2015.
 */
public class GetElementType {
    private By locator;
    private Object parent;

    public GetElementType() { }
    public GetElementType(By locator, Object parent) {
        this.locator = locator;
        this.parent = parent;
    }

    public <T extends BaseElement> T get(Class<T> clazz) {
        try {
            if (locator == null)
                return null;
            else {
                T result = clazz.newInstance();
                Element el = new Element(locator);
                el.setParent(parent);
                result.init(parent, new GetElementModule(locator, el));
                return result;
            }

        } catch (Exception ignore) { return null; }
    }

}