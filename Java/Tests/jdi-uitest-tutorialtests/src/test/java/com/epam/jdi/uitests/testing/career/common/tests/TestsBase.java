package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.core.settings.JDISettings.useDriver;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebDrivers;

/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public abstract class TestsBase extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        WebSite.init(EpamSite.class);
        logger.info("Run Tests");
    }

    @AfterSuite(alwaysRun = true)
    public static void tearDown() {
        killAllRunWebDrivers();
    }
}
