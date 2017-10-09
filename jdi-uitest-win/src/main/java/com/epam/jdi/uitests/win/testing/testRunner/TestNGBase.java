package com.epam.jdi.uitests.win.testing.testRunner;

import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winnium.driver.WiniumDriverFactory;
import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.os.WindowsUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.TryCatchUtil.tryGetResult;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;

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

            winiumDriverFactory.getDriver("winniumdesctop").close(); //Can be useful only for *.exe applications
            Process process = winiumDriverFactory.getStartedProcess();
            if (process != null)
                process.destroy();

//            killAllRunDrivers();
        }
    }

    public static void killAllRunDrivers() {
        try {
            String pid = getPid();
            while (pid != null) {
                killPID(pid);
                pid = getPid();
            }
        } catch (Exception ignore) {
            // Ignore in case of not windows Operation System or any other errors
        }
    }

    private static String getPid() {
        return first(where(tryGetResult(WindowsUtils::procMap), el -> el.getKey() != null
                && (el.getKey().contains("Winium.Desktop.Driver")
                || el.getKey().contains("WindowsPhoneDriver.OuterDriver")
                || el.getKey().contains("Winium.StoreApps.Driver"))));
    }

    private static void killPID(String processID) {
        new CommandLine("taskkill", "/f", "/t", "/pid", processID).execute();
    }
}
