package com.epam.jdi.uitests.testing.unittests.pageobjects.forms;

import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.testing.unittests.entities.Contact;

import java.util.List;

public interface IContactForm extends IForm<Contact> {
    List<String> getFormValue();
    void fillForm(Contact contact);
}
