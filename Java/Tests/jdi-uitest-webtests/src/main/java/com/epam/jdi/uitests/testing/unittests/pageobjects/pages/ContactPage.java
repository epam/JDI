package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Image;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
public class ContactPage extends WebPage {
    @Image("name_textarea.png")
    @FindBy(id = "Name")
    public TextField name;
    @Image("lastname_textarea.png")
    @FindBy(id = "LastName")
    public ITextField lastName;
    @Image("description_textarea.png")
    @FindBy(id = "Description")
    public TextArea description;
    @Image("submit_button.png")
    @FindBy(xpath = "//*[text()='Submit']")
    public IButton contactSubmit;
    @Image("epam_logo.png")
    @FindBy(css = ".epam-logo img")
    public IImage logoImage;
    @Image("result_textarea.png")
    @FindBy(css = ".results")
    public IText result;
    @Image("contactForm/")
    @FindBy(css = "main form")
    public ContactForm contactForm;
    @FindBy(css = "main form")
    public ContactFormTwoButtons contactFormTwoButtons;
}
