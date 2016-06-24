package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.*;
import static com.epam.jdi.uitests.win.winium.elements.complex.table.Column.column;
import static com.epam.jdi.uitests.win.winium.elements.complex.table.Row.row;
import static com.epam.web.matcher.testng.Assert.isFalse;
import static com.epam.web.matcher.testng.Assert.isTrue;

/**
 * Created by Natalia_Grebenshchikova on 10/5/2015.
 */
public class WaiterTests extends SupportTableTestsBase {

    @Test
    public void waitExpectedRowsValueTest() {
        new Check("Find value").isTrue(table().waitValue("Cucumber, Jbehave, Thucydides, SpecFlow", row(6)));
    }

    @Test
    public void waitUnexpectedRowsValueTest() {
        new Check("Do not find value").isFalse(table().waitValue("Cucumber, Jbehave, Thucydides, SpecFlow Unexepected", row(6)));
    }

    @Test
    public void waitExpectedColumnsValueTest() {
        new Check("Find value").isTrue(table().waitValue("Custom", column(2)));
    }

    @Test
    public void waitUnexpectedColumnsValueTest() {
        new Check("Do not find value").isFalse(table().waitValue("Custom Unexpected", column(2)));
    }

    @Test
    public void tableIsEmptyTest(){
        new Check("Table not empty").isFalse(table().isEmpty());
    }

    @Test
    public void cellWaitTextTest() {
        isInState(HOME_PAGE);
        runParallel(SUPPORT_PAGE::open);
        checkText(() -> table().cell(2, 2).waitText("TestNG, JUnit, Custom"), "TestNG, JUnit, Custom");

        isTrue(timer.timePassedInMSec() > waitTimeOut);
    }

    @Test
    public void cellWaitMatchTextTest() {
        isInState(HOME_PAGE);
        runParallel(SUPPORT_PAGE::open);

        checkText(() -> table().cell(2, 2).waitMatchText("[a-zA-Z, ]*JUnit,[a-zA-Z ]*"), "TestNG, JUnit, Custom");
    }

    @Test
    public void waitHaveRowsTest() {
        isInState(HOME_PAGE);
        runParallel(SUPPORT_PAGE::open);

        isTrue(() -> table().waitHaveRows());
    }

    @Test
    public void waitRowsTest() {
        isInState(HOME_PAGE);
        runParallel(SUPPORT_PAGE::open);

        isTrue(table().waitRows(6));
    }

    @Test
    public void waitRowsMoreNumberOfRowsTest() {

        long tableWaitTomeOut;
        isInState(HOME_PAGE);
        runParallel(SUPPORT_PAGE::open);

        tableWaitTomeOut = timer.timePassedInMSec();
        isFalse(table().waitRows(7));
        tableWaitTomeOut -= timer.timePassedInMSec();

        new Check("Wait Row time out").isTrue(-tableWaitTomeOut < 5200);
    }
}
