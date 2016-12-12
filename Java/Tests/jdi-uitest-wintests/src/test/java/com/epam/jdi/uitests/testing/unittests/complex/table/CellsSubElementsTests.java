package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.composite.DynamicTable;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Check;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;


public class CellsSubElementsTests extends InitTests {
    private Supplier<DynamicTable> complexTableSupplier =
            () -> mainWindow.mainTabPane.complexTablePageTab.nestedComplexTableView.table;

    private Supplier<Table> simpleTableSupplier =
            () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.simpleTable;


    @BeforeMethod
    private void before(Method method) throws IOException {
        isInState(Preconditions.COMPLEX_TABLE_PAGE, method);
    }

    @Test
    public void clickCellLinkTest() throws InstantiationException, IllegalAccessException {
        complexTableSupplier.get().getLabel(2, 2).click();
        new Check("Actual Log Info").matches(mainWindow.logTextBox.getLines()[0],
                "([0-9]{1,2}:){2}[0-9]{2} :See More link clicked");
    }

    @Test
    public void clickHiddenCellLinkTest() {
        complexTableSupplier.get().getLabel(2, 8).click();

        new Check("Actual Log Info").matches(mainWindow.logTextBox.getLines()[0],
                "([0-9]{1,2}:){2}[0-9]{2} :See More link clicked");
    }

    @Test
    public void checkCellsCheckBoxTest() {
        complexTableSupplier.get().clickCellCheckBox(2, 2);

        new Verify("CheckBox is Checked").isTrue(complexTableSupplier.get().isCellCheckBoxChecked(2, 2));
        new Verify("Actual Log Info").matches(mainWindow.logTextBox.getLines()[0], "([0-9]{2}:){2}[0-9]{2} Select: condition changed to true");
        new Verify("Log Lines Count").areEquals(mainWindow.logTextBox.getLines().length, 1);
    }

    @Test
    public void clickableCellTest(){
        isInState(Preconditions.SIMPLE_TABLE_PAGE);

        simpleTableSupplier.get().cell(2, 2).click();

        new Check("Actual Log").matches(mainWindow.logTextBox.getLines()[0],
                "([0-9]{1,2}:){2}[0-9]{2} :value=TestNG, JUnit, Custom; cell has been selected");
    }
}
