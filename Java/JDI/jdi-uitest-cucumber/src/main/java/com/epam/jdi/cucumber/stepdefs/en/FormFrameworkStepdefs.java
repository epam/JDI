package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.createFromJSON;
import static com.epam.jdi.cucumber.Utils.getElementByName;

public class FormFrameworkStepdefs {

    @When("^I submit form \"([^\"]*)\" data \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.submit(entity);
    }

    @When("^I fill form \"([^\"]*)\" data \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.fill(entity);
    }

    @Then("^form \"([^\"]*)\" contains data \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.check(entity);
    }
}
