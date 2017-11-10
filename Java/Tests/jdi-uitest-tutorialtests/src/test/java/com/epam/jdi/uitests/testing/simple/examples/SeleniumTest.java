package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.selenium.pageobject.SeleniumPage;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.web.selenium.elements.WebCascadeInit.initPageObject;
import static java.lang.System.setProperty;

/**
 * Created by Roman_Iovlev on 5/23/2017.
 */
public class SeleniumTest {
    SeleniumPage page;
    WebDriver chromeDriver;
    private WebDriver initDriver() {
        String driverPath = new File("").getAbsolutePath() + "/src/main/resources/driver/chromedriver.exe";
        setProperty("webdriver.chrome.driver", driverPath);
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

    @BeforeMethod
    public void before(Method method) throws IOException {
        page = initPageObject(SeleniumPage.class, this::initDriver);
    }

    //TODO @Test
    public void seleniumTest() {
        WebSettings.getDriver().navigate().to("https://www.epam.com/");
        page.logo.click();
        page.menu.get(3).click();
    }
}
