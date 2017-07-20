package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class ElementStepsRU {

    @When("^у элемента \"([^\"]*)\" я устанавливаю атрибуту \"([^\"]*)\" значение \"([^\"]*)\"$")
    public void forElementISetAttributeOn(String fieldName, String attributeName, String attributeValue) {
        IElement el = getElementByName(fieldName);
        el.setAttribute(attributeName, attributeValue);
    }

    @Then("^элемент \"([^\"]*)\" содержит атрибут \"([^\"]*)\" со значением \"([^\"]*)\"$")
    public void elementHasAttributeWithValue(String fieldName, String attributeName, String attributeValue) {
        IElement el = getElementByName(fieldName);
        Assert.assertTrue(el.getAttribute(attributeName).equals(attributeValue));
    }
}