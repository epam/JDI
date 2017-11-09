package com.epam.jdi.site.w3c;

import com.epam.jdi.site.w3c.pages.DropDownPage;
import com.epam.jdi.site.w3c.pages.HomePage;
import com.epam.jdi.site.w3c.form.LikePopupForm;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite("https://www.w3schools.com/")
public class W3CSite extends WebSite {
    @JPage(url = "https://www.w3schools.com/")
    public static HomePage homePage;

    @JPage(url = "/tags/tryit.asp?filename=tryhtml_select")
    public static DropDownPage dropDownPage;

    public static LikePopupForm likeForm;
}
