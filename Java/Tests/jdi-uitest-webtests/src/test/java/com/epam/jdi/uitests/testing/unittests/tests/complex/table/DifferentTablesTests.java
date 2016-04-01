package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.VerifyTestListener;
import org.testng.annotations.Listeners;

/**
 * Created by Natalia_Grebenshchikova on 10/21/2015.
 */
@Listeners({VerifyTestListener.class})
public class DifferentTablesTests extends InitTests {
/* TODO
    @Test(dataProvider = "differentTables", dataProviderClass = TableDP.class)
    public void tableSetupTests(Preconditions page, TableProvider tableProvider, String dimension, String columnHeaders, String rowHeaders, String tableAsText, String footer) {

        ITable table = tableProvider.getTable();
        isInState(page);

        new Verify("Dimensions").areEquals(table.columns().count() + "/" + table.rows().count(), dimension);
        new Verify("Columns headers").areEquals(print(table.columns().headers()), columnHeaders);
        new Verify("Rows headers").areEquals(print(table.rows().headers()), rowHeaders);
        new Verify("Table print").areEquals("\n" + table.getValue() + "\n", "\n" + tableAsText + "\n");
        new Verify("Footer").areEquals(print(table.footer()), footer);
    }*/
}
