package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.*;

public class JDropListTests extends InitTests {
    @BeforeMethod
    public void pageLoad() {
        metalsColorsPage.open();
    }

    @Test
    public void dropListTest() {

        System.out.print(metalsColorsPage.colorsDL.getOptions());
    }

}
