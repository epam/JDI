package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Title;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;


public class SearchResultItem extends Section {
    @Title
    @Css("h5>a")
//            ".search-result__item-name")
    public ILabel name;
    @Css(".search-result__item-description")
    public IText description;
    @Css(".search-result__location")
    public IText location;
    @Css(".search-result__item-apply")
    public ILink apply;

}

