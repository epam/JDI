package com.epam.jdi.uitests.testing.unittests.pageobjects.forms;

import com.epam.jdi.uitests.core.annotations.Name;
import com.epam.jdi.uitests.testing.unittests.entities.Contact;
import com.epam.jdi.uitests.win.winnium.elements.common.Button;
import com.epam.jdi.uitests.win.winnium.elements.common.TextBox;
import com.epam.jdi.uitests.win.winnium.elements.composite.Form;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public class ContactForm extends Form<Contact> implements IContactForm {
    @FindBy(xpath = "./*[starts-with(@AutomationId, 'name')]")
    @Name(value = "name")
    public TextBox nameTextBox;

    @FindBy(xpath = "./*[starts-with(@AutomationId, 'lastName')]")
    @Name(value = "lastName")
    public TextBox lastNameTextBox;

    @FindBy(xpath = "./*[starts-with(@AutomationId, 'description')]")
    public TextBox description;

    @FindBy(xpath = "./*[starts-with(@AutomationId, 'submit')]")
    public Button submitButton;

    @Override
    public List<String> getFormValue() {
        return Arrays.asList(nameTextBox.getText(), lastNameTextBox.getText(), description.getText());
    }

    @Override
    public void fillForm(Contact contact) {
        nameTextBox.newInput(contact.name);
        lastNameTextBox.newInput(contact.lastName);
        description.newInput(contact.description);
    }
}
