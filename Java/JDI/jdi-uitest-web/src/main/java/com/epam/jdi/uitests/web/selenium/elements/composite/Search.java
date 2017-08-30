package com.epam.jdi.uitests.web.selenium.elements.composite;
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


import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.ISearch;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.complex.TextList;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JSearch;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.util.List;

import static com.epam.commons.ReflectionUtils.getFields;
import static com.epam.commons.ReflectionUtils.getValueField;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 7/29/2015.
 */
public class Search extends TextField implements ISearch, ISetup {
    protected IButton searchButton;
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
        this.searchButton = new Button(selectLocator);
        this.suggestions = new TextList<>(suggestionsListLocator);
    }

    protected void findAction(String text) {
        getSearchField().newInput(text);
        getSearchButton().click();
    }

    protected void chooseSuggestionAction(String text, String selectValue) {
        getSearchField().input(text);
        if (suggestions != null)
            getSuggestionsList().getElement(selectValue).click();
        else
            throw exception("Select locator not specified for search. Use accordance constructor");
    }

    protected void chooseSuggestionAction(String text, int selectIndex) {
        getSearchField().input(text);
        getSuggestions().getElement(selectIndex).click();
    }

    protected List<String> getSuggesionsAction(String text) {
        getSearchField().input(text);
        return getSuggestions().getLabels();
    }

    /**
     * @param text Specify Text to search
     *             Input text in search field and press search button
     */
    public final void find(String text) {
        invoker.doJAction(format("Search text '%s'", text), () -> findAction(text));
    }

    /**
     * @param text        Specify Text to search
     * @param selectValue Specify value to choose from suggested search result
     *                    Input text in search and then select value from suggestions
     */
    public final void chooseSuggestion(String text, String selectValue) {
        invoker.doJAction(format("Search for text '%s' and choose suggestion '%s'", text, selectValue),
                () -> chooseSuggestionAction(text, selectValue));
    }

    /**
     * @param text        Specify Text to search
     * @param selectIndex Specify index to choose from suggested search result
     *                    Input text in search and then select suggestions by index
     */
    public final void chooseSuggestion(String text, int selectIndex) {
        invoker.doJAction(format("Search for text '%s' and choose suggestion '%s'", text, selectIndex),
                () -> chooseSuggestionAction(text, selectIndex));
    }

    /**
     * @param text Specify Text to search
     * @return Select all suggestions for text
     */
    public final List<String> getSuggesions(String text) {
        return invoker.doJActionResult(format("Get all suggestions for input '%s'", text),
                () -> getSuggesionsAction(text));
    }

    protected TextList<Enum> getSuggestions() {
        if (suggestions != null)
            return getSuggestionsList();
        throw exception("Suggestions list locator not specified for search. Use accordance constructor");
    }

    protected TextList<Enum> getSuggestionsList() {
        suggestions.setParent(getParent());
        return suggestions;
    }
    protected TextField getTextField() {
        TextField textField = new TextField(getLocator());
        textField.setParent(getParent());
        return textField;
    }
    protected IButton getFindButton() {
        searchButton.setParent(getParent());
        return searchButton;
    }

    protected ITextField getSearchField() {
        if (getLocator() != null)
            return getTextField();
        List<Field> fields = getFields(this, ITextField.class);
        switch (fields.size()) {
            case 0:
                throw exception("Can't find any buttons on form '%s'.", toString());
            case 1:
                return (ITextField) getValueField(fields.get(0), this);
            default:
                throw exception("Form '%s' have more than 1 button. Use submit(entity, buttonName) for this case instead", toString());
        }
    }

    protected IButton getSearchButton() {
        if (searchButton != null)
            return getFindButton();
        List<Field> fields = getFields(this, IButton.class);
        switch (fields.size()) {
            case 0:
                throw exception("Can't find any buttons on form '%s'.", toString());
            case 1:
                return (IButton) getValueField(fields.get(0), this);
            default:
                throw exception("Form '%s' have more than 1 button. Use submit(entity, buttonName) for this case instead", toString());
        }
    }

    public void setup(Field field) {
        if (!fieldHasAnnotation(field, JSearch.class, ISearch.class))
            return;
        JSearch jSearch = field.getAnnotation(JSearch.class);
        By root = findByToBy(jSearch.root());
        By input = findByToBy(jSearch.input());
        By searchButton = findByToBy(jSearch.searchButton());
        By suggestions = findByToBy(jSearch.suggestions());
        if (input == null)
            input = findByToBy(jSearch.jInput());
        if (searchButton == null)
            searchButton = findByToBy(jSearch.jSearchButton());
        if (suggestions == null)
            suggestions = findByToBy(jSearch.jSuggestions());

        if (input != null)
            avatar = new GetElementModule(input, this);
        if (searchButton != null)
            this.searchButton = new Button(searchButton);
        if (suggestions != null)
            this.suggestions = new TextList<>(suggestions);
    }
}