package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.en.*;

/**
 * Created by Dmitry_Lebedev1 on 1/12/2016.
 */
public class FrameworkStepdefs {

    @Given("^I'm fill \"([^\"]*)\" by \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) throws Throwable {
        ((TextField) Utils.getClassField(WebPage.currentPage, fieldName)).setValue(data);
    }
}
