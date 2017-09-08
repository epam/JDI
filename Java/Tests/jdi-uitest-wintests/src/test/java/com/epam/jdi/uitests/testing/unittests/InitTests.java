package com.epam.jdi.uitests.testing.unittests;

import com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop;
import com.epam.jdi.uitests.win.testing.testRunner.TestNGBase;
import com.epam.jdi.uitests.win.winnium.elements.composite.DesktopApplication;
import org.testng.annotations.BeforeSuite;

public class InitTests extends TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        DesktopApplication.init(Desktop.class);
    }
}
