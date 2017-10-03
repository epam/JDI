package com.epam.jdi.site.epam.CustomElements;
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

import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.LinqUtils.first;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.correctXPaths;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;

public class TreeDropdown<T extends Enum> extends Dropdown<T> implements ISetup {
    private List<By> treeLocators;
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
        setWaitTimeout(0);
        List<WebElement> els = getDriver().findElements(treeLocators.get(0));
        restoreWaitTimeout();
        if (treeLocators != null && els.size() == 0)
            element().click();
    }

    @Override
    protected void selectAction(String name) {
        expandAction();
        String[] nodes = name.split(" > ");
        SearchContext context = getDriver();
        if (treeLocators.size() < nodes.length) return;
        for(int i=0; i < nodes.length; i++) {
            String value = nodes[i];
            List<WebElement> els = context.findElements(correctXPaths(treeLocators.get(i)));
            if (els.size() == 0)
                throw exception("No elements found for locator: " + treeLocators.get(i) + "in TreeDropdown " + this);
            context = first(els, el -> el.getText().equals(value));
            if (context == null)
                throw exception("Can't find: " + value + "in TreeDropdown " + this);
            if (i < nodes.length - 1) {
                int next = i + 1;
                boolean nextValue =
                        LinqUtils.any(context.findElements(correctXPaths(treeLocators.get(next))), el -> el.getText().equals(nodes[next]));
                if (nextValue) continue;
            }
            ((WebElement) context).click();
        }
    }
    @Override
    public void setup(Field field) {
        if (!fieldHasAnnotation(field, JTree.class, IDropDown.class))
            return;
        JTree jTree = field.getAnnotation(JTree.class);
        By selectLocator = findByToBy(jTree.select());
        avatar = new GetElementModule(selectLocator, this);
        //element = new GetElementType(selectLocator, this);
        //expander = new GetElementType(selectLocator, this);
        treeLocators = new ArrayList<>();
        for (FindBy fBy : jTree.levels())
            treeLocators.add(findByToBy(fBy));
    }

}