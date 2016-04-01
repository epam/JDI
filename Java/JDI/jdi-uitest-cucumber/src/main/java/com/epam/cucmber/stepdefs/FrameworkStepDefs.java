package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import cucumber.api.java.en.Given;


/**
 * Created by Dmitry_Lebedev1 on 1/12/2016.
 */
public class FrameworkStepDefs {

    @Given("^I'm fill \"([^\"]*)\" by \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) throws Throwable {
        ((TextField) Utils.getClassField(WebPage.currentPage, fieldName)).setValue(data);
    }

}
