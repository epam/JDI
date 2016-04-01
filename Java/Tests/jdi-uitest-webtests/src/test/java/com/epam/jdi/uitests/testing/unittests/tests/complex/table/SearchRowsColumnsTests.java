package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Column.column;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Row.row;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static java.lang.String.format;

/**
 * Created by Natalia_Grebenshchikova on 10/21/2015.
 */
public class SearchRowsColumnsTests extends SupportTableTestsBase {

    private static final String expectedColumn =
            "1:Selenium, Custom, " +
                    "2:TestNG, JUnit, Custom, " +
                    "3:TestNG, JUnit, Custom, " +
                    "4:Log4J, TestNG log, Custom, " +
                    "5:Jenkins, Allure, Custom, " +
                    "6:Custom";
    private final static String expectedRow =
            "Type:Asserter, " +
                    "Now:TestNG, JUnit, Custom, " +
                    "Plans:MSTest, NUnit, Epam";

    @Test
    public void columnByNumTest() {
        MapArray<String, ICell> column = table().column(2);
        areEquals(print(column.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom");
    }

    @Test
    public void columnByNameTest() {
        MapArray<String, ICell> column = table().column("Now");
        areEquals(print(column.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom");
    }

    @Test
    public void rowByNumTest() {
        MapArray<String, ICell> row = table().row(2);
        areEquals(print(row.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam");
    }

    @Test
    public void rowByNameTest() {
        MapArray<String, ICell> row = table().row("2");
        areEquals(print(row.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam");
    }

    @Test
    public void columnByCriteriaIntTest() {
        MapArray<String, ICell> column = table().column("TestNG, JUnit, Custom", row(3));
        areEquals(print(column.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom");
    }

    @Test
    public void columnByCriteriaStringTest() {
        MapArray<String, ICell> column = table().column("TestNG, JUnit, Custom", row("3"));
        areEquals(print(column.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom");
    }

    @Test
    public void rowByCriteriaIntTest() {
        MapArray<String, ICell> row = table().row("MSTest, NUnit, Epam", column(3));
        areEquals(print(row.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam");
    }

    @Test
    public void rowByCriteriaStringTest() {
        MapArray<String, ICell> row = table().row("MSTest, NUnit, Epam", column("Plans"));
        areEquals(print(row.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))),
                "Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam");
    }

    @Test
    public void rowsByCriteriaTest() {
        MapArray<String, MapArray<String, ICell>> rows = table().rows("Plans=MSTest, NUnit, Epam");
        new Check("Rows count").areEquals(rows.size(), 2);
        new Check("Rows content").areEquals(print(rows.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))),
                "2:[Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam], " +
                        "3:[Type:Asserter, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam]");
    }

    @Test
    public void rowsByTwoCriteriasTest() {
        MapArray<String, MapArray<String, ICell>> rows = table().rows("Plans=MSTest, NUnit, Epam", "Type=Asserter");
        new Check("Rows count").areEquals(rows.size(), 1);
        new Check("Rows content").areEquals(print(rows.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))),
                "3:[Type:Asserter, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam]");
    }

    @Test
    public void columnsByCriteriaTest() {
        MapArray<String, MapArray<String, ICell>> columns = table().columns("1=Selenium, Custom");
        new Check("Columns count").areEquals(columns.size(), 1);
        new Check("Columns content").areEquals(print(columns.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))),
                "Now:[1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom]");
    }

    @Test
    public void columnsByTwoCriteriasTest() {
        MapArray<String, MapArray<String, ICell>> columns = table().columns("2=Test Runner", "4=Logger");
        new Check("Columns count").areEquals(columns.size(), 1);
        new Check("Columns content").areEquals(print(columns.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))),
                "Type:[1:Drivers, " +
                        "2:Test Runner, " +
                        "3:Asserter, " +
                        "4:Logger, " +
                        "5:Reporter, " +
                        "6:BDD/DSL]");
    }

    @Test
    public void columnsGetTest() {
        MapArray<String, MapArray<String, ICell>> columns = table().columns().get();
        new Check("Columns count").areEquals(columns.size(), 3);

        new Check("Columns content").areEquals(print(columns.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))),
                "Type:[1:Drivers, " +
                        "2:Test Runner, " +
                        "3:Asserter, " +
                        "4:Logger, " +
                        "5:Reporter, " +
                        "6:BDD/DSL], " +
                        "Now:[1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom], " +
                        "Plans:[1:JavaScript, Appium, WinAPI, Sikuli, " +
                        "2:MSTest, NUnit, Epam, " +
                        "3:MSTest, NUnit, Epam, " +
                        "4:Epam, XML/Json logging, Hyper logging, " +
                        "5:EPAM Report portal, Serenity, TimCity, Hudson, " +
                        "6:Cucumber, Jbehave, Thucydides, SpecFlow]");
    }

    @Test
    public void columnsGetAsTextTest() {
        MapArray<String, MapArray<String, String>> columns = table().columns().getAsText();
        new Check("Columns count").areEquals(columns.size(), 3);
        new Check("Columns content").areEquals(print(columns.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV))))),
                "Type:[1:Drivers, " +
                        "2:Test Runner, " +
                        "3:Asserter, " +
                        "4:Logger, " +
                        "5:Reporter, " +
                        "6:BDD/DSL], " +
                        "Now:[1:Selenium, Custom, " +
                        "2:TestNG, JUnit, Custom, " +
                        "3:TestNG, JUnit, Custom, " +
                        "4:Log4J, TestNG log, Custom, " +
                        "5:Jenkins, Allure, Custom, " +
                        "6:Custom], " +
                        "Plans:[1:JavaScript, Appium, WinAPI, Sikuli, " +
                        "2:MSTest, NUnit, Epam, " +
                        "3:MSTest, NUnit, Epam, " +
                        "4:Epam, XML/Json logging, Hyper logging, " +
                        "5:EPAM Report portal, Serenity, TimCity, Hudson, " +
                        "6:Cucumber, Jbehave, Thucydides, SpecFlow]");
    }

    @Test
    public void columnsGetByNumTest() {
        MapArray<String, ICell> column = table().columns().getColumn(2);
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), expectedColumn);
    }

    @Test
    public void columnsGetByNameTest() {
        MapArray<String, ICell> column = table().columns().getColumn("Now");
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), expectedColumn);
    }

    @Test
    public void columnsGetByNumAsTextTest() {
        MapArray<String, String> column = table().columns().getColumnAsText(2);
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), expectedColumn);
    }

    @Test
    public void columnsGetByNameAsTextTest() {
        MapArray<String, String> column = table().columns().getColumnAsText("Now");
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), expectedColumn);
    }

    @Test
    public void rowsGetTest() {
        MapArray<String, MapArray<String, ICell>> rows = table().rows().get();
        new Check("Rows count").areEquals(rows.size(), 6);
        new Check("Rows content").areEquals(print(rows.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))),
                "1:[Type:Drivers, " +
                        "Now:Selenium, Custom, " +
                        "Plans:JavaScript, Appium, WinAPI, Sikuli], " +
                        "2:[Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam], " +
                        "3:[Type:Asserter, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam], " +
                        "4:[Type:Logger, " +
                        "Now:Log4J, TestNG log, Custom, " +
                        "Plans:Epam, XML/Json logging, Hyper logging], " +
                        "5:[Type:Reporter, " +
                        "Now:Jenkins, Allure, Custom, " +
                        "Plans:EPAM Report portal, Serenity, TimCity, Hudson], " +
                        "6:[Type:BDD/DSL, " +
                        "Now:Custom, " +
                        "Plans:Cucumber, Jbehave, Thucydides, SpecFlow]");
    }

    @Test
    public void rowsGetAsTextTest() {
        MapArray<String, MapArray<String, String>> rows = table().rows().getAsText();
        new Check("Rows count").areEquals(rows.size(), 6);
        new Check("Rows content").areEquals(print(rows.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV))))),
                "1:[Type:Drivers, " +
                        "Now:Selenium, Custom, " +
                        "Plans:JavaScript, Appium, WinAPI, Sikuli], " +
                        "2:[Type:Test Runner, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam], " +
                        "3:[Type:Asserter, " +
                        "Now:TestNG, JUnit, Custom, " +
                        "Plans:MSTest, NUnit, Epam], " +
                        "4:[Type:Logger, " +
                        "Now:Log4J, TestNG log, Custom, " +
                        "Plans:Epam, XML/Json logging, Hyper logging], " +
                        "5:[Type:Reporter, " +
                        "Now:Jenkins, Allure, Custom, " +
                        "Plans:EPAM Report portal, Serenity, TimCity, Hudson], " +
                        "6:[Type:BDD/DSL, " +
                        "Now:Custom, " +
                        "Plans:Cucumber, Jbehave, Thucydides, SpecFlow]");
    }

    @Test
    public void rowsGetByNumTest() {
        MapArray<String, ICell> row = table().rows().getRow(3);
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), expectedRow);
    }

    @Test
    public void rowsGetByNameTest() {
        MapArray<String, ICell> row = table().rows().getRow("3");
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), expectedRow);
    }

    @Test
    public void rowsGetByNumAsTextTest() {
        MapArray<String, String> row = table().rows().getRowAsText(3);
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), expectedRow);
    }

    @Test
    public void rowsGetByNameAsTextTest() {
        MapArray<String, String> row = table().rows().getRowAsText("3");
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), expectedRow);
    }

    @Test
    public void cellsToColumnTest(){
        MapArray<String, ICell> cellsToColumn = table().columns().cellsToColumn(Arrays.asList(table().cell(1, 1), table().cell(2, 2)));

        new Check("Columns Headers").areEquals("Type, Now", cellsToColumn.key(0)+", "+cellsToColumn.key(1));
        new Check("Cells Values").areEquals("Drivers, TestNG, JUnit, Custom", print(new String[]{cellsToColumn.value(0).getValue(), cellsToColumn.value(1).getValue()}));
    }

    @Test
    public void cellsToRowTest(){
        MapArray<String, ICell> cellsToRow = table().rows().cellsToRow(Arrays.asList(table().cell(1, 1), table().cell(2, 2)));

        new Check("Rows Indexes").areEquals("1, 2", cellsToRow.key(0)+", "+cellsToRow.key(1));
        new Check("Cells Values").areEquals("Drivers, TestNG, JUnit, Custom", print(new String[]{cellsToRow.value(0).getValue(), cellsToRow.value(1).getValue()}));
    }

    @Test
    public void rowValueByNameTests() {
        new Check("Row Value").areEquals(table().rowValue("2"), Arrays.asList("Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"));
    }

    @Test
    public void rowValueByNumberTests() {
        new Check("Row Value").areEquals(table().rowValue(2), Arrays.asList("Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"));
    }

    @Test
    public void columnValueByNameTests() {
        new Check("Column Value").areEquals(table().columnValue("Now"), Arrays.asList("Selenium, Custom", "TestNG, JUnit, Custom", "TestNG, JUnit, Custom", "Log4J, TestNG log, Custom", "Jenkins, Allure, Custom", "Custom"));
    }

    @Test
    public void columnValueByNumberTests() {
        new Check("Column Value").areEquals(table().columnValue(2), Arrays.asList("Selenium, Custom", "TestNG, JUnit, Custom", "TestNG, JUnit, Custom", "Log4J, TestNG log, Custom", "Jenkins, Allure, Custom", "Custom"));
    }

}
