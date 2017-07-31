package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ITable;
import com.epam.jdi.uitests.testing.unittests.dataproviders.IndexesDP;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SORTING_TABLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.sortingTablePage;

/**
 * Created by Natalia_Grebenshchik on 10/28/2015.
 */
public class NegativeTableTests extends SupportTableTestsBase {

    //NB indexes lower and higher than scope give different types of exceptions
    @Test(expectedExceptions = Throwable.class,
            expectedExceptionsMessageRegExp = ".*Failed to do 'Get web element' action.*|Table indexes starts from -?[0-9]",
            dataProvider = "cellIndexes", dataProviderClass = IndexesDP.class)
    public void illegalCellIndexTest(int columnIndex, int rowIndex) {
            table().cell(columnIndex, rowIndex).getValue();
    }

    //NB indexes lower and higher than scope give different types of exceptions
    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = "Can't Get Row '-?[0-9]+'. \\[num\\] > ColumnsCount\\([0-9]+\\)."
            +"|Table indexes starts from -?[0-9]",
            dataProvider = "indexes", dataProviderClass = IndexesDP.class)
    public void illegalRowIndexTest(int rowIndex){
        table().row(rowIndex);
    }

    // NB indexes lower and higher than scope give different types of exceptions
    @Test(expectedExceptions = AssertionError.class,
            expectedExceptionsMessageRegExp = "Can't Get Column '[-0-9]*'. \\[num\\] > RowsCount\\([0-9]+\\)."
            +"|Table indexes starts from -?[0-9]",
            dataProvider = "indexes", dataProviderClass = IndexesDP.class)
    public void illegalColumnIndexTest(int columnIndex){
        table().column(columnIndex);
    }

     @Test(expectedExceptions = NullPointerException.class)
     public void illegalHeaderName(){
        table().header("Column_illegal").select();
    }

     @Test(expectedExceptions = NullPointerException.class)
      public void illegalHeaderIndex(){
         table().rows().header("Row_illegal").select();
    }

    @Test(expectedExceptions = AssertionError.class, expectedExceptionsMessageRegExp = "Can't Get Row '[0-9]+'. \\[num\\] > ColumnsCount\\(1\\).")
    public void getBlankTableTest() {
        isInState(SORTING_TABLE_PAGE);
        ITable table = sortingTablePage.sortingTable.hasAllHeaders().useCache(false);
        table.useCache(false);
        sortingTablePage.search("smth");
        //first row is table's headers here
        //if we have just one row and the exception thrown the negative test is passed
        //also it is possible to check table.cell(1,1).getValue() using sell's indexes
        //instead of rowNum
        table.row(2);
    }
}
