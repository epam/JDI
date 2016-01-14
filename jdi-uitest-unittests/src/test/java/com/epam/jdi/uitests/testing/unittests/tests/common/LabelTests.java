package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.testng.annotations.Factory;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;

/**
 * Created by Dmitry_Lebedev1 on 15/12/2015.
 */
public class LabelTests extends InitTests {

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new ButtonTests("ILabel", METALS_AND_COLORS_PAGE,
                        () -> metalsColorsPage.calculate,
                        "CALCULATE",
                        "CUL",
                        ".*LCU.*")
        };
    }
}
