package com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders;

import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;

public class JSearchDP {
    @DataProvider(name = "searchData")
    public static Object[][] inputData() {
        return new Object[][]{
                {homePage.jSearchRootInputSearchButton, true},
                {homePage.jSearchInputSearchButton, true},
                {homePage.jSearchRootInput, true}
        };
    }
}
