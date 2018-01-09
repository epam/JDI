package com.epam.jdi.uitests.win.winnium.driver;

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.settings.IDriver;
import com.epam.jdi.uitests.core.settings.HighlightSettings;
import com.epam.jdi.uitests.core.settings.JDISettings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.function.Supplier;

import static com.epam.commons.PropertyReader.*;

public class WiniumDriverFactory implements IDriver<WebDriver> {
    private static final String WINNIUM_DEFAULT_HOST = "http://localhost:9999";
    private Supplier<WebDriver> winniumDesktopDriverSupplier;
    private Process startedProcess;
    private static final String DEFAULT_PATH = new File("").getAbsolutePath() + "\\src\\main\\resources\\";
    private String driverPath = DEFAULT_PATH;
    private String appPath = DEFAULT_PATH;
    private WebDriver winiumDriver;
    private static final String WINNIUM = "winiumdesktop";
    private String currentDriverName = WINNIUM;

    public Process getStartedProcess() {
        return startedProcess;
    }

    @Override
    public String registerDriver(String driverName) {
        if (!WINNIUM.equals(driverName.toLowerCase()))
            throw JDISettings.exception("Unknown driver: " + driverName);

        winniumDesktopDriverSupplier = () -> {
            try {
                startedProcess = new ProcessBuilder(getDriverPath()).start();
                DesktopOptions options = new DesktopOptions();
                options.setApplicationPath(getAppPath());
                return new WiniumDriver(new URL(WINNIUM_DEFAULT_HOST), options);
            } catch (IOException e) {
                throw JDISettings.exception("Unknown driver: " + driverName);
            }
        };

        return driverName;
    }

    public void setDriverPath(String path) {
        this.driverPath = path;
    }

    public void setAppPath(String path) {
        this.appPath = path;
    }

    public void setRunType(String runType) {}

    public WebDriver getDriver() {
        return getDriver(currentDriverName);
    }

    public boolean hasDrivers() {
        return winniumDesktopDriverSupplier != null;
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
        if (winniumDesktopDriverSupplier == null)
            throw new RuntimeException();

        if (winiumDriver == null)
            winiumDriver = winniumDesktopDriverSupplier.get();

        return winiumDriver;
    }

    public void highlight(IElement element) {
        throw new UnsupportedOperationException("Not supported");
    }

    public void highlight(IElement element, HighlightSettings highlightSettings) {
        throw new UnsupportedOperationException("Not supported");
        
    }

    public String getDriverPath() {
        return concatPaths(driverPath.contains(":")
                ? driverPath
                : concatPaths(DEFAULT_PATH, driverPath)
            , "Winium.Desktop.Driver.exe");
    }
    public String getAppPath() {
        return appPath.contains(":")
            ? appPath
            : concatPaths(DEFAULT_PATH, appPath);
    }
    private String concatPaths(String s1, String s2) {
        return s1.replaceAll("\\\\$", "") + "\\" + s2.replaceAll("^\\\\", "");
    }
}
