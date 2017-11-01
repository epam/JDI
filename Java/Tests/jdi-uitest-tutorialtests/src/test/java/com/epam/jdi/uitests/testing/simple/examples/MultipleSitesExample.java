package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.site.google.GoogleSite;
import com.epam.jdi.site.yandex.YandexSite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.CHROME;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.*;
import static com.epam.jdi.uitests.web.settings.WebSettings.initFromProperties;
import static com.epam.jdi.uitests.web.settings.WebSettings.useDriver;


public class MultipleSitesExample {
    @BeforeMethod
    public void before(Method method) throws IOException {
        initFromProperties();
        init(useDriver(CHROME), YandexSite.class);
        init(useDriver(CHROME), GoogleSite.class);
    }
    @Test
    public void twoSiteExample() {
        open(YandexSite.class);
        open(GoogleSite.class);
        GoogleSite.homePage.search("JDI framework");
    }
}
