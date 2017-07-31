package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.ColorsList;
import com.epam.jdi.uitests.testing.unittests.enums.Even;
import com.epam.jdi.uitests.testing.unittests.enums.Nature;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.*;
import static com.epam.web.matcher.testng.Assert.assertContains;

/**
 * Created by Roman_Iovlev on 11/14/2016.
 */
public class _SimpleTests extends InitTests {

    @BeforeMethod
    public void before(final Method method) {
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void clickTest() {
        metalsColorsPage.summary.odds.select("5");
        metalsColorsPage.summary.even.select(Even.FOUR);
        metalsColorsPage.calculateButton.click();
        assertContains(() -> resultsLog.getFirstText(), "Summary: 9");
        metalsColorsPage.colors.select(ColorsList.Blue);
        metalsColorsPage.nature.select(Nature.FIRE, Nature.EARTH);
        assertContains(actionsLog::getFirstText, "Earth: condition changed to true");
    }

}
