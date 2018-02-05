package com.epam.jdi.uitests.win.winnium.driver;

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.core.settings.JDISettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.File;
import java.net.URL;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.win.winnium.elements.composite.DesktopApplication.appPath;

public class WiniumDriverFactory implements IDriver<WebDriver> {
    private static final String WINIUM_DEFAULT_HOST = "http://localhost:9999";
    private Supplier<WebDriver> winiumDesktopDriverSupplier;
    private Process startedProcess;
    private String driversPath;
    private WebDriver winiumDriver;
    public static final String DEFAULT_PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\";
    public static final String WINIUM = "winiumdesktop";
    private String currentDriverName = WINIUM;

    public WiniumDriverFactory() {
        driversPath = DEFAULT_PATH;
    }

    public Process getStartedProcess() {
        return startedProcess;
    }

    public String registerDriver(String driverName) {
        if (!WINIUM.equals(driverName.toLowerCase()))
            throw JDISettings.exception("Unknown driver: " + driverName);

        winiumDesktopDriverSupplier = () -> {
            try {
                startedProcess = new ProcessBuilder(driversPath + "Winium.Desktop.Driver.exe").start();
                DesktopOptions options = new DesktopOptions();
                options.setApplicationPath(appPath);
                return new WiniumDriver(new URL(WINIUM_DEFAULT_HOST), options);
            } catch (Exception e) {
                throw JDISettings.exception("Unknown driver: " + driverName);
            }
        };

        return driverName;
    }

    public void setDriverPath(String driverPath) {
        this.driversPath = driverPath;
    }

    public void setRunType(String runType) {}

    public void setRemoteHubUrl(String url) { }

    public WebDriver getDriver() {
        return getDriver(currentDriverName);
    }

    public boolean hasDrivers() {
        return winiumDesktopDriverSupplier != null;
    }

    public boolean hasRunDrivers() {
        return winiumDriver != null;
    }

    public String currentDriverName() {
        return currentDriverName;
    }

    public void setCurrentDriver(String driverName) {
        currentDriverName = driverName;
    }

    public WebDriver getDriver(String name) {
        if (winiumDesktopDriverSupplier == null)
            throw new RuntimeException();

        if (winiumDriver == null)
            winiumDriver = winiumDesktopDriverSupplier.get();

        return winiumDriver;
    }

    public void highlight(IElement element) {
        throw new UnsupportedOperationException("Not supported");
    }

    public void highlight(IElement element, HighlightSettings highlightSettings) {
        throw new UnsupportedOperationException("Not supported");
    }

    public String getDriverPath() {
        return driversPath;
    }
}
