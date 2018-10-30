package com.epam.jdi.site.epam.pages;

import com.epam.jdi.site.epam.sections.JobFilter;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

import java.util.List;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class CareerPage extends WebPage {
    @Css(".recruiting-search-ui.job-search-ui")
    public JobFilter jobFilter;

    @Css(".top-navigation__row .top-navigation__item > span >a")
    public List<Label> listMenu;


}
