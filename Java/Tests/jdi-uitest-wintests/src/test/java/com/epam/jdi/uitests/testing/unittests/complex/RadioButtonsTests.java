package com.epam.jdi.uitests.testing.unittests.complex;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.win.winnium.elements.complex.RadioButtons;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;

public class RadioButtonsTests extends InitTests {
    private Supplier<RadioButtons> oddsRBSupplier =
            () -> mainWindow.mainTabPane.metalsAndColorsTab.nestedMetalsAndColorsView.oddsR;

    @BeforeMethod
    public void before(Method method) throws IOException {
        isInState(METALS_AND_COLORS_PAGE);
    }

    @Test
    public void selectStringTest() {
        oddsRBSupplier.get().select("7");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Summary (Odd): value changed to 7");
    }

    @Test
    public void getSelectedTest() {
        oddsRBSupplier.get().select("7");
        Assert.assertEquals(oddsRBSupplier.get().getSelected(), "7");
    }

    @Test
    public void getSelectedIndexTest() {
        oddsRBSupplier.get().select("7");
        Assert.assertEquals(oddsRBSupplier.get().getSelectedIndex(), "4");
    }
}
