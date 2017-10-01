package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.site.google.GoogleSite;
import com.epam.jdi.site.yandex.YandexSite;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.CHROME;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.init;
import static com.epam.jdi.uitests.web.settings.WebSettings.initFromProperties;
import static com.epam.jdi.uitests.web.settings.WebSettings.useDriver;


public class MultipleSitesExample {
    @BeforeMethod
    public void before(Method method) throws IOException {
        initFromProperties();
        init(useDriver(CHROME), YandexSite.class);
        init(useDriver(CHROME), GoogleSite.class);
    }
    //@Test
    public void twoSiteExample() {
        YandexSite.homePage.open();
        Assert.areEquals(YandexSite.homePage.getDriver().getCurrentUrl(),
            "https://ya.ru/");
        YandexSite.homePage.search("JDI framework");

        GoogleSite.homePage.open();
        Assert.contains(GoogleSite.homePage.getDriver().getCurrentUrl(),
                "https://www.google.");
        GoogleSite.homePage.search("JDI framework");
    }
}
