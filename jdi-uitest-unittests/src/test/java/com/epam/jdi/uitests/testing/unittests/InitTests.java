package com.epam.jdi.uitests.testing.unittests;

import com.epam.jdi.uitests.testing.unittests.entities.User;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite;
import com.epam.jdi.uitests.web.testng.testRunner.TestNGBase;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.login;
import static com.epam.jdi.uitests.web.WebSettings.*;
import static com.epam.jdi.uitests.web.selenium.elements.composite.Site.Init;


/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class InitTests extends TestNGBase {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        getDriverFactory().setDriverPath("C:\\Selenium");
        initJDIFromProperties();
        //Assert.noScreenOnFail();
        Init(EpamJDISite.class);
        homePage.open();
        login.submit(User.DEFAULT);
        logger.info("Run Tests");
        Verify.getFails();
    }

    @AfterMethod
    public void tearDown() {
        Verify.getFails();
    }
}
