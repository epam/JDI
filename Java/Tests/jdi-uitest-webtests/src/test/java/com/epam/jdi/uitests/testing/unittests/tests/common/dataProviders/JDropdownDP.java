package com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders;

import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;

public class JDropdownDP {
    @DataProvider(name = "dropdownData")
    public static Object[][] inputData() {
        return new Object[][]{
                {metalsColorsPage.colorsRootExpandListValue, true, "Colors", "[Colors, Red, Green, Blue, Yellow]"},
                {metalsColorsPage.colorsRootExpand, false, "", ""},
                {metalsColorsPage.colorsRoot, false, "", ""},
                {metalsColorsPage.colorsRootValue, true, "", ""},
                {metalsColorsPage.colorsRootList, true, "", ""},
                {metalsColorsPage.colorsRootListValue,true, "Colors", "[Colors, Red, Green, Blue, Yellow]"}
        };
    }
}
