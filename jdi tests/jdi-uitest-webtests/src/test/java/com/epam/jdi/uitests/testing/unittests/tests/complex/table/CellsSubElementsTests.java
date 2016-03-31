package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.DynamicTableTestBase;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.VerifyTestListener;
import com.epam.web.matcher.testng.Check;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SIMPLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.actionsLog;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.simpleTablePage;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
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


