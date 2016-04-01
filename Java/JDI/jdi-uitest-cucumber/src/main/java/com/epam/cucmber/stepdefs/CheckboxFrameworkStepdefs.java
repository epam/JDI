package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

/**
 * Created by Dmitry_Lebedev1 on 1/20/2016.
 */
public class CheckboxFrameworkStepdefs {

    @And("^I'm check \"([^\"]*)\"$")
    public void iMCheck(String fieldName) throws Throwable {
        ((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).check();
    }

    @And("^I'm unchecked \"([^\"]*)\"$")
    public void iMUncheck(String fieldName) throws Throwable {
        ((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).uncheck();
    }

    @Then("^Checkbox \"([^\"]*)\" is checked$")
    public void checkboxIsCheck(String fieldName) throws Throwable {
        if (!((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).isChecked()) {
            throw new Exception("Checkbox is not checked.");
        }
    }

    @Then("^Checkbox \"([^\"]*)\" is unchecked$")
    public void checkboxIsUncheck(String fieldName) throws Throwable {
        if (((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).isChecked()) {
            throw new Exception("Checkbox is checked.");
        }
    }
}
