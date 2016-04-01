package com.epam.jdi.uitests.mobile.appium.elements.composite;
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


import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.ISearch;
import com.epam.jdi.uitests.mobile.appium.elements.base.Clickable;
import com.epam.jdi.uitests.mobile.appium.elements.common.TextField;
import com.epam.jdi.uitests.mobile.appium.elements.complex.TextList;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.mobile.appium.driver.WebDriverByUtils.fillByTemplate;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/29/2015.
 */
public class Search extends TextField implements ISearch {
    protected Clickable select;
    protected TextList<Enum> suggestions;

    public Search() {
        super();
    }

    public Search(By byLocator) {
        super(byLocator);
    }

    public Search(By byLocator, By selectLocator) {
        this(byLocator, selectLocator, null);
    }

    public Search(By byLocator, By selectLocator, By suggestionsListLocator) {
        super(byLocator);
        this.select = new Clickable(selectLocator);
        this.suggestions = new TextList(suggestionsListLocator);
    }

    protected void findAction(String text) {
        getSearchField().newInput(text);
        getSearchButton().click();
    }

    protected void chooseSuggestionAction(String text, String selectValue) {
        getSearchField().input(text);
        getElement(selectValue).click();
    }

    protected void chooseSuggestionAction(String text, int selectIndex) {
        getSearchField().input(text);
        getSuggestions().getElement(selectIndex).click();
    }

    protected List<String> getSuggesionsAction(String text) {
        getSearchField().input(text);
        return getSuggestions().getLabels();
    }

    public final void find(String text) {
        invoker.doJAction(format("Search text '%s'", text), () -> findAction(text));
    }

    public final void chooseSuggestion(String text, String selectValue) {
        invoker.doJAction(format("Search for text '%s' and choose suggestion '%s'", text, selectValue),
                () -> chooseSuggestionAction(text, selectValue));
    }

    public final void chooseSuggestion(String text, int selectIndex) {
        invoker.doJAction(format("Search for text '%s' and choose suggestion '%s'", text, selectIndex),
                () -> chooseSuggestionAction(text, selectIndex));
    }

    public final List<String> getSuggesions(String text) {
        return invoker.doJActionResult(format("Get all suggestions for input '%s'", text),
                () -> getSuggesionsAction(text));
    }

    private TextList<Enum> getSuggestions() {
        if (suggestions != null)
            return suggestions;
        else
            throw exception("Suggestions list locator not specified for search. Use accordance constructor");
    }

    private Clickable getElement(String name) {
        if (select != null)
            return copy(select, fillByTemplate(getLocator(), name));
        else
            throw exception("Select locator not specified for search. Use accordance constructor");
    }

    private ITextField getSearchField() {
        List<Field> fields = getFields(this, ITextField.class);
        switch (fields.size()) {
            case 0:
                throw exception("Can't find any buttons on form '%s.", toString());
            case 1:
                return (ITextField) getValueField(fields.get(0), this);
            default:
                throw exception("Form '%s' have more than 1 button. Use submit(entity, buttonName) for this case instead", toString());
        }
    }

    protected IButton getSearchButton() {
        List<Field> fields = getFields(this, IButton.class);
        switch (fields.size()) {
            case 0:
                throw exception("Can't find any buttons on form '%s.", toString());
            case 1:
                return (IButton) getValueField(fields.get(0), this);
            default:
                throw exception("Form '%s' have more than 1 button. Use submit(entity, buttonName) for this case instead", toString());
        }
    }
}