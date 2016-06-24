package com.epam.jdi.uitests.testing.notepadtests.pageobjects;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Iuliia_Petrova on 6/7/2016.
 */
public class SaveAsWindow extends Section {

    @FindBy(className = "Edit")
    public ITextField fileName;

    @FindBy(name = "Save")
    public IButton saveBtn;
}
