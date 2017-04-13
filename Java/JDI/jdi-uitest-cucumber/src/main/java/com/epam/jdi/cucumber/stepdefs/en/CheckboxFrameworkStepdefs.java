package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.en.*;

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

    @Then("^checkbox \"([^\"]*)\" is checked$")
    public void checkboxIsCheck(String fieldName) throws Throwable {
        if (!((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).isChecked()) {
            throw new Exception("Checkbox is not checked.");
        }
    }

    @Then("^checkbox \"([^\"]*)\" is unchecked$")
    public void checkboxIsUncheck(String fieldName) throws Throwable {
        if (((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).isChecked()) {
            throw new Exception("Checkbox is checked.");
        }
    }
}
