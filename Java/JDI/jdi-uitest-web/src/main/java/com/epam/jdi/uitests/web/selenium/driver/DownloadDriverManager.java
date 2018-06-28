package com.epam.jdi.uitests.web.selenium.driver;

import io.github.bonigarcia.wdm.WebDriverManager;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 11/28/2017.
 */
public class DownloadDriverManager {
    public static String driverVersion = "none";
    public static String platform = "32";
    public static boolean shouldDownloadDriver() {
        return driverVersion.equalsIgnoreCase("latest") ||
                hasVersion();
    }
    private static boolean hasVersion() {
        char c = driverVersion.charAt(0);
        return (c >= '0' && c <= '9');
    }

    public static void downloadDriver(DriverTypes driverType) {
        WebDriverManager wdm = null;
        try {
            switch (driverType) {
                case CHROME:
                    wdm = WebDriverManager.chromedriver(); break;
                case FIREFOX:
                    wdm = WebDriverManager.firefoxdriver(); break;
                case IE:
                    wdm = WebDriverManager.iedriver(); break;
                case EDGE:
                    wdm = WebDriverManager.edgedriver(); break;
                case PHANTOMJS:
                    wdm = WebDriverManager.phantomjs(); break;
                case OPERA:
                    wdm = WebDriverManager.operadriver(); break;
            }
            if (wdm == null)
                throw exception("Unknown driver: " + driverType);
            switch (platform) {
                case "32": wdm = wdm.arch32(); break;
                case "64": wdm = wdm.arch64(); break;
            }
            if (hasVersion())
                wdm = wdm.version(driverVersion);
            wdm.setup();
        } catch (Exception ex) {
            throw exception("Can't download latest driver for " + driverType
                    + ". Exception " + ex.getMessage());
        }
    }
}
