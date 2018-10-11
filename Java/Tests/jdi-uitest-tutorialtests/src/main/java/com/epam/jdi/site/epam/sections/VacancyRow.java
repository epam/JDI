package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Title;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;


public class VacancyRow extends Section {
    @Title
    @Css("h5>a")
    public ILabel name;
    @Css(".search-result__item-description")
    public IText description;
    @Css(".search-result__item-controls a")
    public ILink apply;
    @Css(".search-result__share-button")
    public IButton share;
    @Override
    public String toString() {
        return "Name: " + name.getText() + "; Description: " + description.getText();
    }

}