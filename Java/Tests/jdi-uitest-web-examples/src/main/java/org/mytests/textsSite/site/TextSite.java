package org.mytests.textsSite.site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.mytests.textsSite.site.pages.TextPage;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class TextSite extends WebSite {

    @JPage(url = "file:///D:/Work/EPAM/Presentations/SeleniumCamp/images/divInputField.html")
    //@JPage(url = "file:///D:/Work/EPAM/Presentations/SeleniumCamp/images/inputField.html")
    public static TextPage textsPage;
}
