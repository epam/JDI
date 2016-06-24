package com.epam.jdi.uitests.testing.unittests.dataproviders;

import com.epam.jdi.uitests.testing.unittests.enums.RowNumbers;
import com.epam.jdi.uitests.win.winium.elements.complex.table.Table;
import org.testng.annotations.DataProvider;

import static com.epam.jdi.uitests.testing.unittests.enums.ColumnHeaders.*;
import static com.epam.jdi.uitests.testing.unittests.enums.RowNumbers.Twenty;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.dynamicTablePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.sortingTablePage;

/**
 * Created by Natalia_Grebenshchikova on 12/17/2015.
 */
public class DynamicTableDP {

    @DataProvider(name = "changeColumnSet")
    public static Object[][] changeColumnSet() {
        return new Object[][]{
                {new TableProvider("Add column Table with additional column ", dynamicTablePage.dynamicTable, Table::hasAllHeaders,
                        dynamicTablePage -> dynamicTablePage.addColumn(col3), dynamicTablePage),
                        "3/8", "Column 1, Column 2, Column 3",
                        "Microsoft Technologies, Mobile, UXD, Version Control Systems, JavaScript Components and Frameworks, Software Construction, Life Sciences, Content management",
                        "||X||Column 1|Column 2|Column 3||\n" +
                                "||Microsoft Technologies||Select\nSee More\n.NET Technologies|Select\nSee More\nInternet Technologies|Select\nSee More\nProgramming Languages||\n" +
                                "||Mobile||Select\nSee More\nHTML5/CSS3|Select\nSee More\nJQuery Mobile|Select\nSee More\nJQuery Mobile||\n" +
                                "||UXD||Select\nSee More\n(X)CSS Development|Select\nSee More\nGrunt (The JavaScript Task Runner)|Select\nSee More\nLess CSS||\n" +
                                "||Version Control Systems||Select\nSee More\nCVS|Select\nSee More\nTortoiseSVN|Select\nSee More\nGit||\n" +
                                "||JavaScript Components and Frameworks||Select\nSee More\nBackbone marionette js|Select\nSee More\nBootstrap|Select\nSee More\nRequireJS||\n" +
                                "||Software Construction||Select\nSee More\nInternet Technologies|Select\nSee More\nJavaScript Components and Frameworks|Select\nSee More\nBackend||\n" +
                                "||Life Sciences||Select\nSee More\nBiology|Select\nSee More\nChemistry|Select\nSee More\nMedicine||\n" +
                                "||Content management||Select\nSee More\nDrupal|Select\nSee More\nAdobe Day CRX|Select\nSee More\nSharepoint||",
                        "1 column, 2 column, 3 column, 4 column"},

                {new TableProvider("deleteFirstColumn", dynamicTablePage.dynamicTable, Table::hasAllHeaders,
                        dynamicTablePage -> dynamicTablePage.deleteColumn(col1), dynamicTablePage),
                        "1/8", "Column 2",
                        "Microsoft Technologies, Mobile, UXD, Version Control Systems, JavaScript Components and Frameworks, Software Construction, Life Sciences, Content management",
                        "||X||Column 2||\n" +
                                "||Microsoft Technologies||Select\nSee More\nInternet Technologies||\n" +
                                "||Mobile||Select\nSee More\nJQuery Mobile||\n" +
                                "||UXD||Select\nSee More\nGrunt (The JavaScript Task Runner)||\n" +
                                "||Version Control Systems||Select\nSee More\nTortoiseSVN||\n" +
                                "||JavaScript Components and Frameworks||Select\nSee More\nBootstrap||\n" +
                                "||Software Construction||Select\nSee More\nJavaScript Components and Frameworks||\n" +
                                "||Life Sciences||Select\nSee More\nChemistry||\n" +
                                "||Content management||Select\nSee More\nAdobe Day CRX||",
                        "1 column, 3 column"},
                {new TableProvider("deleteLastColumn", dynamicTablePage.dynamicTable, Table::hasAllHeaders,
                        dynamicTablePage -> dynamicTablePage.deleteColumn(col2), dynamicTablePage),
                        "1/8", "Column 1",
                        "Microsoft Technologies, Mobile, UXD, Version Control Systems, JavaScript Components and Frameworks, Software Construction, Life Sciences, Content management",
                        "||X||Column 1||\n" +
                                "||Microsoft Technologies||Select\nSee More\n.NET Technologies||\n" +
                                "||Mobile||Select\nSee More\nHTML5/CSS3||\n" +
                                "||UXD||Select\nSee More\n(X)CSS Development||\n" +
                                "||Version Control Systems||Select\nSee More\nCVS||\n" +
                                "||JavaScript Components and Frameworks||Select\nSee More\nBackbone marionette js||\n" +
                                "||Software Construction||Select\nSee More\nInternet Technologies||\n" +
                                "||Life Sciences||Select\nSee More\nBiology||\n" +
                                "||Content management||Select\nSee More\nDrupal||",
                        "1 column, 2 column"}
        };
    }

    @DataProvider(name = "changeRowsSet")
    public static Object[][] changeRows() {
        return new Object[][]{
                {new TableProvider("increaseRowCount", sortingTablePage.sortingTable, table -> table,
                        sortingTablePage -> sortingTablePage.setRowCount(Twenty), sortingTablePage),
                        "3/11", "Type, Now, Plans",
                        "1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11",
                        "||X||Type|Now|Plans||\n" +
                                "||1||ADO.NET|Entity Framework|LINQ||\n" +
                                "||2||ASP.NET|AJAX|LINQ||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||\n" +
                                "||5||Certificates|Corporate|Cognos Planning||\n" +
                                "||6||Cognos PowerPlay|Hyperion Essbase|Oracle OLAP||\n" +
                                "||7||Domain Experience|Back- and Mid-Office Related|Front-Office Related||\n" +
                                "||8||Drivers|Selenium Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
                                "||9||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
                                "||10||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
                                "||11||Test Runner|TestNG, JUnit Custom|MSTest, NUnit, Epam||"},
                {new TableProvider("decreaseRowCount", sortingTablePage.sortingTable, table -> table,
                        sortingTablePage -> sortingTablePage.setRowCount(Twenty).setRowCount(RowNumbers.Five), sortingTablePage),
                        "3/5", "Type, Now, Plans",
                        "1, 2, 3, 4, 5",
                        "||X||Type|Now|Plans||\n" +
                                "||1||ADO.NET|Entity Framework|LINQ||\n" +
                                "||2||ASP.NET|AJAX|LINQ||\n" +
                                "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
                                "||4||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||\n" +
                                "||5||Certificates|Corporate|Cognos Planning||"},
                {new TableProvider("sortByColumn", sortingTablePage.sortingTable, table -> table,
                        sortingTablePage -> sortingTablePage.sortTableByHeader("Now"), sortingTablePage),
                        "3/5", "Type, Now, Plans",
                        "1, 2, 3, 4, 5",
                        "||X||Type|Now|Plans||\n" +
                                "||1||ASP.NET|AJAX|LINQ||\n" +
                                "||2||Domain Experience|Back- and Mid-Office Related|Front-Office Related||\n" +
                                "||3||Certificates|Corporate|Cognos Planning||\n" +
                                "||4||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||\n" +
                                "||5||ADO.NET|Entity Framework|LINQ||"}
        };
    }
}

