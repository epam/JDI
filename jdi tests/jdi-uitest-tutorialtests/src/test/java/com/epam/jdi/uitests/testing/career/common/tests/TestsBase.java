package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.homePage;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.Init;

/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public abstract class TestsBase extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        Init(EpamSite.class);
        homePage.open();
        logger.info("Run Tests");
    }
}
