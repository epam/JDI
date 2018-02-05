package com.epam.jdi.uitests.win.winnium.elements.composite;

import com.epam.jdi.uitests.core.interfaces.Application;
import com.epam.jdi.uitests.win.winnium.elements.WinCascadeInit;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.WinApp;
import org.openqa.selenium.By;

import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.win.winnium.driver.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.win.winnium.driver.WiniumDriverFactory.DEFAULT_PATH;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class DesktopApplication extends Application {

    public static String appPath;
    public static void setAppPath(String path) {
        appPath = "";
        if (!path.contains(":"))
            appPath = DEFAULT_PATH;
        appPath += path;
    }
    public static By windowId;

    public static <T> void init(Class<T> applicationClass) {
        if (applicationClass.isAnnotationPresent(WinApp.class)) {
            String value = applicationClass.getAnnotation(WinApp.class).value();
            if (isBlank(value))
                value = applicationClass.getAnnotation(WinApp.class).application();
            if (isNotBlank(value))
                setAppPath(value);
            By windowLocator = findByToBy(applicationClass.getAnnotation(WinApp.class).windowLocator());
            if (windowLocator != null)
                windowId = windowLocator;
        }
        new WinCascadeInit().initStaticPages(applicationClass, driverFactory.currentDriverName());
        currentSite = applicationClass;
    }
}
