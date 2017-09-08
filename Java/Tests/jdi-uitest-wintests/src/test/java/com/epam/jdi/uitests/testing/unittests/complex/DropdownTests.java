package com.epam.jdi.uitests.testing.unittests.complex;

import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Colors;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;

public class DropdownTests extends InitTests {
    private Supplier<IDropDown<Colors>> dropDownSupplier =
            () -> mainWindow.mainTabPane.metalsAndColorsTab.nestedMetalsAndColorsView.colors;

    @BeforeMethod
    public void before(Method method) throws IOException {
        isInState(METALS_AND_COLORS_PAGE);
    }

    @Test
    public void selectStringTest() {
        dropDownSupplier.get().select(4);
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Colors: value changed to Blue");
    }

    @Test
    public void selectStringByNameTest() {
        dropDownSupplier.get().select("Blue");
        Assert.assertContains(mainWindow.logTextBox.getLines()[0], "Colors: value changed to Blue");
    }
}
