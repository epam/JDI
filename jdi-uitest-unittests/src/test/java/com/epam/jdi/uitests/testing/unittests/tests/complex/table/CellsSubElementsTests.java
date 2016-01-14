package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.testing.unittests.pageobjects.composite.DynamicTable;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.DynamicTablePage;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.DynamicTableTestBase;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.VerifyTestListener;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.web.matcher.testng.Check;
import com.epam.web.matcher.verify.Verify;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Function;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.DYNAMIC_TABLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SIMPLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.actionsLog;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dynamicTablePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.simpleTablePage;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Column.column;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Row.row;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.isInState;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.isTrue;
import static com.epam.web.matcher.testng.Assert.matches;

/**
 * Created by Natalia_Grebenshchikova on 12/17/2015.
 */
@Listeners({VerifyTestListener.class})
public class CellsSubElementsTests extends DynamicTableTestBase {

    @Test
    public void clickCellLinkTest() {
        dynamic().getCellLink(2, 2).click();

        new Check("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} :See More link clicked");
    }

    @Test
    public void clickHiddenCellLinkTest() {
        dynamic().getCellLink(2, 8).click();

        new Check("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} :See More link clicked");
    }

    @Test
    public void checkCellsCheckBoxTest() {
        dynamic().clickCellCheckBox(2, 2);

        new Verify("CheckBox is Checked").isTrue(dynamic().isCellCheckBoxChecked(2, 2));
        new Verify("Actual Log Info").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} Select: condition changed to true");
        new Verify("Log Lines Count").areEquals(actionsLog.count(), 1);
    }

    @Test
    public void clickableCellTest(){
        isInState(SIMPLE_PAGE);

        simpleTablePage.getTable(false, false).cell(2, 2).click();

        new Check("Actual Log").matches(actionsLog.getText(0), "([0-9]{2}:){2}[0-9]{2} :value=TestNG, JUnit Custom; cell has been selected");
    }
}


