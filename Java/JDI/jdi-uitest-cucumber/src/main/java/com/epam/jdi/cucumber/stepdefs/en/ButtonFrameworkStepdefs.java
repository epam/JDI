package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.base.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.en.*;

/**
 * Created by Dmitry_Lebedev1 on 1/15/2016.
 */
public class ButtonFrameworkStepdefs {

    @Given("^I'm click on \"([^\"]*)\"(?:(?: button|link)$|$)")
    public void iMClickOnButton(String buttonName) throws Throwable {
        ((IClickable) Utils.getClassField(WebPage.currentPage, buttonName)).click();
    }

    @And("^I'm click on \"([^\"]*)\" (?:(?: button|link)) from \"([^\"]*)\"$")
    public void iMClickOnLinkFrom(String fieldName, String containerName) throws Throwable {
        ((IClickable) Utils.getClassField(Utils.getClassField(containerName), fieldName)).click();
    }
}
