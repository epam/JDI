package com.epam.jdi.uitests.testing.unittests.tests.complex;

import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Metals;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Metals.Metals;
import static com.epam.jdi.uitests.testing.unittests.enums.Metals.Gold;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.*;
import static com.epam.web.matcher.testng.Assert.*;
import static java.util.Arrays.asList;

/**
 * Created by Roman_Iovlev on 9/15/2015.
 */
public class ComboBoxTests extends InitTests {
    private static final List<String> oddOptions = asList("Metals", "Gold", "Silver", "Bronze", "Selen");

    private IComboBox<Metals> metals() {
        return metalsColorsPage.comboBox;
    }

    @BeforeMethod
    public void before(Method method) {
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void selectStringTest() {
        metals().select("Gold");
        checkAction("Metals: value changed to Gold");
    }

    @Test
    public void selectIndexTest() {
        metals().select(3);
        checkAction("Metals: value changed to Silver");
    }

    @Test
    public void selectEnumTest() {
        metals().select(Gold);
        checkAction("Metals: value changed to Gold");
    }

    @Test
    public void getOptionsTest() {
        listEquals(metals().getOptions(), oddOptions);
    }

    @Test
    public void getNamesTest() {
        listEquals(metals().getNames(), oddOptions);
    }

    @Test
    public void getValuesTest() {
        listEquals(metals().getValues(), oddOptions);
    }

    @Test
    public void getOptionsAsTextTest() {
        areEquals(metals().getOptionsAsText(), "Metals, Gold, Silver, Bronze, Selen");
    }

    @Test
    public void setValueTest() {
        metals().setValue("Blue");
        looseFocus();
        checkAction("Metals: value changed to Blue");
    }

    @Test
    public void getSelectedTest() {
        metals().select("Gold");
        areEquals(metals().getSelected(), "Gold");
    }

    @Test
    public void getSelectedIndexTest() {
        checkActionThrowError(() -> metals().getSelectedIndex(), noElementsMessage); // isDisplayed not defined
    }

    @Test
    public void isSelectedTest() {
        areEquals(metals().isSelected("Metals"), true);
    }

    @Test
    public void isSelectedEnumTest() {
        areEquals(metals().isSelected(Metals), true);
    }

    @Test
    public void waitSelectedTest() {
        try {
            metals().waitSelected("Metal");
        } catch (Exception ex) {
            throw exception("WaitSelected throws exception");
        }
    }

    @Test
    public void waitSelectedEnumTest() {
        new Check("WaitSelected")
            .hasNoExceptions(() -> metals().waitSelected(Metals));
    }

    @Test
    public void getValueTest() {
        areEquals(metals().getValue(), "Metals");
    }
}
