package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class ElementFrameworkStepdefs {

    @When("^for element \"([^\"]*)\" I set attribute \"([^\"]*)\" on \"([^\"]*)\"$")
    public void forElementISetAttributeOn(String fieldName, String attributeName, String attributeValue) {
        IElement el = getElementByName(fieldName);
        el.setAttribute(attributeName, attributeValue);
    }

    @Then("^element \"([^\"]*)\" has /attribute \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void elementHasAttributeWithValue(String fieldName, String attributeName, String attributeValue) {
        IElement el = getElementByName(fieldName);
        Assert.assertTrue(el.getAttribute(attributeName).equals(attributeValue));
    }
}