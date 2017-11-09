package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.testing.unittests.entities.Contact;
import com.epam.jdi.uitests.testing.unittests.utils.JSLoader;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Image;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitry_Lebedev1 on 10/16/2015.
 */
public class ContactForm extends Form<Contact> implements IContactForm {
    @Image("name_textarea.png")
    @FindBy(id = "Name")
    public TextField name;
    @FindBy(id = "LastName")
    public TextField lastName;
    @FindBy(id = "Description")
    public TextArea description;

    @FindBy(xpath = "//*[text()='Submit']")
    public IButton submit;

    @FindBy(xpath = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all' and position()=1]")
    public Link firstRoller;
    @FindBy(xpath = ".//a[@class='ui-slider-handle ui-state-default ui-corner-all' and position()=2]")
    public Link secondRoller;

    private JavascriptExecutor javaScriptExecutor;

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

    private JavascriptExecutor getJSExecutor() {
        if (javaScriptExecutor == null) {
            javaScriptExecutor = (JavascriptExecutor) WebSettings.getDriverFactory().getDriver();
        }
        return javaScriptExecutor;
    }

    public void setLeftRollerPosition(int position) {
        JSLoader jsLoader = new JSLoader();
        String[][] keyWords = {{"LEFT_POS", String.valueOf(position)}};
        getJSExecutor().executeScript(jsLoader.getJSFromFile("JavaScript/rollerLeft.js", keyWords));
        firstRoller.click();
    }

    public void setRightRollerPosition(int position) {
        JSLoader jsLoader = new JSLoader();
        String[][] keyWords = {{"RIGHT_POS", String.valueOf(position)}};
        getJSExecutor().executeScript(jsLoader.getJSFromFile("JavaScript/rollerRight.js", keyWords));

        secondRoller.click();
    }
}
