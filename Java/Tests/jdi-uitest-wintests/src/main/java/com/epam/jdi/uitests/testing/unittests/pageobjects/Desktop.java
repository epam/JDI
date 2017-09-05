package com.epam.jdi.uitests.testing.unittests.pageobjects;

import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.MainWindow;
import org.openqa.selenium.support.FindBy;

public class Desktop {
    @FindBy(xpath = "./*[contains(@AutomationId, 'JDITestDesktopApp')]")
    public static MainWindow mainWindow;
}
