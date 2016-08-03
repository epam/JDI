package com.epam.jdi.uitests.testing.career.page_objects.site.CustomElements;
/*
 * Created by Roman_Iovlev on 6/20/2016
 *
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
 * Site:
 * Github:
 * Contacts:
 */

import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.epam.commons.LinqUtils.first;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.correctXPaths;

public class TreeDropdown<T extends Enum> extends Dropdown<T> {
    public List<By> treeLocators;
    public TreeDropdown() {
        super();
    }

    public TreeDropdown(By selectLocator) {
        super(selectLocator);
    }

    public TreeDropdown(By selectLocator, List<By> treeLocators) {
        super(selectLocator, selectLocator, null);
        this.treeLocators = treeLocators;
    }

    protected void expandAction() {
        if (getDriver().findElements(treeLocators.get(0)).size() == 0)
            element().click();
    }

    @Override
    protected void selectAction(String name) {
        expandAction();
        String[] nodes = name.split(" > ");
        SearchContext context = getDriver();
        if (treeLocators.size() >= nodes.length)
            for(int i=0; i < nodes.length; i++) {
                String value = nodes[i];
                context = first(context.findElements(correctXPaths(treeLocators.get(i))), el -> el.getText().equals(value));
                ((WebElement) context).click();
            }
    }
}