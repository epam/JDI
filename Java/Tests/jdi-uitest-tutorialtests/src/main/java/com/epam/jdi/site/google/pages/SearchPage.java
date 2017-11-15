package com.epam.jdi.site.google.pages;

import com.epam.jdi.site.google.custom.SearchResult;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Text;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class SearchPage extends WebPage {
    @Css(".srg>.g") public Elements<SearchResult> jobsE;
    @Text("JDI is the test Framework for UI test automation") public Element gitHubJdi;

}
