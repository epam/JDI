package com.epam.jdi.uitests.win.testing.testRunner;

import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winnium.driver.WiniumDriverFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.win.winnium.driver.WiniumDriverFactory.WINIUM;
import static org.openqa.selenium.os.WindowsUtils.killByName;

public class TestNGBase {
    @BeforeSuite(alwaysRun = true)
    public static void jdiSetUp() throws Exception  {
        WinSettings.initFromProperties();
        logger.info("Init test run");
    }

    @AfterSuite
    public static void jdiTearDown() {
        if (WinSettings.driverFactory instanceof WiniumDriverFactory) {
            WiniumDriverFactory winiumDriverFactory = (WiniumDriverFactory) WinSettings.driverFactory;

            winiumDriverFactory.getDriver(WINIUM).close(); //Can be useful only for *.exe applications
            Process process = winiumDriverFactory.getStartedProcess();
            if (process != null)
                process.destroy();
//            killAllRunDrivers();
        }
    }

    public static void killAllRunDrivers() {
        asserter.ignore(() -> killByName("Winium.Desktop.Driver.exe"));
    }
}
