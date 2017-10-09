package com.epam.jdi.uitests.win.winnium.elements.composite;

import com.epam.jdi.uitests.core.interfaces.Application;
import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winnium.elements.WinCascadeInit;

public class DesktopApplication extends Application {
    public static <T> void init(Class<T> applicationClass) {
        new WinCascadeInit().initStaticPages(applicationClass, WinSettings.driverFactory.currentDriverName());
        currentSite = applicationClass;
    }
}
