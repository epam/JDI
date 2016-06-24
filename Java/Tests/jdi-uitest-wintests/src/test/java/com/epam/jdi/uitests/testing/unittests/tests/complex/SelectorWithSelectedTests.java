package com.epam.jdi.uitests.testing.unittests.tests.complex;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Odds;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication;
import com.epam.jdi.uitests.win.winium.elements.complex.Selector;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Odds.SEVEN;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.*;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.isTrue;
import static com.epam.web.matcher.testng.Assert.listEquals;
import static java.util.Arrays.asList;

/**
 * Created by Roman_Iovlev on 9/15/2015.
 */
public class SelectorWithSelectedTests extends InitTests {
    private static final List<String> oddOptions = asList("1", "3", "5", "7");

    private Selector<Odds> odds() {
        return EpamJDIApplication.metalsColorsPage.summary.oddsWithSelected;
    }

    @BeforeMethod
    public void before(Method method) throws IOException {
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void selectStringTest() {
        odds().select("7");
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void selectIndexTest() {
        odds().select(4);
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void selectEnumTest() {
        odds().select(Odds.SEVEN);
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void getOptionsTest() {
        listEquals(odds().getOptions(), oddOptions);
    }

    @Test
    public void getNamesTest() {
        listEquals(odds().getNames(), oddOptions);
    }

    @Test
    public void getValuesTest() {
        listEquals(odds().getValues(), oddOptions);
    }

    @Test
    public void getOptionsAsTextTest() {
        areEquals(odds().getOptionsAsText(), "1, 3, 5, 7");
    }

    @Test
    public void setValueTest() {
        odds().setValue("7");
        checkAction("Summary (Odd): value changed to 7");
    }

    @Test
    public void getNameTest() {
        areEquals(odds().getName(), "Odds With Selected");
    }

    @Test
    public void getSelectedTest() {
        odds().select(Odds.SEVEN);
        areEquals(odds().getSelected(), "7");
    }

    @Test
    public void getSelectedIndexTest() {
        odds().select(Odds.SEVEN);
        areEquals(odds().getSelectedIndex(), 4);
    }

    @Test
    public void isSelectedTest() {
        odds().select(Odds.SEVEN);
        isTrue(odds().isSelected("7"));
    }

    @Test
    public void isSelectedEnumTest() {
        odds().select(Odds.SEVEN);
        isTrue(odds().isSelected(Odds.SEVEN));
    }

    @Test
    public void waitSelectedTest() {
        odds().select(Odds.SEVEN);
        new Check("WaitSelected")
                .hasNoExceptions(() -> odds().waitSelected("7"));
    }

    @Test
    public void waitSelectedEnumTest() {
        odds().select(Odds.SEVEN);
        new Check("WaitSelected")
                .hasNoExceptions(() -> odds().waitSelected(SEVEN));
    }

    @Test
    public void wait3SelectedTest() {
        runParallel(() -> odds().select(Odds.SEVEN));
        new Check("WaitSelected")
                .hasNoExceptions(() -> odds().waitSelected("7"));
        isTrue(getTimePassed() > waitTimeOut);
        isTrue(odds().isSelected(Odds.SEVEN));
    }

    @Test
    public void wait3SelectedEnumTest() {
        runParallel(() -> odds().select(Odds.SEVEN));
        new Check("WaitSelected")
                .hasNoExceptions(() -> odds().waitSelected(SEVEN));
        isTrue(getTimePassed() > waitTimeOut);
        isTrue(odds().isSelected(Odds.SEVEN));
    }

    @Test
    public void getValueTest() {
        odds().select(Odds.SEVEN);
        areEquals(odds().getValue(), "7");
    }

}
