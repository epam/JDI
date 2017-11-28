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
    private SeleniumDriverFactory driverFactory;
    public DownloadDriverManager(SeleniumDriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }
    public WebDriver downloadDriver(DriverTypes driverType) {
        try {
            switch (driverType) {
                case CHROME:
                    return initChrome();
                case FIREFOX:
                    return initFirefox();
                case IE:
                    return initIE();
                default:
                    throw exception("Unknown driver: " + driverType);
            }
        } catch (Exception ex) {
            throw exception("Can't download latest driver for " + driverType
                    + ". Exception " + ex.getMessage());
        }
    }
    private WebDriver initChrome() {
        ChromeDriverManager.getInstance().setup();
        return new ChromeDriver(driverFactory.defaultChromeOptions());
    }

    private WebDriver initIE() {
        InternetExplorerDriverManager.getInstance().setup();
        return new InternetExplorerDriver(driverFactory.defaultIEOptions());
    }

    private WebDriver initFirefox() {
        FirefoxDriverManager.getInstance().arch32().setup();
        return new FirefoxDriver(driverFactory.defaultFirefoxOptions());
    }
}
