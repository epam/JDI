package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import com.epam.jdi.uitests.win.winium.elements.complex.table.interfaces.ICell;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.jdi.uitests.win.winium.elements.complex.table.Column.column;
import static com.epam.jdi.uitests.win.winium.elements.complex.table.Row.row;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static java.lang.String.format;

/**
 * Created by Natalia_Grebenshchikova on 10/21/2015.
 */
public class SearchCellsTests extends SupportTableTestsBase {
    @Test
    public void cellsEqualsTest() {
        checkCells(table().cells("MSTest, NUnit, Epam"));
    }

    @Test
    public void cellsMatchTest() {
        checkCells(table().cellsMatch(".*Test, NUnit, Epam"));
    }

    @Test
    public void cellEqualsTest() {
        checkCell(table().cell("MSTest, NUnit, Epam"));
    }

    @Test
    public void cellMatchTest() {
        checkCell(table().cellMatch(".*Test, NUnit, Epam"));
    }

    @Test
    public void cellInColumnNumEqualsTest() {
        checkCell(table().cell("MSTest, NUnit, Epam", column(3)));
    }

    @Test
    public void cellInColumnNameEqualsTest() {
        checkCell(table().cell("MSTest, NUnit, Epam", column("Plans")));
    }

    @Test
    public void cellInRowNumEqualsTest() {
        checkCell(table().cell("MSTest, NUnit, Epam", row(2)));
    }

    @Test
    public void cellInRowNameEqualsTest() {
        checkCell(table().cell("MSTest, NUnit, Epam", row("2")));
    }

    @Test
    public void cellsMatchInColumnNumEqualsTest() {
        checkCells(table().cellsMatch(".*Test, NUnit, Epam", column(3)));
    }

    @Test
    public void cellsMatchInColumnNameEqualsTest() {
        checkCells(table().cellsMatch(".*MSTest, NUnit, Epam", column("Plans")));
    }

    @Test
    public void cellsMatchInRowNumEqualsTest() {
        List<ICell> cells = table().cellsMatch(".*MSTest, NUnit, Epam", row(2));
        areEquals(cells.size(), 1);
        checkCell(cells.get(0));
    }

    @Test
    public void cellsMatchInRowNameEqualsTest() {
        List<ICell> cells = table().cellsMatch(".*MSTest, NUnit, Epam", row("2"));
        areEquals(cells.size(), 1);
        checkCell(cells.get(0));
    }

    private void checkCell(ICell cell) {
        areEquals(format("Value: %s; Text: %s; %s/%s; %s/%s",
                cell.getValue(), cell.getText(), cell.columnName(), cell.rowName(),
                cell.columnNum(), cell.rowNum()), "Value: MSTest, NUnit, Epam; Text: MSTest, NUnit, Epam; Plans/2; 3/2");
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
}
