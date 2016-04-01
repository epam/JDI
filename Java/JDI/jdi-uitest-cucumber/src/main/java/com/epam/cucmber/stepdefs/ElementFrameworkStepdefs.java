package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

/**
 * Created by Dmitry_Lebedev1 on 1/20/2016.
 */
public class ElementFrameworkStepdefs {

    @And("^For element \"([^\"]*)\" I set attribute \"([^\"]*)\" on \"([^\"]*)\"$")
    public void forElementISetAttributeOn(String fieldName, String attributeName, String attributeValue) throws Throwable {
        ((IElement) Utils.getClassField(WebPage.currentPage, fieldName)).setAttribute(attributeName, attributeValue);
    }

    @Then("^Element \"([^\"]*)\" has attribute \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void elementHasAttributeWithValue(String fieldName, String attributeName, String attributeValue) throws Throwable {
        Assert.assertTrue(((IElement) Utils.getClassField(WebPage.currentPage, fieldName)).getAttribute(attributeName).equals(attributeValue));
    }
}