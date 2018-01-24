package com.epam.jdi.uitests.testing.unittests.pageobjects;

import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.MainTabPane;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.MainWindow;
import com.epam.jdi.uitests.win.winnium.elements.common.TextBox;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.WinApp;
import org.openqa.selenium.support.FindBy;

@WinApp(application = "DesktopApp/JDITestDesktopApp.exe",
    windowLocator = @FindBy(xpath = "./*[contains(@AutomationId, 'JDITestDesktopApp')]"))
public class Desktop {
    public static MainWindow mainWindow;


    @FindBy(id = "tabControl")
    public static MainTabPane mainTabPane;

    @FindBy(id = "logTextBox")
    public static TextBox logTextBox;

    @FindBy(id = "resultTextBox")
    public static TextBox passwordTextBox;
}
