package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
public class ContactPage extends WebPage {
    @FindBy(id = "Name")
    public TextField name;
    @FindBy(id = "LastName")
    public ITextField lastName;
    @FindBy(id = "Description")
    public TextArea description;
    @FindBy(xpath = "//*[text()='Submit']")
    public IButton contactSubmit;
    @FindBy(css = ".epam-logo img")
    public IImage logoImage;
    @FindBy(css = ".results")
    public IText result;
    @FindBy(css = "main form")
    public ContactForm contactForm;
    @FindBy(css = "main form")
    public ContactFormTwoButtons contactFormTwoButtons;
}
