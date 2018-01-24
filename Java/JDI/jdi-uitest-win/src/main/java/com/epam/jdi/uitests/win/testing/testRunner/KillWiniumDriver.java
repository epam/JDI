package com.epam.jdi.uitests.win.testing.testRunner;

import org.openqa.selenium.os.CommandLine;
import org.openqa.selenium.os.WindowsUtils;

import static com.epam.commons.LinqUtils.first;
import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.TryCatchUtil.tryGetResult;

/**
 * Created by Roman_Iovlev on 1/24/2018.
 */
public class KillWiniumDriver {
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
