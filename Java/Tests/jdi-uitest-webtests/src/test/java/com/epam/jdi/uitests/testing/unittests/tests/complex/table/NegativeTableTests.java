package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.dataproviders.IndexesDP;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SORTING_TABLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.sortingTablePage;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;

/**
 * Created by Natalia_Grebenshchik on 10/28/2015.
 */
public class NegativeTableTests extends SupportTableTestsBase {
    /* TODO fix tests
    @Test(  expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = "Failed to do 'Get web element' action. Reason: java.lang.RuntimeException: java.lang.AssertionError: Can't get Web Elements",
            dataProvider = "cellIndexes",dataProviderClass = IndexesDP.class)
    public void illegalCellIndexTest(int columnIndex, int rowIndex){
        ((Element)table().cell(columnIndex, rowIndex)).getWebElement();
    }*/

    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = "Can't Get Row '[-0-9]*'. \\[num\\] > ColumnsCount\\([-0-9]*\\).",
            dataProvider = "indexes", dataProviderClass = IndexesDP.class)
    public void illegalRowIndexTest(int rowIndex){
        table().row(rowIndex);
    }

    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = "Can't Get Column '[-0-9]*'. \\[num\\] > RowsCount\\([-0-9]*\\).",
            dataProvider = "indexes", dataProviderClass = IndexesDP.class)
    public void illegalColumnIndexTest(int columnIndex){
        table().column(columnIndex);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void illegalHeaderName(){
        table().header("Column_illegal").getWebElement();
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void illegalHeaderIndex(){
        table().rows().header("Row_illegal").getWebElement();
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Can't Get Row 'No matching records found'. Reason: Index: 0, Size: 0")
    public void getBlankTableTest(){
        isInState(SORTING_TABLE_PAGE);
        ITable table = sortingTablePage.sortingTable.hasAllHeaders().useCache(false);
        table.useCache(false);

        sortingTablePage.search("smth");
        table.getCells();
    }
}
