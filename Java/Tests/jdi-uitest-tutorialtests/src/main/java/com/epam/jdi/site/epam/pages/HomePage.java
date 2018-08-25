package com.epam.jdi.site.epam.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.XPath;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
@JPage(url = "/", title = "EPAM | Software Product Development Services")
public class HomePage extends WebPage {
    @XPath("//h2[contains(.,'Our Work')]") public Label ourWork;

}
