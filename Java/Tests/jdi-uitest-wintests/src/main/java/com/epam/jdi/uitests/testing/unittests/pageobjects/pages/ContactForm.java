package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.testing.unittests.Contact;
import com.epam.jdi.uitests.win.winium.elements.common.TextArea;
import com.epam.jdi.uitests.win.winium.elements.common.TextField;

import com.epam.jdi.uitests.win.winium.elements.composite.Form;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitry_Lebedev1 on 10/16/2015.
 */
public class ContactForm extends Form<Contact> implements IContactForm {
    @FindBy(id = "nameCFTextBox")
    public TextField name;
    @FindBy(id = "lastNameCFTextBox")
    public TextField lastName;
    @FindBy(id = "descriptionCFTextBox")
    public TextArea description;

    @FindBy(id= "submitCFButton")
    public IButton submit;

    public List<String> getFormValue() {
        return Arrays.asList(
                name.getText(),
                lastName.getText(),
                description.getText());
    }

    public void fillForm(Contact contact) {
        name.newInput(contact.name);
        lastName.newInput(contact.lastName);
        description.newInput(contact.description);
    }
}
