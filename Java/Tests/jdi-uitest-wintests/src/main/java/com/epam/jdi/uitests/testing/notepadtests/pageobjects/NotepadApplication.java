package com.epam.jdi.uitests.testing.notepadtests.pageobjects;

import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Iuliia_Petrova on 6/6/2016.
 */
public class NotepadApplication extends WebPage {

    @FindBy(className = "Notepad")
    public static MainWindow mainWindow;

    @FindBy(className = "#32770")
    public static SaveAsWindow saveAsWindow;
}
