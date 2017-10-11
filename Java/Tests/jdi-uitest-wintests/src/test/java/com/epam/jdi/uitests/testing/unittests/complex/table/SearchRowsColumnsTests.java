package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Supplier;

import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.column;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Row.row;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static java.lang.String.format;

public class SearchRowsColumnsTests extends InitTests {
    private static final String EXPECTED_COLUMN =
            "1:Selenium, Custom, " +
                    "2:TestNG, JUnit, Custom, " +
                    "3:TestNG, JUnit, Custom, " +
                    "4:Log4J, TestNG log, Custom, " +
                    "5:Jenkins, Allure, Custom, " +
                    "6:Custom";
    private final static String EXPECTED_ROW =
            "Type:Test Runner, " +
                    "Now:TestNG, JUnit, Custom, " +
                    "Plans:MSTest, NUnit, Epam";
    public static final String ALL_COLUMNS = "Type:[1:Drivers, " +
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
            "5:Epam Report portal, Serenity, TeamCity, Hudson, " +
            "6:Cucumber, Jbehave, Thucydicles, Specflow]";
    public static final String ALL_ROWS = "1:[Type:Drivers, " +
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
            "Plans:Epam Report portal, Serenity, TeamCity, Hudson], " +
            "6:[Type:BDD/DSL, " +
            "Now:Custom, " +
            "Plans:Cucumber, Jbehave, Thucydicles, Specflow]";

    private Supplier<Table> simpleTableSupplier =
            () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.simpleTable;

    @BeforeMethod
    private void before(Method method) throws IOException {
        isInState(Preconditions.SIMPLE_TABLE_PAGE, method);
    }

    @Test
    public void columnByNumTest() throws IllegalAccessException, InstantiationException {
        MapArray<String, ICell> column = simpleTableSupplier.get().column(2);
        String print = print(column.select((rowK, rowV) -> format("%s:%s", rowK, rowV.getText())));
        areEquals(print, EXPECTED_COLUMN);
    }

    @Test
    public void columnByNameTest() {
        MapArray<String, ICell> column = simpleTableSupplier.get().column("Now");
        areEquals(print(column.select((rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_COLUMN);
    }

    @Test
    public void rowByNumTest() {
        MapArray<String, ICell> row = simpleTableSupplier.get().row(2);
        areEquals(print(row.select((rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_ROW);
    }

    @Test
    public void rowByNameTest() {
        MapArray<String, ICell> row = simpleTableSupplier.get().row("2");
        areEquals(print(row.select((rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_ROW);
    }

    @Test
    public void columnByCriteriaIntTest() {
        MapArray<String, ICell> column = simpleTableSupplier.get().column("TestNG, JUnit, Custom", row(3));
        areEquals(print(column.select((rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_COLUMN);
    }

    @Test
    public void columnByCriteriaStringTest() {
        MapArray<String, ICell> column = simpleTableSupplier.get().column("TestNG, JUnit, Custom", row("3"));
        areEquals(print(column.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_COLUMN);
    }

    @Test
    public void rowByCriteriaIntTest() {
        MapArray<String, ICell> row = simpleTableSupplier.get().row("MSTest, NUnit, Epam", column(3));
        areEquals(print(row.select(
                        (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_ROW);
    }

    @Test
    public void rowByCriteriaStringTest() {
        MapArray<String, ICell> row = simpleTableSupplier.get().row("MSTest, NUnit, Epam", column("Plans"));
        areEquals(print(row.select((rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))), EXPECTED_ROW);
    }

    @Test
    public void rowsByCriteriaTest() {
        MapArray<String, MapArray<String, ICell>> rows = simpleTableSupplier.get().rows("Plans=MSTest, NUnit, Epam");
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
        MapArray<String, MapArray<String, ICell>> rows =
                simpleTableSupplier.get().rows("Plans=MSTest, NUnit, Epam", "Type=Asserter");
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
        MapArray<String, MapArray<String, ICell>> columns = simpleTableSupplier.get().columns("1=Selenium, Custom");
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
        MapArray<String, MapArray<String, ICell>> columns =
                simpleTableSupplier.get().columns("2=Test Runner", "4=Logger");
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
        MapArray<String, MapArray<String, ICell>> columns = simpleTableSupplier.get().columns().get();
        new Check("Columns count").areEquals(columns.size(), 3);

        new Check("Columns content").areEquals(print(columns.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))), ALL_COLUMNS);
    }

    @Test
    public void columnsGetAsTextTest() {
        MapArray<String, MapArray<String, String>> columns = simpleTableSupplier.get().columns().getAsText();
        new Check("Columns count").areEquals(columns.size(), 3);
        new Check("Columns content").areEquals(print(columns.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV))))), ALL_COLUMNS);
    }

    @Test
    public void columnsGetByNumTest() {
        MapArray<String, ICell> column = simpleTableSupplier.get().columns().getColumn(2);
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), EXPECTED_COLUMN);
    }

    @Test
    public void columnsGetByNameTest() {
        MapArray<String, ICell> column = simpleTableSupplier.get().columns().getColumn("Now");
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), EXPECTED_COLUMN);
    }

    @Test
    public void columnsGetByNumAsTextTest() {
        MapArray<String, String> column = simpleTableSupplier.get().columns().getColumnAsText(2);
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), EXPECTED_COLUMN);
    }

    @Test
    public void columnsGetByNameAsTextTest() {
        MapArray<String, String> column = simpleTableSupplier.get().columns().getColumnAsText("Now");
        new Check("Column content").areEquals(print(column.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), EXPECTED_COLUMN);
    }

    @Test
    public void rowsGetTest() {
        MapArray<String, MapArray<String, ICell>> rows = simpleTableSupplier.get().rows().get();
        new Check("Rows count").areEquals(rows.size(), 6);
        new Check("Rows content").areEquals(print(rows.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV.getText()))))), ALL_ROWS);
    }

