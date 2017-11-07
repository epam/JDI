package com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders;

import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;

public class JDropListDP {
    @DataProvider(name = "dropListData")
    public static Object[][] inputData() {
        return new Object[][]{
                {metalsColorsPage.saladDL}
        };
    }
}
