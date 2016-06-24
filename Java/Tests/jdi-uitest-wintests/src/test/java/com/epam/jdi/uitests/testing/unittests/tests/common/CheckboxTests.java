package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.tests.common.dataProviders.CheckBoxDP;
import com.epam.jdi.uitests.win.winium.elements.common.CheckBox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.metalsColorsPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.checkAction;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Created by Dmitry_Lebedev1 on 15/12/2015.
 */
public class CheckboxTests extends InitTests {

    public CheckBox checkBox() {
        return metalsColorsPage.cbWater;
    }

    @BeforeMethod
    public void before(final Method method) throws IOException {
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void checkSingleTest() throws InterruptedException {
        checkBox().check();
        checkAction("Water: condition changed to true");
    }

    @Test
    public void uncheckSingleTest() throws InterruptedException {
        checkBox().click();
        checkBox().uncheck();
        checkAction("Water: condition changed to false");
    }

    @Test
    public void isCheckTest() throws Exception {
        assertFalse(checkBox().isChecked());
        checkBox().click();
        assertTrue(checkBox().isChecked());
    }

    @Test
    public void multiUncheckTest() throws Exception {
        checkBox().click();
        checkBox().uncheck();
        checkBox().uncheck();
        checkAction("Water: condition changed to false");
    }

    @Test
    public void multiCheckTest() throws Exception {
        checkBox().check();
        checkBox().check();
        checkAction("Water: condition changed to true");
    }

    @Test
    public void clickTest() throws Exception {
        checkBox().click();
        checkAction("Water: condition changed to true");
        checkBox().click();
        checkAction("Water: condition changed to false");
    }

    @Test(dataProvider = "inputData", dataProviderClass = CheckBoxDP.class)
    public void setValue(String input, Boolean expected){
        if (!expected)
            checkBox().click();
        checkBox().setValue(input);
        String resMsg = "Water: condition changed to " + expected.toString();
        checkAction(resMsg);
    }

    @Test(dataProvider = "inputDataNothing2Changed", dataProviderClass = CheckBoxDP.class)
    public void setValueNothing2Changed(String input){
        checkBox().click();
        checkBox().setValue(input);
        checkAction("Water: condition changed to true");
        checkBox().click();
        checkBox().setValue(input);
        checkAction("Water: condition changed to false");
    }
}
