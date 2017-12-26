package com.epam.jdi.uitests.web.selenium.driver;

import io.github.bonigarcia.wdm.BrowserManager;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 11/28/2017.
 */
public class DownloadDriverManager {
    public static boolean shouldDownloadDriver() {
        return driverVersion.equalsIgnoreCase("latest") ||
                hasVersion();
    }
    private static boolean hasVersion() {
        char c = driverVersion.charAt(0);
        return (c >= '0' && c <= '9');
    }

    public static String driverVersion = "none";
    public static void downloadDriver(DriverTypes driverType) {
        BrowserManager bm = null;
        try {
            switch (driverType) {
                case CHROME:
                    bm = ChromeDriverManager.getInstance(); break;
                case FIREFOX:
                    bm = FirefoxDriverManager.getInstance().arch32(); break;
                case IE:
                    bm = InternetExplorerDriverManager.getInstance(); break;
            }
            if (bm == null)
                throw exception("Unknown driver: " + driverType);
            if (hasVersion())
                bm = bm.version(driverVersion);
            bm.setup();
        } catch (Exception ex) {
            throw exception("Can't download latest driver for " + driverType
                    + ". Exception " + ex.getMessage());
        }
    }
}
