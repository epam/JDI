package com.epam.jdi.uitests.web.selenium.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Roman_Iovlev on 11/28/2017.
 */
public class DownloadDriverManager {
    public static void downloadDriver(DriverTypes driverType) {
        try {
            switch (driverType) {
                case CHROME:
                    ChromeDriverManager.getInstance().setup(); break;
                case FIREFOX:
                    FirefoxDriverManager.getInstance().arch32().setup(); break;
                case IE:
                    InternetExplorerDriverManager.getInstance().setup(); break;
                default:
                    throw exception("Unknown driver: " + driverType);
            }
        } catch (Exception ex) {
            throw exception("Can't download latest driver for " + driverType
                    + ". Exception " + ex.getMessage());
        }
    }
}
