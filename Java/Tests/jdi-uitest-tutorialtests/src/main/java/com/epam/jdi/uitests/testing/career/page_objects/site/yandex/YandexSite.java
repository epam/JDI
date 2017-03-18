package com.epam.jdi.uitests.testing.career.page_objects.site.yandex;

import com.epam.jdi.uitests.testing.career.page_objects.site.yandex.pages.HomePage;
import com.epam.jdi.uitests.testing.career.page_objects.site.yandex.pages.SearchPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "https://ya.ru")
public class YandexSite extends WebSite {
    @JPage(url = "/")
    public static HomePage homePage;
    @JPage(url = "/search")
    public static SearchPage searchPage;
}
