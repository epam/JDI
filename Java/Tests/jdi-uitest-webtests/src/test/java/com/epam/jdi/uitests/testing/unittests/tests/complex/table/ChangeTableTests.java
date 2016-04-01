package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.TableTestBase;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.VerifyTestListener;
import org.testng.annotations.Listeners;

/**
 * Created by Natalia_Grebenshchikova on 12/17/2015.
 */
@Listeners({VerifyTestListener.class})
public class ChangeTableTests extends TableTestBase {
    // TODO
        /*
    @Test(dataProvider = "changeColumnSet", dataProviderClass = DynamicTableDP.class)
    public void addNewColumn(TableProvider objTable, String dimension, String columnHeaders, String rowHeaders, String tableAsText, String footer) {

        isInState(DYNAMIC_TABLE_PAGE);
        ITable table = objTable.getTable();
        objTable.changeColumns();

        new Verify("Dimensions").areEquals(table.columns().count() + "/" + table.rows().count(), dimension);
        new Verify("Columns headers").areEquals(print(table.columns().headers()), columnHeaders);
        new Verify("Rows headers").areEquals(print(table.rows().headers()), rowHeaders);
        new Verify("Table print").areEquals("\n" + table.getValue() + "\n", "\n" + tableAsText + "\n");
        new Verify("Footer").areEquals(print(table.footer()), footer);
    }
    @Test(dataProvider = "changeRowsSet", dataProviderClass = DynamicTableDP.class)
    public void changeRowSet(TableProvider objTable, String dimension, String columnHeaders, String rowHeaders, String tableAsText){

        isInState(SORTING_TABLE_PAGE);
        ITable table = objTable.getTable();
        objTable.changeRows();

        new Verify("Dimensions").areEquals(table.columns().count() + "/" + table.rows().count(), dimension);
        new Verify("Columns headers").areEquals(print(table.columns().headers()), columnHeaders);
        new Verify("Rows headers").areEquals(print(table.rows().headers()), rowHeaders);
        new Verify("Table print").areEquals("\n" + table.getValue() + "\n", "\n" + tableAsText + "\n");
    }
*/
}
