package com.epam.jdi.site.w3c.pages;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.Frame;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class DropDownPage extends WebPage {
    @Frame(id = "iframeResult") public HtmlFrame frame;
}
