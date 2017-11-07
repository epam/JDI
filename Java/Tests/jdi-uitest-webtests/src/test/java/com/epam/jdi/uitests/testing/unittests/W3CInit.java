package com.epam.jdi.uitests.testing.unittests;

import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite;
import com.epam.jdi.uitests.testing.unittests.pageobjects.w3cSite.W3cSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.w3cSite.W3cSite.framePage;
import static com.epam.jdi.uitests.web.selenium.driver.DriverTypes.CHROME;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.init;
import static com.epam.jdi.uitests.web.settings.WebSettings.logger;
import static com.epam.jdi.uitests.web.settings.WebSettings.useDriver;


/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class W3CInit extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSettings.domain = "http://www.w3schools.com";
        init(useDriver(CHROME), W3cSite.class);
        init(useDriver(CHROME), EpamJDISite.class);
        framePage.open();
        Verify.getFails();
        logger.info("Run Tests");
    }

    @AfterMethod
    public void tearDown() {
        Verify.getFails();
    }
}
