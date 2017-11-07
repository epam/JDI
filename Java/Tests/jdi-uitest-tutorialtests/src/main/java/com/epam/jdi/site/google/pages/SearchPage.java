package com.epam.jdi.site.google.pages;

import com.epam.jdi.site.google.custom.SearchResult;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Text;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class SearchPage extends WebPage {
    @FindBy(css = ".srg>.g") public List<SearchResult> jobsL;
    @FindBy(css = ".srg>.g") public Elements<SearchResult> jobsE;
    @Text("GitHub - epam/JDI: JDI") public Element gitHubJdi;

}
