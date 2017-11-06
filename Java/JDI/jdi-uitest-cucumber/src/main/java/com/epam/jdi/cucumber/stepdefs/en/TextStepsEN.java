package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class TextStepsEN {

    @And("^I fill field \"([^\"]*)\" by text \"([^\"]*)\"$")
    public void iMFillFieldByText(String fieldName, String text) {
        ITextField textField = Utils.getElementByName(fieldName);
        textField.sendKeys(text);
    }

    @Then("^(?:text|label|link|button) \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void textFromContains(String fieldName, String contains) {
        IText text = Utils.getElementByName(fieldName);
        text.waitText(contains);
    }

    @Then("^(?:text|label|link|button) \"([^\"]*)\" match \"([^\"]*)\"$")
    public void textFromMach(String fieldName, String regex) {
        IText text = Utils.getElementByName(fieldName);
        text.waitMatchText(regex);
    }

    @Then("^(?:text|label|link|button) \"([^\"]*)\" from \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void textFromContains(String linkName, String containerName, String contains) {
        IText text = Utils.getElementByName(containerName, linkName);
        text.waitText(contains);
    }

    @Then("^(?:text|label|link|button) \"([^\"]*)\" from \"([^\"]*)\" match \"([^\"]*)\"$")
    public void textFromMuch(String linkName, String containerName, String regex) {
        IText text = Utils.getElementByName(containerName, linkName);
        text.waitMatchText(regex);
    }
}
