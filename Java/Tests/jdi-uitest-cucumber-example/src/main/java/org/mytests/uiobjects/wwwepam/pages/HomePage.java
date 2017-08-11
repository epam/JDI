package org.mytests.uiobjects.wwwepam.pages;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.Frame;
import org.mytests.uiobjects.w3c.pages.HtmlFrame;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class HomePage extends WebPage {
    @Frame(id = "iframeResult")
    public HtmlFrame frame;
}
