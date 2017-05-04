package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import cucumber.api.java.en.Given;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class FrameworkStepdefs {

    @Given("^I fill \"([^\"]*)\" textfield with \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) {
        TextField textField = getElementByName(fieldName);
        textField.setValue(data);
    }
}
