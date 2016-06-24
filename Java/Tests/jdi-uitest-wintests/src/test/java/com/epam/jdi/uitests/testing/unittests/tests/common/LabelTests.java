package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.metalsColorsPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.checkCalculate;

/**
 * Created by Dmitry_Lebedev1 on 15/12/2015.
 */
public class LabelTests extends InitTests {

    private Supplier<ILabel> label = () -> metalsColorsPage.calculate;

    @BeforeMethod
    public void before(final Method method) {
        isInState(METALS_AND_COLORS_PAGE, method);
    }

    @Test
    public void clickTest() {
        label.get().click();
        checkCalculate("Summary: 3");
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextTests("Label", METALS_AND_COLORS_PAGE, label::get, "CALCULATE", "CUL", ".*LCU.*")
        };
    }
}
