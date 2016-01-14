package com.epam.jdi.uitests.testing.unittests.dataproviders;

import org.testng.annotations.DataProvider;

/**
 * Created by Roman_Iovlev on 11/19/2015.
 */
public class NameDP {

    public static String currentTestName = "";

    @DataProvider(name = "name")
    public static Object[][] name() {
        return new Object[][]{
                {currentTestName}};
    }
}
