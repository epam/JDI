package com.epam.jdi.uitests.testing.unittests.tests.complex;

import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Colors;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Colors.Blue;
import static com.epam.jdi.uitests.testing.unittests.enums.Colors.Colors;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.metalsColorsPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.*;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.listEquals;
import static java.util.Arrays.asList;

/**
 * Created by Roman_Iovlev on 9/15/2015.
 */
public class DropdownExpandedTests extends InitTests {
    private static final List<String> oddOptions = asList("Colors", "Red", "Green", "Blue", "Yellow");

    private IDropDown<Colors> colors() {
        return metalsColorsPage.colors;
    }

    @BeforeMethod
    public void before(Method method) throws IOException {
        isInState(METALS_AND_COLORS_PAGE, method);
        colors().expand();
    }

    @Test
    public void selectStringTest() {
        colors().select("Blue");
        checkAction("Colors: value changed to Blue");
    }

    @Test
    public void selectIndexTest() {
        colors().select(4);
        checkAction("Colors: value changed to Blue");
    }

    @Test
    public void selectEnumTest() {
        colors().select(Blue);
        checkAction("Colors: value changed to Blue");
    }

    @Test
    public void getOptionsTest() {
        listEquals(colors().getOptions(), oddOptions);
    }

    @Test
    public void getNamesTest() {
        listEquals(colors().getNames(), oddOptions);
    }

    @Test
    public void getValuesTest() {
        listEquals(colors().getValues(), oddOptions);
    }

    @Test
    public void getOptionsAsTextTest() {
        areEquals(colors().getOptionsAsText(), "Colors, Red, Green, Blue, Yellow");
    }

    @Test
    public void setValueTest() {
        colors().setValue("Blue");
        checkAction("Colors: value changed to Blue");
    }

    @Test
    public void getNameTest() {
        areEquals(colors().getName(), "Colors");
    }

    // Fails
    @Test
    public void getSelectedTest() {
        areEquals(colors().getSelected(), "Colors");
    }

    @Test
    public void getSelectedIndexTest() {
        checkActionThrowError(() -> colors().getSelectedIndex(), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void isSelectedTest() {
        areEquals(colors().isSelected("Colors"), true);
    }

    @Test
    public void isSelectedEnumTest() {
        areEquals(colors().isSelected(Colors), true);
    }

    @Test
    public void waitSelectedTest() {
        new Check("WaitSelected")
                .hasNoExceptions(() -> colors().waitSelected("Colors"));
    }

    @Test
    public void waitSelectedEnumTest() {
        new Check("WaitSelected")
                .hasNoExceptions(() -> colors().waitSelected(Colors));
    }

    @Test
    public void getValueTest() {
        areEquals(colors().getValue(), "Colors");
    }
}
