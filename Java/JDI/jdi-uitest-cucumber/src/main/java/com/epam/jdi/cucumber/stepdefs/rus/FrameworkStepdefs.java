package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import cucumber.api.java.en.Given;

import static com.epam.jdi.cucumber.Utils.getElementByName;

/**
 * Created by Dmitry_Lebedev1 on 1/12/2016.
 */
public class FrameworkStepdefs {

    @Given("^I fill \"([^\"]*)\" textfield with \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) {
        TextField textField = getElementByName(fieldName);
        textField.setValue(data);
    }
}
