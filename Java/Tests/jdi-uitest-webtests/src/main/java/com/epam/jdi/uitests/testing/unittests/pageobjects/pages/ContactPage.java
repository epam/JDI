package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.ImageFile;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
public class ContactPage extends WebPage {
    @ImageFile("name_textarea.png")
    @FindBy(id = "Name")
    public TextField name;
    @ImageFile("lastname_textarea.png")
    @FindBy(id = "LastName")
    public ITextField lastName;
    @ImageFile("description_textarea.png")
    @FindBy(id = "Description")
    public TextArea description;
    @ImageFile("submit_button.png")
    @FindBy(xpath = "//*[text()='Submit']")
    public IButton contactSubmit;
    @ImageFile("epam_logo.png")
    @FindBy(css = ".epam-logo img")
    public IImage logoImage;
    @ImageFile("result_textarea.png")
    @FindBy(css = ".results")
    public IText result;
    @ImageFile("contactForm/")
    @FindBy(css = "main form")
    public ContactForm contactForm;
    @FindBy(css = "main form")
    public ContactFormTwoButtons contactFormTwoButtons;
}
