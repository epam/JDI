package com.epam.jdi.uitests.testing.career.common_tests;

import com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.homePage;
import static com.epam.jdi.uitests.web.selenium.elements.composite.Site.Init;

/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public abstract class TestsBase extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        driverFactory.setDriverPath("C:\\Selenium");
        initJDIFromProperties();
        Init(EpamSite.class);
        homePage.open();
        logger.info("Run Tests");
    }
}
