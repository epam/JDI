package com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders;

import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.supportPage;

public class JTableDP {
    public static final String supportTableText = "||X||Type|Now|Plans||\n" +
            "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
            "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
            "||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
            "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||";

    public static final String supportTableRows =
            "Type:Test Runner, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam";

    public static final String supportTableColumns =
            "1:Drivers, 2:Test Runner, 3:Asserter, 4:Logger, 5:Reporter, 6:BDD/DSL";

    public static final String supportTableKeyWord = "Custom";

    public static final String supportTableSearchResult = "Selenium, Custom";

    public static final String supportTableHeaders = "[Type, Now, Plans]";

    public static final String supportTableFirstCell = "Drivers";

    public static final String complexTableText = "||X||||\n" +
            "||1||Microsoft Technologies|Select\n" +
            "See More\n" +
            ".NET Technologies|Select\n" +
            "See More\n" +
            "Internet Technologies||\n" +
            "||2||Mobile|Select\n" +
            "See More\n" +
            "HTML5/CSS3|Select\n" +
            "See More\n" +
            "JQuery Mobile||\n" +
            "||3||UXD|Select\n" +
            "See More\n" +
            "(X)CSS Development|Select\n" +
            "See More\n" +
            "Grunt (The JavaScript Task Runner)||\n" +
            "||4||Version Control Systems|Select\n" +
            "See More\n" +
            "CVS|Select\n" +
            "See More\n" +
            "TortoiseSVN||\n" +
            "||5||JavaScript Components and Frameworks|Select\n" +
            "See More\n" +
            "Backbone marionette js|Select\n" +
            "See More\n" +
            "Bootstrap||\n" +
            "||6||Software Construction|Select\n" +
            "See More\n" +
            "Internet Technologies|Select\n" +
            "See More\n" +
            "JavaScript Components and Frameworks||\n" +
            "||7||Life Sciences|Select\n" +
            "See More\n" +
            "Biology|Select\n" +
            "See More\n" +
            "Chemistry||\n" +
            "||8||Content management|Select\n" +
            "See More\n" +
            "Drupal|Select\n" +
            "See More\n" +
            "Adobe Day CRX||' in 21.88 seconds\n" +
            "||X||||\n" +
            "||1||Microsoft Technologies|Select\n" +
            "See More\n" +
            ".NET Technologies|Select\n" +
            "See More\n" +
            "Internet Technologies||\n" +
            "||2||Mobile|Select\n" +
            "See More\n" +
            "HTML5/CSS3|Select\n" +
            "See More\n" +
            "JQuery Mobile||\n" +
            "||3||UXD|Select\n" +
            "See More\n" +
            "(X)CSS Development|Select\n" +
            "See More\n" +
            "Grunt (The JavaScript Task Runner)||\n" +
            "||4||Version Control Systems|Select\n" +
            "See More\n" +
            "CVS|Select\n" +
            "See More\n" +
            "TortoiseSVN||\n" +
            "||5||JavaScript Components and Frameworks|Select\n" +
            "See More\n" +
            "Backbone marionette js|Select\n" +
            "See More\n" +
            "Bootstrap||\n" +
            "||6||Software Construction|Select\n" +
            "See More\n" +
            "Internet Technologies|Select\n" +
            "See More\n" +
            "JavaScript Components and Frameworks||\n" +
            "||7||Life Sciences|Select\n" +
            "See More\n" +
            "Biology|Select\n" +
            "See More\n" +
            "Chemistry||\n" +
            "||8||Content management|Select\n" +
            "See More\n" +
            "Drupal|Select\n" +
            "See More\n" +
            "Adobe Day CRX||";


    @DataProvider(name = "tableData")
    public static Object[][] inputData() {
        return new Object[][]{
                {supportPage, supportPage.tableRootRowHeader, supportTableText, supportTableRows, supportTableColumns, supportTableKeyWord,
                        supportTableSearchResult, 6, supportTableFirstCell, supportTableHeaders},
                {supportPage, supportPage.tableRoot, supportTableText, supportTableRows, supportTableColumns, supportTableKeyWord,
                        supportTableSearchResult, 6, supportTableFirstCell, supportTableHeaders},
                {supportPage, supportPage.tableRootHeaderRow, supportTableText, supportTableRows, supportTableColumns, supportTableKeyWord,
                        supportTableSearchResult, 6, supportTableFirstCell, supportTableHeaders}
                // {simpleTablePage.table}
        };
    }
}
