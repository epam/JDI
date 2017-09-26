package com.epam.jdi.uitests.testing.unittests.tests.annotations;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.testng.annotations.Test;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.menu;

public class JMenuTests extends InitTests {
    @Test
    public void jMenuTest() {
        menu.select("Home");
    }
}
