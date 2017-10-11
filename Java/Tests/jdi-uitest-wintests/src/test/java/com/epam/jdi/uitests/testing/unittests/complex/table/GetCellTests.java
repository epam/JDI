package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.column;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Row.row;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.areEquals;

public class GetCellTests extends InitTests {
    private static final String cellValue = "Log4J, TestNG log, Custom";
    private Supplier<Table> tableSupplier = () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.simpleTable;

    @BeforeMethod
    public void before(Method method) {
        isInState(Preconditions.SIMPLE_TABLE_PAGE, method);
    }

    @Test
    public void getCellIntIntTests() throws InstantiationException, IllegalAccessException {
        areEquals(tableSupplier.get().cell(2, 4).getText(), cellValue);
    }

    @Test
    public void getCellStringStringTests() {
        areEquals(tableSupplier.get().cell("Now", "4").getText(), cellValue);
    }

    @Test
    public void getCellParamsStringStringTests() {
        areEquals(tableSupplier.get().cell(column("Now"), row("4")).getText(), cellValue);
    }

    @Test
    public void getCellParamsIntStringTests() {
        areEquals(tableSupplier.get().cell(column(2), row("4")).getText(), cellValue);
    }

    @Test
    public void getCellParamsStringIntTests() {
        areEquals(tableSupplier.get().cell(column("Now"), row(4)).getText(), cellValue);
    }

    @Test
    public void getCellParamsIntIntTests() {
        areEquals(tableSupplier.get().cell(column(2), row(4)).getText(), cellValue);
    }
}
