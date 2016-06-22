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
import com.epam.commons.PrintUtils;
import com.epam.jdi.uitests.core.interfaces.complex.IMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.first;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * RadioButtons control implementation
 *
 * @author Alexeenko Yan
 */
public class Menu<TEnum extends Enum> extends Selector<TEnum> implements IMenu<TEnum> {
    private List<By> menuLevelsLocators = new ArrayList<>();
    private String separator = "\\.";
    public <T extends IMenu<TEnum>> T useSeparator(String separator) {
        this.separator = separator;
        return (T)this;
    }

    public Menu() { }

    public Menu(By optionsNamesLocatorTemplate) {
        super(optionsNamesLocatorTemplate);
    }

    public Menu(By optionsNamesLocatorTemplate, By allOptionsNamesLocator) {
        super(optionsNamesLocatorTemplate, allOptionsNamesLocator);
        menuLevelsLocators.add(optionsNamesLocatorTemplate);
    }
    public Menu(List<By> menuLevelsLocators) {
        this.menuLevelsLocators = menuLevelsLocators;
    }

    protected void hoverAction(String name) {
        chooseItemAction(name, el -> {
            Actions action = new Actions(getDriver());
            action.moveToElement(el).clickAndHold().build().perform();
        });
    }

    public final void hover(String name) {
        actions.hover(name, this::hoverAction);
    }

    protected void hoverAndClickAction(String name) {
        String[] split = name.split(separator);
        if (split.length > menuLevelsLocators.size())
            throw exception("Can't hover and click on element (%s) by value: %s. Amount of locators (%s) less than select path length (%s)",
                this, name, menuLevelsLocators.size(), split.length);
        if (split.length > 1)
            hover(PrintUtils.print(LinqUtils.listCopyUntil(Arrays.asList(split), -1), ","));
        int lastIndex = split.length - 1;
        Selector selector = new Selector<>(menuLevelsLocators.get(lastIndex));
        selector.setParent(getParent());
        selector.select(split[lastIndex]);
    }
    public final void hover(TEnum name) {
        hover(getEnumValue(name));
    }
    public final void hoverAndClick(String name) { actions.select(name, this::hoverAndClickAction); }
    public final void hoverAndClick(TEnum name) {
        hoverAndClick(getEnumValue(name));
    }
    public final void hoverAndSelect(String name) {
        actions.select(name, this::hoverAndClickAction);
    }
    public final void hoverAndSelect(TEnum name) {
        hoverAndClick(getEnumValue(name));
    }

    @Override
    protected void selectAction(String name) {
        chooseItemAction(name, WebElement::click);
    }

    protected void chooseItemAction(String name,  Consumer<WebElement> action) {
        String[] nodes = name.split(separator);
        if (menuLevelsLocators.size() == 0 && hasLocator())
            menuLevelsLocators.add(getLocator());
        if (menuLevelsLocators.size() >= nodes.length)
            for(int i=0; i < nodes.length; i++) {
                String value = nodes[i];
                List<WebElement> elements = new Selector<>(menuLevelsLocators.get(i)).getElements();
                WebElement element = first(elements, el -> el.getText().equals(value));
                action.accept(element);
            }
    }

    public void setUp(List<By> menuLevelsLocators) {
        this.menuLevelsLocators = menuLevelsLocators;
    }
}