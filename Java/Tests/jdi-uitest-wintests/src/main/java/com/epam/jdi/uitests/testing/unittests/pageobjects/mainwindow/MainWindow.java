package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow;

import com.epam.jdi.uitests.win.winnium.elements.common.TextBox;
import com.epam.jdi.uitests.win.winnium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

public class MainWindow extends Section {
    @FindBy(id = "tabControl")
    public MainTabPane mainTabPane;

    @FindBy(id = "logTextBox")
    public TextBox logTextBox;

    @FindBy(id = "resultTextBox")
    public TextBox passwordTextBox;
}
