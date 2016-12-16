package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.contactform;

import com.epam.jdi.uitests.testing.unittests.pageobjects.forms.ContactForm;
import com.epam.jdi.uitests.testing.unittests.pageobjects.forms.ContactFormTwoButtons;
import com.epam.jdi.uitests.win.winnium.elements.common.TextBox;
import com.epam.jdi.uitests.win.winnium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

public class NestedContactFormView extends Section {
    @FindBy(id = "nameCFTextBox")
    public TextBox nameTextBox;

    @FindBy(id = "lastNameCFTextBox")
    public TextBox lastNameTextBox;

    @FindBy(id = "descriptionCFTextBox")
    public TextBox descriptionTextBox;

    @FindBy(xpath = ".") // Not necessary, made just for example
    public ContactForm contactForm;

    public ContactFormTwoButtons contactFormTwoButtons;
}
