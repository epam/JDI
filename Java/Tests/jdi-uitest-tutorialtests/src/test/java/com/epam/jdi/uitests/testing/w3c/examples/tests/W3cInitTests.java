package com.epam.jdi.uitests.testing.w3c.examples.tests;

import com.epam.jdi.site.w3c.W3CSite;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;


/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class W3cInitTests extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(W3CSite.class);
        logger.info("Run Tests");
    }

    @AfterMethod
    public void tearDown() {
        Verify.getFails();
    }
}
