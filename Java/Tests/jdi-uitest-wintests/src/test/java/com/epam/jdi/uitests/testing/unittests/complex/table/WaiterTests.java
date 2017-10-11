package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.column;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Row.row;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.*;

public class WaiterTests extends InitTests {
    private Supplier<Table> simpleTableSupplier =
            () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.simpleTable;

    @BeforeMethod
    private void before(Method method) throws IOException {
        isInState(Preconditions.SIMPLE_TABLE_PAGE, method);
    }

    @Test
    public void waitExpectedRowsValueTest() {
        new Check("Find value").isTrue(
                simpleTableSupplier.get().waitValue("Cucumber, Jbehave, Thucydicles, Specflow", row(6)));
    }

    @Test
    public void waitUnexpectedRowsValueTest() {
        new Check("Do not find value").isFalse(simpleTableSupplier.get().
                waitValue("Cucumber, Jbehave, Thucydides, SpecFlow Unexepected", row(6)));
    }

    @Test
    public void waitExpectedColumnsValueTest() {
        new Check("Find value").isTrue(simpleTableSupplier.get().waitValue("Custom", column(2)));
    }

    @Test
    public void waitUnexpectedColumnsValueTest() {
        new Check("Do not find value").isFalse(simpleTableSupplier.get().waitValue("Custom Unexpected", column(2)));
    }

    @Test
    public void tableIsEmptyTest(){
        new Check("Table not empty").isFalse(simpleTableSupplier.get().isEmpty());
    }

    @Test
    public void cellWaitTextTest() {
        areEquals(() -> simpleTableSupplier.get().cell(2, 2).waitText("TestNG, JUnit, Custom"),
                "TestNG, JUnit, Custom");
    }

    @Test
    public void cellWaitMatchTextTest() {
        areEquals(() -> simpleTableSupplier.get().cell(2, 2).waitMatchText("[a-zA-Z, ]*JUnit,[a-zA-Z ]*"), "TestNG, JUnit, Custom");
    }

    @Test
    public void waitHaveRowsTest() {
        isTrue(() -> simpleTableSupplier.get().waitHaveRows());
    }

    @Test
    public void waitRowsTest() {
        isTrue(simpleTableSupplier.get().waitRows(6));
    }

    @Test
    public void waitRowsMoreNumberOfRowsTest() {
        isFalse(simpleTableSupplier.get().waitRows(7));
    }
}
