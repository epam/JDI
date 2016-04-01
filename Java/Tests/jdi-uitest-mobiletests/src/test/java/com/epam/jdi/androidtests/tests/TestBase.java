package com.epam.jdi.androidtests.tests;

import com.epam.jdi.androidtestind.page_objects.EpamJDIAndroidApp;
import com.epam.jdi.uitests.mobile.testRunner.TestNGBase;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.mobile.appium.elements.composite.Site.init;


/**
 * Created by Natalia_Grebenshchikova on 12/25/2015.
 */
public abstract class TestBase extends TestNGBase {

    @BeforeMethod
    public static void setUp() throws Exception {
        init(EpamJDIAndroidApp.class);
        logger.info("Run Tests");
    }

    @AfterMethod
    public static void teaDown(){
      ((AndroidDriver)driverFactory.getDriver()).quit();
    }
}
