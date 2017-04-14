package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.base.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.en.*;
import org.junit.*;

/**
 * Created by Dmitry_Lebedev1 on 1/20/2016.
 */
public class ElementFrameworkStepdefs {

    @And("^for element \"([^\"]*)\" I set attribute \"([^\"]*)\" on \"([^\"]*)\"$")
    public void forElementISetAttributeOn(String fieldName, String attributeName, String attributeValue) throws Throwable {
        ((IElement) Utils.getClassField(WebPage.currentPage, fieldName)).setAttribute(attributeName, attributeValue);
    }

    @Then("^element \"([^\"]*)\" has /attribute \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void elementHasAttributeWithValue(String fieldName, String attributeName, String attributeValue) throws Throwable {
        Assert.assertTrue(((IElement) Utils.getClassField(WebPage.currentPage, fieldName)).getAttribute(attributeName).equals(attributeValue));
    }
}