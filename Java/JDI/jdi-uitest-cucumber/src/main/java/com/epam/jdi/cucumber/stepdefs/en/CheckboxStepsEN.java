package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class CheckboxStepsEN {

    @When("^I check \"([^\"]*)\"$")
    public void iMCheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        checkbox.check();
    }

    @When("^I uncheck \"([^\"]*)\"$")
    public void iMUncheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        checkbox.uncheck();
    }

    @Then("^checkbox \"([^\"]*)\" is checked$")
    public void checkboxIsCheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        if (!checkbox.isChecked()) {
            throw exception("Checkbox is not checked.");
        }
    }

    @Then("^checkbox \"([^\"]*)\" is unchecked$")
    public void checkboxIsUncheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        if (checkbox.isChecked()) {
            throw exception("Checkbox is checked.");
        }
    }
}
