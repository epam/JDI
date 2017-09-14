package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.column;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Row.row;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static java.lang.String.format;


public class SearchCellsTests extends InitTests {
    private Supplier<Table> simpleTableSupplier =
            () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.simpleTable;

    @BeforeMethod
    public void before(Method method) {
        isInState(Preconditions.SIMPLE_TABLE_PAGE, method);
    }

    @Test
    public void cellsEqualsTest() {
        checkCells(simpleTableSupplier.get().cells("MSTest, NUnit, Epam"));
    }

    @Test
    public void cellsMatchTest() {
        checkCells(simpleTableSupplier.get().cellsMatch(".*Test, NUnit, Epam"));
    }

    @Test
    public void cellEqualsTest() {
        checkCell(simpleTableSupplier.get().cell("MSTest, NUnit, Epam"));
    }

    @Test
    public void cellMatchTest() {
        checkCell(simpleTableSupplier.get().cellMatch(".*Test, NUnit, Epam"));
    }

    @Test
    public void cellInColumnNumEqualsTest() {
        checkCell(simpleTableSupplier.get().cell("MSTest, NUnit, Epam", column(3)));
    }

    @Test
    public void cellInColumnNameEqualsTest() {
        ICell plans = simpleTableSupplier.get().cell("MSTest, NUnit, Epam", column("Plans"));
        checkCell(plans);
    }

    @Test
    public void cellInRowNumEqualsTest() {
        checkCell(simpleTableSupplier.get().cell("MSTest, NUnit, Epam", row(2)));
    }

    @Test
    public void cellInRowNameEqualsTest() {
        checkCell(simpleTableSupplier.get().cell("MSTest, NUnit, Epam", row("2")));
    }

    @Test
    public void cellsMatchInColumnNumEqualsTest() {
        checkCells(simpleTableSupplier.get().cellsMatch(".*Test, NUnit, Epam", column(3)));
    }

    @Test
    public void cellsMatchInColumnNameEqualsTest() {
        checkCells(simpleTableSupplier.get().cellsMatch(".*MSTest, NUnit, Epam", column("Plans")));
    }

    @Test
    public void cellsMatchInRowNumEqualsTest() {
        List<ICell> cells = simpleTableSupplier.get().cellsMatch(".*MSTest, NUnit, Epam", row(2));
        areEquals(cells.size(), 1);
        checkCell(cells.get(0));
    }

    @Test
    public void cellsMatchInRowNameEqualsTest() {
        List<ICell> cells = simpleTableSupplier.get().cellsMatch(".*MSTest, NUnit, Epam", row("2"));
        areEquals(cells.size(), 1);
        checkCell(cells.get(0));
    }

    private void checkCells(List<ICell> cells) {
        new Check("Cells size").areEquals(cells.size(), 2);
        new Check("Cell 1 coordinates").areEquals(format("Value: %s; %s/%s; %s/%s",
                cells.get(0).getValue(), cells.get(0).columnName(), cells.get(0).rowName(),
                cells.get(0).columnNum(), cells.get(0).rowNum()), "Value: MSTest, NUnit, Epam; Plans/2; 3/2");
        new Check("Cell 2 coordinates").areEquals(format("Value: %s; %s/%s; %s/%s",
                cells.get(1).getValue(), cells.get(1).columnName(), cells.get(1).rowName(),
                cells.get(1).columnNum(), cells.get(1).rowNum()), "Value: MSTest, NUnit, Epam; Plans/3; 3/3");
    }

    private void checkCell(ICell cell) {
        areEquals(format("Value: %s; Text: %s; %s/%s; %s/%s",
                cell.getValue(), cell.getText(), cell.columnName(), cell.rowName(),
                cell.columnNum(), cell.rowNum()), "Value: MSTest, NUnit, Epam; Text: MSTest, NUnit, Epam; Plans/2; 3/2");
    }
}
