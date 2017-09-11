package com.epam.jdi.uitests.testing.unittests.common;

import com.epam.jdi.uitests.core.preconditions.PreconditionsState;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.metalsandcolors.NestedMetalsAndColorsView;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;

public class ButtonTests extends InitTests {
    private NestedMetalsAndColorsView nestedMetalsAndColorsView;

    @BeforeSuite
    public void initVarialbe() {
        nestedMetalsAndColorsView = mainWindow.mainTabPane.metalsAndColorsTab.nestedMetalsAndColorsView;
    }

    @BeforeMethod
    public void before(final Method method) {
        PreconditionsState.isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void Test() {
        nestedMetalsAndColorsView.calculateButton.click();
        Assert.assertContains(Arrays.toString(mainWindow.passwordTextBox.getLines()), "Summary: 3");
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextTests("Button", METALS_AND_COLORS_PAGE, () -> nestedMetalsAndColorsView.calculateButton,
                        "Calculate", "cul", ".*lcu.*")
        };
    }
}
