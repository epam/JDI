package com.epam.jdi.uitests.testing.unittests.dataproviders;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SIMPLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.simpleTablePage;
import static java.util.Arrays.asList;

/**
 * Created by Natalia_Grebenshchikova on 10/8/2015.
 */
public class TableDP {

    @DataProvider(name = "differentTables")
    public static Object[][] differentTables() {
        return new Object[][]{
                {SUPPORT_PAGE,
                        new TableProvider("Table with all headers",
                                Table::hasAllHeaders),
                        "2/6", "Now, Plans", "Drivers, Test Runner, Asserter, Logger, Reporter, BDD/DSL",
                        "||X||Now|Plans||\n" +
                                "||Drivers||Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SUPPORT_PAGE,
                        new TableProvider("Table with no headers",
                                Table::hasNoHeaders),
                        "3/6", "1, 2, 3", "1, 2, 3, 4, 5, 6",
                        "||X||1|2|3||\n" +
                                "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SUPPORT_PAGE,
                        new TableProvider("Table with only column headers",
                                Table::hasOnlyColumnHeaders),
                        "3/6", "Type, Now, Plans", "1, 2, 3, 4, 5, 6",
                        "||X||Type|Now|Plans||\n" +
                                "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SUPPORT_PAGE,
                        new TableProvider("Table with only row headers",
                                Table::hasOnlyRowHeaders),
                        "2/6", "1, 2", "Drivers, Test Runner, Asserter, Logger, Reporter, BDD/DSL",
                        "||X||1|2||\n" +
                                "||Drivers||Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},

                {SUPPORT_PAGE,
                        new TableProvider("Table with specified columns (Type, Now)", table ->
                                table.hasColumnHeaders(asList("Type", "Now"))),
                        "2/6", "Type, Now", "1, 2, 3, 4, 5, 6",
                        "||X||Type|Now||\n" +
                                "||1||Drivers|Selenium, Custom||\n" +
                                "||2||Test Runner|TestNG, JUnit, Custom||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom||\n" +
                                "||4||Logger|Log4J, TestNG log, Custom||\n" +
                                "||5||Reporter|Jenkins, Allure, Custom||\n" +
                                "||6||BDD/DSL|Custom||", "null"},
                {SUPPORT_PAGE,
                        new TableProvider("Table with specified rows (First Row, Second Row, Third Row)", table ->
                                table.hasRowHeaders(asList("First Row", "Second Row", "Third Row"))),
                        "2/3", "Now, Plans", "First Row, Second Row, Third Row",
                        "||X||Now|Plans||\n" +
                                "||First Row||Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Second Row||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Third Row||TestNG, JUnit, Custom|MSTest, NUnit, Epam||", "null"},
                {SUPPORT_PAGE,
                        new TableProvider("Table with limited columns (2)", table ->
                                table.setColumnsCount(2)),
                        "2/6", "Type, Now", "1, 2, 3, 4, 5, 6",
                        "||X||Type|Now||\n" +
                                "||1||Drivers|Selenium, Custom||\n" +
                                "||2||Test Runner|TestNG, JUnit, Custom||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom||\n" +
                                "||4||Logger|Log4J, TestNG log, Custom||\n" +
                                "||5||Reporter|Jenkins, Allure, Custom||\n" +
                                "||6||BDD/DSL|Custom||", "null"},
                {SUPPORT_PAGE,
                        new TableProvider("Table with limited rows (4)", table ->
                                table.setRowsCount(4)),
                        "3/4", "Type, Now, Plans", "1, 2, 3, 4",
                        "||X||Type|Now|Plans||\n" +
                                "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||", "null"},

                {SIMPLE_PAGE,
                        new TableProvider("Custom table with custom headers",
                                simpleTablePage.getTable(By.xpath(".//tr[position() > 1]/td[1]"),
                                        By.xpath(".//tr[1]/td[position() > 1]"),
                                        By.xpath(".//tr[position() > 1][%s]/td[position() > 1]"),
                                        By.xpath(".//tr[position() > 1]/td[position() > 1][%s]")), Table::hasAllHeaders),
                        "2/5", "Selenium Custom, JavaScript, Appium, WinAPI, Sikuli", "Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||X||Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},

        // TODO
        /*      {SIMPLE_PAGE,
                        new TableProvider("Custom table with custom rows",
                                simpleTablePage.getTable(By.xpath(".//tr/td[1]"),
                                null, By.xpath(".//tr[position() > 1][%s]/td[position() > 1]"),
                                By.xpath(".//tr/td[%s]"), 2, 2), Table::hasOnlyRowHeaders),
                        "2/6", "", "Drivers, Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||X||1|2||\n" +
                                "||Drivers||Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Custom table with custom columns",
                                simpleTablePage.getTable(null,
                                        By.xpath(".//tr[1]/td[position() > 1]"),
                                        By.xpath(".//tr[position() > 1][%s]/td[position() > 1]"),
                                        By.xpath(".//tr[position() > 1]/td[position() > 1][%s]")), Table::hasAllHeaders),
                        "2/5", "Selenium Custom, JavaScript, Appium, WinAPI, Sikuli", "Drivers, Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||X||Drivers|Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||1||Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||2||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||3||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||4||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||5||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Custom table with no headers",
                                simpleTablePage.getTable(null, null,
                                        By.xpath(".//tr[%s]/td"),
                                        By.xpath(".//tr/td[%s]")), table -> table),
                        "3/6", "", "1, 2, 3, 4, 5, 6",

                        "||1|Drivers|Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||2|Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||3|Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4|Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||5|Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||6|BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Custom table with both headers",
                                simpleTablePage.getTable(true, true,
                                        By.xpath(".//tr[position() > 1]/td[1]"),
                                        By.xpath(".//tr[1]/td")), Table::hasAllHeaders),
                        "2/5", "Selenium Custom, JavaScript, Appium, WinAPI, Sikuli", "Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||X||Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Custom table with rows headers",
                                simpleTablePage.getTable(true, false, By.xpath(".//tr/td[1]"), null), Table::hasAllHeaders),
                        "2/6", "1, 2", "Drivers, Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||X||1|2||\n" +
                                "||Drivers||Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Custom table with column headers",
                                simpleTablePage.getTable(false, true, null, By.xpath(".//tr[1]/td")), table -> table),
                        "3/5", "Drivers, Selenium Custom, JavaScript, Appium, WinAPI, Sikuli", "1, 2, 3, 4, 5, 6",

                        "||X||Drivers|Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||1||Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||2||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||3||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||4||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||5||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Table without rows and columns",
                                simpleTablePage.getTable(false, false, null, null), table -> table),
                        "3/6", "1, 2, 3", "1, 2, 3, 4, 5, 6",

                        "||X||1|2|3||\n" +
                                "||1||Drivers|Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||2||Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Table with custom rows and columns start index and both headers",
                                simpleTablePage.getTable(By.xpath(".//tr[position() > 1]/td[1]"),
                                        By.xpath(".//tr[1]/td[position() > 1]"), 2, 2), Table::hasAllHeaders),
                        "2/5", "Selenium Custom, JavaScript, Appium, WinAPI, Sikuli", "Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||Drivers||Selenium Custom||JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Table with custom rows and columns start index and rows header",
                                simpleTablePage.getTable(By.xpath(".//tr/td[1]"), null, 1, 2), Table::hasAllHeaders),
                        "2/6", "", "Drivers, Test Runner, Asserter, Logger, Reporter, BDD/DSL",

                        "||Drivers||Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner||TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter||TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger||Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter||Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL||Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Table with custom rows and columns start index and columns header",
                                simpleTablePage.getTable(null,
                                        By.xpath(".//tr[1]/td"), 2, 1), table -> table),
                        "3/5", "Selenium Custom, JavaScript, Appium, WinAPI, Sikuli", "1, 2, 3, 4, 5, 6",

                        "||Drivers||Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {SIMPLE_PAGE,
                        new TableProvider("Table with custom rows and columns start index but without any headers",
                                simpleTablePage.getTable(null, null, 1, 1), table -> table),
                        "3/6", "", "1, 2, 3, 4, 5, 6",

                        "||Drivers|Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||\n" +
                                "||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||", "null"},
                {DYNAMIC_TABLE_PAGE,
                        new TableProvider("Table with footer", dynamicTablePage.dynamicTable, Table::hasAllHeaders),
                        "2/8", "Column 1, Column 2",
                        "Microsoft Technologies, Mobile, UXD, Version Control Systems, JavaScript Components and Frameworks, Software Construction, Life Sciences, Content management",
                        "||X||Column 1|Column 2||\n" +
                                "||Microsoft Technologies||Select,See More,.NET Technologies|Select,See More,Internet Technologies||\n" +
                                "||Mobile||Select,See More,HTML5/CSS3|Select,See More,JQuery Mobile||\n" +
                                "||UXD||Select,See More,(X)CSS Development|Select,See More,Grunt (The JavaScript Task Runner)||\n" +
                                "||Version Control Systems||Select,See More,CVS|Select,See More,TortoiseSVN||\n" +
                                "||JavaScript Components and Frameworks||Select,See More,Backbone marionette js|Select,See More,Bootstrap||\n" +
                                "||Software Construction||Select,See More,Internet Technologies|Select,See More,JavaScript Components and Frameworks||\n" +
                                "||Life Sciences||Select,See More,Biology|Select,See More,Chemistry||\n" +
                                "||Content management||Select,See More,Drupal|Select,See More,Adobe Day CRX||\n"+
                                "||1 column|2 column|3 column||", "1 column, 2 column, 3 column"}*/
        };
    }
}
