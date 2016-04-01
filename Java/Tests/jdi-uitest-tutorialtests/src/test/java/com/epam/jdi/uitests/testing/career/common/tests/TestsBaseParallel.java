package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebDrivers;

/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public abstract class TestsBaseParallel extends TestNGBase {


    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSettings.initFromProperties();
        killAllRunWebDrivers();
        logger.info("Run Tests");
    }
}
