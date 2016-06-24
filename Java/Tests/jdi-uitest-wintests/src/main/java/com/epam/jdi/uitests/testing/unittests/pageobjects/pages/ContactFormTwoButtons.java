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
public class ContactFormTwoButtons extends Form<Contact> implements IContactForm {

    @FindBy(id = "Name")
    public TextField name;

    @FindBy(id = "LastName")
    public TextField lastName;

    @FindBy(id = "Description")
    public TextArea description;

    @FindBy(xpath = "//*[text()='Submit']")
    public IButton submit;

    @FindBy(xpath = "//*[text()='Calculate']")
    public IButton calculate;

    public List<String> getFormValue() {
        return Arrays.asList(
                name.getWebElement().getAttribute("value"),
                lastName.getWebElement().getAttribute("value"),
                description.getWebElement().getAttribute("value"));
    }

    public void fillForm(Contact contact) {
        name.getWebElement().clear();
        lastName.getWebElement().clear();
        description.getWebElement().clear();
        name.getWebElement().sendKeys(contact.name);
        lastName.getWebElement().sendKeys(contact.lastName);
        description.getWebElement().sendKeys(contact.description);
    }
}
