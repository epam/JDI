package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.commons.map.MapArray;
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
    @BeforeMethod
    public void pageLoad(){
        supportPage.open();
    }

    @Test(dataProvider = "tableData", dataProviderClass = JTableDP.class)
    public void tableTest(ITable table, Boolean option){

        new Check().areEquals(table.getText(), JTableDP.supportTableText);
        new Check().isFalse(table.isEmpty());

        new Check().areEquals(table.rows().getRowAsText(2),
                "Type:Test Runner, Now:TestNG, JUnit, Custom, Plans:MSTest, NUnit, Epam");
        new Check().areEquals(table.columns().getColumnAsText(1),
                "1:Drivers, 2:Test Runner, 3:Asserter, 4:Logger, 5:Reporter, 6:BDD/DSL");
        new Check().areEquals(table.cellsContains("Custom").get(0).getText(),
                "Selenium, Custom" );
        new Check().areEquals(table.cellsContains("TestNG").size(),
                3);
        new Check().areEquals(table.cell(1, 1).getText(),
                "Drivers");
        new Check().areEquals(table.header().key(2),
                "Plans");
        

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
