package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.dataproviders.TableProvider;
import com.epam.jdi.uitests.testing.unittests.dataproviders.TableDP;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.VerifyTestListener;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.isInState;

/**
 * Created by Natalia_Grebenshchikova on 10/21/2015.
 */
@Listeners({VerifyTestListener.class})
public class DifferentTablesTests extends InitTests {

    @Test(dataProvider = "differentTables", dataProviderClass = TableDP.class)
    public void tableSetupTests(Preconditions page, TableProvider tableProvider, String dimension, String columnHeaders, String rowHeaders, String tableAsText, String footer) {

        ITable table = tableProvider.getTable();
        isInState(page);

        new Verify("Dimensions").areEquals(table.columns().count() + "/" + table.rows().count(), dimension);
        new Verify("Columns headers").areEquals(print(table.columns().headers()), columnHeaders);
        new Verify("Rows headers").areEquals(print(table.rows().headers()), rowHeaders);
        new Verify("Table print").areEquals("\n" + table.getValue() + "\n", "\n" + tableAsText + "\n");
        new Verify("Footer").areEquals(print(table.footer()), footer);
    }
}
