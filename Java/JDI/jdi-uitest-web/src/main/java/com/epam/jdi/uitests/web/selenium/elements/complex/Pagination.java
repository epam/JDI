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


import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.core.annotations.AnnotationsUtil;
import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.core.interfaces.complex.IPagination;
import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JPagination;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;

import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.fillByTemplate;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;
import static java.lang.String.format;

/**
 * Created by Morokov Danila on 15.05.2018.
 */

/**
 * This is the realization for @JPagination annotation
 *
 * {@see} Example of usage in the @JPagination
 */
public class Pagination extends BaseElement implements IPagination, ISetup {
    protected GetElementType root;
    protected GetElementType next;
    protected GetElementType previous;
    protected GetElementType first;
    protected GetElementType last;
    protected GetElementType pages;

    private By rootLocator;
    private By nextLocator;
    private By previousLocator;
    private By firstLocator;
    private By lastLocator;
    private By pagesLocator;

    public Pagination() {
        super();
    }

    public void setup(Field field) {
        if (!fieldHasAnnotation(field, JPagination.class, IPagination.class))
            return;
        JPagination pagination = field.getAnnotation(JPagination.class);

        rootLocator = findByToBy(pagination.root());
        nextLocator = findByToBy(pagination.nextLocator());
        previousLocator = findByToBy(pagination.previousLocator());
        firstLocator = findByToBy(pagination.firstLocator());
        lastLocator = findByToBy(pagination.lastLocator());
        pagesLocator = findByToBy(pagination.pagesLocator());

        if (rootLocator != null) {
            setLocator(rootLocator);
            this.root = new GetElementType(rootLocator, getParent());
        }

        if (nextLocator != null) {
            this.next = new GetElementType(nextLocator, this);
        }

        if (previousLocator != null) {
            this.previous = new GetElementType(previousLocator, this);
        }

        if (firstLocator != null) {
            this.first = new GetElementType(firstLocator, this);
        }

        if (lastLocator != null) {
            this.last = new GetElementType(lastLocator, this);
        }

        if (pagesLocator != null) {
            this.pages = new GetElementType(pagesLocator, this);
        }
    }

    /**
     * Choose Next page
     */
    public void next() {
        invoker.doJAction("Choose Next page", () -> nextAction().click());
    }

    /**
     * Choose Previous page
     */
    public void previous() {
        invoker.doJAction("Choose Previous page", () -> previousAction().click());
    }

    /**
     * Choose First page
     */
    public void first() {
        if (firstLocator != null) {
            invoker.doJAction("Choose First page", () -> firstAction().click());
        }
    }

    /**
     * Choose Last page
     */
    public void last() {
        if (lastLocator != null) {
            invoker.doJAction("Choose Last page", () -> lastAction().click());
        }
    }

    /**
     * @param index Specify page index
     *              Choose page by index
     */
    public void selectPage(int index) {
        if (pagesLocator != null) {
            invoker.doJAction(format("Choose '%s' page", index), () -> pageAction(index).click());
        }
    }

    private Clickable getClickable(String name) {
        List<Field> fields = getFields(this, IClickable.class);
        Field result = LinqUtils.first(fields,
                field -> AnnotationsUtil.getElementName(field).toLowerCase().contains(name.toLowerCase()));
        return (Clickable) getValueField(result, this);
    }

    private String cantChooseElementMsg(String actionName, String shortName, String action) {
        return MessageFormat.format("Can't choose {0} page for Element '{3}'. "
                + "Please specify locator for this action using constructor or add Clickable Element "
                + "on pageObject with name '{1}Link' or '{1}Button' or use locator template with parameter '{1}'"
                + "or override {2}() in class", actionName, shortName, action, toString());
    }

    private Clickable nextAction() {
        String shortName = "next";
        if (nextLocator != null)
            return new GetElementType(nextLocator, this).get(Clickable.class);

        Clickable nextLink = getClickable(shortName);
        if (nextLink != null)
            return nextLink;

        if (getLocator() != null && getLocator().toString().contains("%s"))
            return new GetElementType(fillByTemplate(getLocator(), shortName), this).get(Clickable.class);
        throw exception(cantChooseElementMsg("Next", shortName, "nextAction"));
    }

    private Clickable previousAction() {
        String shortName = "prev";
        if (previousLocator != null)
            return new GetElementType(previousLocator, this).get(Clickable.class);

        Clickable prevLink = getClickable(shortName);
        if (prevLink != null)
            return prevLink;

        if (getLocator() != null && getLocator().toString().contains("%s"))
            return new GetElementType(fillByTemplate(getLocator(), shortName), this).get(Clickable.class);
        throw exception(cantChooseElementMsg("Previous", shortName, "previousAction"));
    }

    private Clickable firstAction() {
        String shortName = "first";
        if (firstLocator != null)
            return new GetElementType(firstLocator, this).get(Clickable.class);

        Clickable firstLink = getClickable(shortName);
        if (firstLink != null)
            return firstLink;

        if (getLocator() != null && getLocator().toString().contains("%s"))
            return new GetElementType(fillByTemplate(getLocator(), shortName), this).get(Clickable.class);
        throw exception(cantChooseElementMsg("First", shortName, "firstAction"));
    }

    private Clickable lastAction() {
        String shortName = "last";
        if (lastLocator != null)
            return new GetElementType(lastLocator, this).get(Clickable.class);

        Clickable lastLink = getClickable(shortName);
        if (lastLink != null)
            return lastLink;

        if (getLocator() != null && getLocator().toString().contains("%s"))
            return new GetElementType(fillByTemplate(getLocator(), shortName), this).get(Clickable.class);
        throw exception(cantChooseElementMsg("Last", shortName, "lastAction"));
    }

    private Clickable pageAction(int index) {
        String shortName = "pages";
        if (pagesLocator != null && pagesLocator.toString().contains("%s"))
            return new GetElementType(fillByTemplate(pagesLocator, index), this).get(Clickable.class);

        Clickable pageLink = getClickable(shortName);
        if (pageLink != null)
            return pageLink;
        throw exception(cantChooseElementMsg(Integer.toString(index), shortName, "pageAction"));
    }

    private Label root() {
        return root.get(Label.class);
    }

    public boolean isDisplayed() {
        return root().isDisplayed();
    }

    public boolean isHidden() {
        return !root().isDisplayed();
    }

    public void waitDisplayed() {
        root().waitDisplayed();
    }

    public void waitVanished() {

    }

    public String getAttribute(String name) {
        return root().getAttribute(name);
    }

    public void waitAttribute(String name, String value) {
        root().waitAttribute(name, value);
    }

    public void setAttribute(String attributeName, String value) {
        root().setAttribute(attributeName, value);
    }
}