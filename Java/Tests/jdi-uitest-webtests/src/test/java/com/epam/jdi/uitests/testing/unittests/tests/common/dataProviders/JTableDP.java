package com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders;

import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.supportPage;

public class JTableDP {
    public static final String supportTableText = "||X||Type|Now|Plans||\n" +
            "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
            "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
            "||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
            "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||";

    @DataProvider(name = "tableData")
    public static Object[][] inputData() {
        return new Object[][]{
                {supportPage.tableRootRowHeader, true},
                {supportPage.tableRoot, true}
        };
    }
}
