package com.epam.jdi.uitests.win.winnium.elements.composite;

import com.epam.jdi.uitests.core.interfaces.Application;
import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winnium.elements.WinCascadeInit;

import java.io.File;

import static com.epam.jdi.uitests.win.winnium.driver.WiniumDriverFactory.DEFAULT_PATH;

public class DesktopApplication extends Application {

    public static String appPath;
    public static void setAppPath(String path) {
        appPath = "";
        if (!path.contains(":/"))
            appPath = DEFAULT_PATH;
        appPath += path;
    }

    public static <T> void init(Class<T> applicationClass) {
        new WinCascadeInit().initStaticPages(applicationClass, WinSettings.driverFactory.currentDriverName());
        currentSite = applicationClass;
    }
}
