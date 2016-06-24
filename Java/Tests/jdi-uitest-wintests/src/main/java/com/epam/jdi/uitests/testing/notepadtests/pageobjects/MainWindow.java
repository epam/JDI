package com.epam.jdi.uitests.testing.notepadtests.pageobjects;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Iuliia_Petrova on 6/6/2016.
 */
public class MainWindow extends Section {
    @FindBy(className = "Edit")
    public ITextArea editText;

    @FindBy(name = "File")
    public IButton fileBtn;

    @FindBy(name = "Save As...")
    public IButton saveAsBtn;
}
