package com.epam.jdi.uitests.win.winnium.driver;

import com.epam.commons.PropertyReader;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.core.settings.JDISettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.IOException;
import java.net.URL;
import java.util.function.Supplier;

public class WiniumDriverFactory implements IDriver<WebDriver> {
    private static final String WINNIUM_DEFAULT_HOST = "http://localhost:9999";
    private Supplier<WebDriver> winniumDesktopDriverSupplier;
    private Process startedProcess;
    private String driversPath = "";
    private WebDriver winiumDriver;
    private String currentDriverName = "winniumdesctop";


    public Process getStartedProcess() {
        return startedProcess;
    }

    @Override
    public String registerDriver(String driverName) {
        if (!"winiumdesktop".equals(driverName.toLowerCase()))
            throw JDISettings.exception("Unknown driver: " + driverName);

        winniumDesktopDriverSupplier = () -> {
            try {
                startedProcess = new ProcessBuilder(driversPath + "\\Winium.Desktop.Driver.exe").start();

//                DesiredCapabilities cap = new DesiredCapabilities();
//                cap.setCapability("app", JDISettings.domain);
//                cap.setCapability("launchDelay","5");
//                return new RemoteWebDriver(new URL("http://localhost:9999"), cap);


                DesktopOptions options = new DesktopOptions();

                options.setApplicationPath(PropertyReader.getProperty("domain"));

                return new WiniumDriver(new URL(WINNIUM_DEFAULT_HOST), options);
            } catch (IOException e) {
                throw JDISettings.exception("Unknown driver: " + driverName);
            }
        };

        return driverName;
    }

    @Override
    public void setDriverPath(String driverPath) {
        this.driversPath = driverPath;
    }

    @Override
    public void setRunType(String runType) {}

    @Override
    public WebDriver getDriver() {
        return getDriver(currentDriverName);
    }

    @Override
    public boolean hasDrivers() {
        return winniumDesktopDriverSupplier != null;
    }

    @Override
    public boolean hasRunDrivers() {
        return winiumDriver != null;
    }

    @Override
    public String currentDriverName() {
        return currentDriverName;
    }

    @Override
    public void setCurrentDriver(String driverName) {
        currentDriverName = driverName;
    }

    @Override
    public WebDriver getDriver(String name) {
        if (winniumDesktopDriverSupplier == null)
            throw new RuntimeException();

        if (winiumDriver == null)
            winiumDriver = winniumDesktopDriverSupplier.get();

        return winiumDriver;
    }

    @Override
    public void highlight(IElement element) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void highlight(IElement element, HighlightSettings highlightSettings) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public String getDriverPath() {
        return driversPath;
    }
}