    @Test
    public void rowsGetAsTextTest() {
        MapArray<String, MapArray<String, String>> rows = simpleTableSupplier.get().rows().getAsText();
        new Check("Rows count").areEquals(rows.size(), 6);
        new Check("Rows content").areEquals(print(rows.select(
                        (k, v) -> format("%s:%s", k, v.select(
                                (rowK, rowV) -> format("%s:%s", rowK, rowV))))), ALL_ROWS);
    }

    @Test
    public void rowsGetByNumTest() {
        MapArray<String, ICell> row = simpleTableSupplier.get().rows().getRow(2);
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), EXPECTED_ROW);
    }

    @Test
    public void rowsGetByNameTest() {
        MapArray<String, ICell> row = simpleTableSupplier.get().rows().getRow("2");
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV.getValue()))), EXPECTED_ROW);
    }

    @Test
    public void rowsGetByNumAsTextTest() {
        MapArray<String, String> row = simpleTableSupplier.get().rows().getRowAsText(2);
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), EXPECTED_ROW);
    }

    @Test
    public void rowsGetByNameAsTextTest() {
        MapArray<String, String> row = simpleTableSupplier.get().rows().getRowAsText("2");
        new Check("Row content").areEquals(print(row.select(
                (rowK, rowV) -> format("%s:%s", rowK, rowV))), EXPECTED_ROW);
    }

    @Test
    public void rowValueByNameTests() {
        new Check("Row Value").areEquals(simpleTableSupplier.get().rowValue("2"),
                Arrays.asList("Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"));
    }

    @Test
    public void rowValueByNumberTests() {
        new Check("Row Value").areEquals(simpleTableSupplier.get().rowValue(2),
                Arrays.asList("Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"));
    }

    @Test
    public void columnValueByNameTests() {
        new Check("Column Value").areEquals(simpleTableSupplier.get().columnValue("Now"),
                Arrays.asList("Selenium, Custom", "TestNG, JUnit, Custom", "TestNG, JUnit, Custom", "Log4J, TestNG log, Custom", "Jenkins, Allure, Custom", "Custom"));
    }

    @Test
    public void columnValueByNumberTests() {
        new Check("Column Value").areEquals(simpleTableSupplier.get().columnValue(2),
                Arrays.asList("Selenium, Custom", "TestNG, JUnit, Custom", "TestNG, JUnit, Custom", "Log4J, TestNG log, Custom", "Jenkins, Allure, Custom", "Custom"));
    }
}
