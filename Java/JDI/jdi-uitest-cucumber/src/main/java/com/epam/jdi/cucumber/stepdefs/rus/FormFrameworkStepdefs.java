package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.createFromJSON;
import static com.epam.jdi.cucumber.Utils.getElementByName;

public class FormFrameworkStepdefs {

    @When("^я отправляю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.submit(entity);
    }

    @When("^я заполняю форму \"([^\"]*)\" данными \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.fill(entity);
    }

    @Then("^форма \"([^\"]*)\" заполнена данными \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.check(entity);
    }
}
