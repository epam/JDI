package com.epam.jdi.site.google;

import com.epam.jdi.site.google.pages.HomePage;
import com.epam.jdi.site.google.pages.SearchPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "http://google.com")
public class GoogleSite extends WebSite {
    @JPage(url = "/") public static HomePage homePage;
    @JPage(url = "/search") public static SearchPage searchPage;
}
