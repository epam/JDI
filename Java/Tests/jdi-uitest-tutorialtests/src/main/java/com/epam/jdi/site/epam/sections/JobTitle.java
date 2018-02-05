package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

/**
 * Created by Roman_Iovlev on 1/28/2018.
 */
public class JobTitle extends Section{
    @Css(".search-result__item-name")
    public Text name;
    @Css(".search-result__location")
    public Text location;
}
