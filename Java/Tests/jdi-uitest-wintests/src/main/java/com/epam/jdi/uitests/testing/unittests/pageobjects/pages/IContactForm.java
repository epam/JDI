package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import com.epam.jdi.uitests.testing.unittests.Contact;

import java.util.List;

/**
 * Created by Dmitry_Lebedev1 on 10/16/2015.
 */
public interface IContactForm extends IForm<Contact> {
    List<String> getFormValue();
    void fillForm(Contact contact);
}
