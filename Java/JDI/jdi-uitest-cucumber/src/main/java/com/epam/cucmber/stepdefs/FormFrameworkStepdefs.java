package com.epam.cucmber.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

/**
 * Created by Dmitry_Lebedev1 on 1/19/2016.
 */
public class FormFrameworkStepdefs {
    @And("^I'm submit form \"([^\"]*)\" data \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) throws Throwable {
        Utils.processForm(formName, json, FormActions.SUBMIT);
    }

    @And("^I'm fill form \"([^\"]*)\" data \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) throws Throwable {
        Utils.processForm(formName, json, FormActions.FILL);
    }

    @Then("^Form \"([^\"]*)\" contains data \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) throws Throwable {
        Utils.processForm(formName, json, FormActions.CHECK);
    }
}
