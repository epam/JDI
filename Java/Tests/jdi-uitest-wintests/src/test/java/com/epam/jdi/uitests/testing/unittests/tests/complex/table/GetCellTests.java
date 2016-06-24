package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.win.winium.elements.complex.table.Column.column;
import static com.epam.jdi.uitests.win.winium.elements.complex.table.Row.row;
import static com.epam.web.matcher.testng.Assert.areEquals;

/**
 * Created by Natalia_Grebenshchikova on 10/21/2015.
 */
public class GetCellTests extends SupportTableTestsBase {
    private final String cellValue = "Log4J, TestNG log, Custom";

    @Test
    public void getCellIntIntTests() {
        areEquals(table().cell(2, 4).getText(), cellValue);
    }

    @Test
    public void getCellStringStringTests() {
        areEquals(table().cell("Now", "4").getText(), cellValue);
    }

    @Test
    public void getCellParamsStringStringTests() {
        areEquals(table().cell(column("Now"), row("4")).getText(), cellValue);
    }

    @Test
    public void getCellParamsIntStringTests() {
        areEquals(table().cell(column(2), row("4")).getText(), cellValue);
    }

    @Test
    public void getCellParamsStringIntTests() {

        areEquals(table().cell(column("Now"), row(4)).getText(), cellValue);
    }

    @Test
    public void getCellParamsIntIntTests() {
        areEquals(table().cell(column(2), row(4)).getText(), cellValue);
    }
}
