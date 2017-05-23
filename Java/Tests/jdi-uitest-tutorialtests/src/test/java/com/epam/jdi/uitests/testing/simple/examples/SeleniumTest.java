package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.selenium.pageobject.SeleniumPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
    private void initDriver() {
        String driverPath = new File("").getAbsolutePath() + "/src/main/resources/driver/chromedriver.exe";
        setProperty("webdriver.chrome.driver", driverPath);
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    @BeforeMethod
    public void before(Method method) throws IOException {
        initDriver();
        page = initPageObject(SeleniumPage.class, chromeDriver);
    }

    @Test
    public void seleniumTest() {
        chromeDriver.navigate().to("https://www.epam.com/");
        page.logo.click();
        page.menu.get(3).click();
    }
}
