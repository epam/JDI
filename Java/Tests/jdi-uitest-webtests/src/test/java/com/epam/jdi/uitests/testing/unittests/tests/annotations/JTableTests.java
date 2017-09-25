package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.IPage;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.IRow;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ITable;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.JTableDP;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.*;

public class JTableTests extends InitTests {


    @Test(dataProvider = "tableData", dataProviderClass = JTableDP.class)
    public void tableTest(IPage page, ITable table, String rowAsText, String columnsAsText, String keyWord, String searchResult, int searchResultSize, String firstCell, String headers ){
        simpleTablePage.open();
        System.out.println(simpleTablePage.table.rows().getRowAsText(2));
        new Check().areEquals(table.getText(), JTableDP.supportTableText);
        new Check().isFalse(table.isEmpty());

        new Check().areEquals(table.rows().getRowAsText(2),
                rowAsText);
        new Check().areEquals(table.columns().getColumnAsText(1),
                columnsAsText);
        new Check().areEquals(table.cellsContains(keyWord).get(0).getText(),
                searchResult);
        new Check().areEquals(table.cellsContains(keyWord).size(),
                searchResultSize);
        new Check().areEquals(table.cell(1, 1).getText(),
                firstCell);
        new Check().areEquals(table.headers(),
                headers);



       System.out.println(table.headers());

    }

    @Test
    public void cellsContainsTestForTableWithRoot() {
        List<ICell> jenkins = supportPage.tableRoot.cellsContains("Jenkins");
        new Check().areEquals(jenkins.size(), 1);
        for (ICell cell: jenkins){
            new Check().areEquals(cell.getText(), "Jenkins, Allure, Custom");
        }
    }

}
