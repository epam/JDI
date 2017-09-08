package com.epam.jdi.uitests.testing.unittests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.dataProviders.CheckBoxDP;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.metalsandcolors.NestedMetalsAndColorsView;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckboxTests extends InitTests {
    private NestedMetalsAndColorsView nestedMetalsAndColorsView;

    @BeforeSuite
    public void initVarialbe() {
        nestedMetalsAndColorsView = mainWindow.mainTabPane.metalsAndColorsTab.nestedMetalsAndColorsView;
    }

    @BeforeMethod
    public void before(Method method) {
//        PreconditionsState.alwaysMoveToCondition = true;
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void checkSingleTest() throws InterruptedException {
        nestedMetalsAndColorsView.cbWater.check();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to true");
    }

    @Test
    public void uncheckSingleTest() throws InterruptedException {
        nestedMetalsAndColorsView.cbWater.click();
        nestedMetalsAndColorsView.cbWater.uncheck();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to false");
    }

    @Test
    public void isCheckTest() throws Exception {
        assertFalse(nestedMetalsAndColorsView.cbWater.isChecked());
        nestedMetalsAndColorsView.cbWater.click();
        assertTrue(nestedMetalsAndColorsView.cbWater.isChecked());
    }

    @Test
    public void multiUncheckTest() throws Exception {
        nestedMetalsAndColorsView.cbWater.click();
        nestedMetalsAndColorsView.cbWater.uncheck();
        nestedMetalsAndColorsView.cbWater.uncheck();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to false");
    }

    @Test
    public void multiCheckTest() throws Exception {
        nestedMetalsAndColorsView.cbWater.check();
        nestedMetalsAndColorsView.cbWater.check();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to true");
    }

    @Test
    public void clickTest() throws Exception {
        nestedMetalsAndColorsView.cbWater.click();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to true");
        nestedMetalsAndColorsView.cbWater.click();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to false");
    }

    @Test(dataProvider = "inputData", dataProviderClass = CheckBoxDP.class)
    public void setValue(String input, Boolean expected){
        if (!expected)
            nestedMetalsAndColorsView.cbWater.click();
        nestedMetalsAndColorsView.cbWater.setValue(input);
        String resMsg = "Water: condition changed to " + expected.toString();
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], resMsg);
    }

    @Test(dataProvider = "inputDataNothing2Changed", dataProviderClass = CheckBoxDP.class)
    public void setValueNothing2Changed(String input){
        nestedMetalsAndColorsView.cbWater.click();
        nestedMetalsAndColorsView.cbWater.setValue(input);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to true");
        nestedMetalsAndColorsView.cbWater.click();
        nestedMetalsAndColorsView.cbWater.setValue(input);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Water: condition changed to false");
    }
}
