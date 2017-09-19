package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
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
    @BeforeMethod
    public void pageLoad(){
        supportPage.open();
    }

    @Test(dataProvider = "tableData", dataProviderClass = JTableDP.class)
    public void tableTest(ITable table, Boolean option){
       new Check().areEquals(table.getText(), JTableDP.supportTableText);
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
