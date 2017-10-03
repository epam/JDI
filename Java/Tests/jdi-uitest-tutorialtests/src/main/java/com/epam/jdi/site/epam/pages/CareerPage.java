package com.epam.jdi.site.epam.pages;

import com.epam.jdi.site.epam.sections.JobFilter;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ClassName;

import java.util.List;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class CareerPage extends WebPage {
    @ClassName( "job-search-form-ui") public JobFilter jobFilter;

    @ClassName(".tile-menu>li>a") public List<Label> listMenu;

}
