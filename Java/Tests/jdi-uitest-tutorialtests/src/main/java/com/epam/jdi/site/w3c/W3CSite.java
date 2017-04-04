package com.epam.jdi.site.w3c;

import com.epam.jdi.site.w3c.pages.DropDownPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "https://www.w3schools.com/")
public class W3CSite extends WebSite {

    @JPage(url = "/tags/tryit.asp?filename=tryhtml_select")
    public static DropDownPage dropDownPage;
}
