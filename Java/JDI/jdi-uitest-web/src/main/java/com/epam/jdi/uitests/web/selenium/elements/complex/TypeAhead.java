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

import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.core.interfaces.complex.ITypeAhead;
import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.Clickable;
import com.epam.jdi.uitests.web.selenium.elements.base.ClickableText;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTypeAhead;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;

/**
 * Created by Morokov Danila on 22.05.2018.
 */
public class TypeAhead extends BaseSelector implements ITypeAhead, ISetup {
    protected int actionTimeout;

    protected GetElementType root;
    protected GetElementType input;
    protected GetElementType list;

    private By rootLocator;
    private By inputLocator;
    private By listLocator;

    public TypeAhead() {
        super();
    }

    public TypeAhead(By rootLocator, By inputLocator, By listLocator) {
        this();

        this.rootLocator = rootLocator;
        this.inputLocator = inputLocator;
        this.listLocator = listLocator;

        if (rootLocator != null) {
            setLocator(rootLocator);
            this.root = new GetElementType(rootLocator, getParent());
        }

        if (inputLocator != null) {
            this.input = new GetElementType(inputLocator, this);
        }

        if (listLocator != null) {
            this.list = new GetElementType(listLocator, this);
        }
    }

    public TypeAhead(By rootLocator, By inputLocator, By listLocator, int actionTimeout) {
        this(rootLocator, inputLocator, listLocator);

        this.actionTimeout = actionTimeout;
    }

    public void setup(Field field) {
        if (!fieldHasAnnotation(field, JTypeAhead.class, ITypeAhead.class))
            return;
        JTypeAhead jTypeAhead = field.getAnnotation(JTypeAhead.class);

        rootLocator = findByToBy(jTypeAhead.root());
        inputLocator = findByToBy(jTypeAhead.input());
        listLocator = findByToBy(jTypeAhead.list());
        actionTimeout = jTypeAhead.actionTimeout();

        if (rootLocator != null) {
            setLocator(rootLocator);
            this.root = new GetElementType(rootLocator, getParent());
        }

        if (inputLocator != null) {
            this.input = new GetElementType(inputLocator, this);
        }

        if (listLocator != null) {
            this.list = new GetElementType(listLocator, this);
        }
    }

    private String cantChooseElementMsg(String shortName) {
        return MessageFormat.format("Can't choose Element '{0}'. "
                + "Please specify locator for this action using @JTypeAhead annotaion with all locators", shortName);
    }

    protected ClickableText root() {
        String shortName = "rootLocator";
        if (rootLocator != null)
            return new GetElementType(rootLocator, rootLocator).get(ClickableText.class);
        throw exception(cantChooseElementMsg(shortName));
    }

    protected TextField input() {
        String shortName = "inputLocator";
        if (inputLocator != null)
            return new GetElementType(inputLocator, inputLocator).get(TextField.class);
        throw exception(cantChooseElementMsg(shortName));
    }

    protected Selector list() {
        String shortName = "listLocator";
        if (listLocator != null)
            return new GetElementType(listLocator, rootLocator).get(Selector.class);
        throw exception(cantChooseElementMsg(shortName));
    }

    protected void selectAction(String name) {
        list().select(name);
    }

    protected void selectAction(int index) {
        list().select(index);
    }

    @Override
    public void waitDisplayed() {
        root().waitDisplayed();
    }

    @Override
    public void waitVanished() {
        root().waitVanished();
    }

    public void wait(Function<WebElement, Boolean> resultFunc) {
        root().wait(resultFunc);
    }

    public <R> R wait(Function<WebElement, R> resultFunc, Function<R, Boolean> condition) {
        return  root().wait(resultFunc, condition);
    }

    public void wait(Function<WebElement, Boolean> resultFunc, int timeoutSec) {
        root().wait(resultFunc, timeoutSec);
    }

    public <R> R wait(Function<WebElement, R> resultFunc, Function<R, Boolean> condition, int timeoutSec) {
        return root().wait(resultFunc, condition, timeoutSec);
    }

    @Override
    protected String getValueAction() {
        return null;
    }

    protected String getTextAction() {
        String result = "";
        if (root().getLocator().toString().contains("select")) try {
            result = new Select(root().getWebElement()).getFirstSelectedOption().getText();
        } catch (Exception ignore) {}
        return result != null && !result.equals("")
            ? result
            : input().getText();
    }

    /**
     * Click on Element
     */
    public final void click() {
        root().click();
    }

    public String getValue() {
        return input().getText();
    }

    public void setValue(String value) {
        input().sendKeys(value);
    }

    public void sendKeys(String value) {
        setValue(value);
    }

    public final String getText() {
        return input().getText();
    }

    public List<String> getOptions() {
        return (List<String>) new GetElementType(listLocator, rootLocator).get(TextList.class).getTextList();
    }


    /**
     * @param text Specify expected text
     * @return Wait while Element’s text contains expected text. Returns Element’s text
     */
    public final String waitText(String text) {
        return actions.waitText(text, this::getTextAction);
    }

    /**
     * @param regEx Specify expected regular expression Text
     * @return Wait while Element’s text matches regEx. Returns Element’s text
     */
    public final String waitMatchText(String regEx) {
        return actions.waitMatchText(regEx, this::getTextAction);
    }

    /**
     * @param attributeName Specify attribute name
     * @param value         Specify attribute value
     *                      Sets attribute value for Element
     */
    public void setAttribute(String attributeName, String value) {
        root().setAttribute(attributeName, value);
    }

    public WebElement getWebElement() {
        return new GetElementType(rootLocator, rootLocator).get(Element.class).getWebElement();
    }

    @Override
    protected boolean isSelectedAction(String name) {
        return false;
    }

    @Override
    protected boolean isSelectedAction(int num) {
        return false;
    }

    public String getAttribute(String name) {
        return root().getAttribute(name);
    }

    /**
     * @param name  Specify attribute name
     * @param value Specify attribute value
     * Waits while attribute gets expected value. Return false if this not happens
     */
    public void waitAttribute(String name, String value) {
        root().waitAttribute(name, value);
    }

    /**
     * Click on root element, send keys to the input and select a value from the list with some delay if it needed
     */
    public void select(String query, String option) {
        Timer.sleep(actionTimeout);
        click();

        Timer.sleep(actionTimeout);
        input().setValue(query);

        Timer.sleep(actionTimeout);
        list().select(option);
    }

    @Override
    public void select(String name) {
        selectAction(name);
    }

    @Override
    public void select(Enum name) {
        select(getEnumValue(name));
    }

    @Override
    public void select(int index) {
        selectAction(index);
    }

    @Override
    public String getSelected() {
        return null;
    }

    @Override
    public int getSelectedIndex() {
        return 0;
    }
}