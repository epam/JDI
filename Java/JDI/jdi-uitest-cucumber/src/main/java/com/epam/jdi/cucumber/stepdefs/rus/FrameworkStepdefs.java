package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import cucumber.api.java.en.Given;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class FrameworkStepdefs {

    @Given("^я заполняю \"([^\"]*)\" значением \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) {
        TextField textField = getElementByName(fieldName);
        textField.setValue(data);
    }
}
