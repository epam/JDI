package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.en.*;

/**
 * Created by Dmitry_Lebedev1 on 1/14/2016.
 */
public class TextFrameworkStepdefs {

    @And("^I'm fill field \"([^\"]*)\" by text \"([^\"]*)\"$")
    public void iMFillFieldByText(String fieldName, String text) throws Throwable {
        ((ITextField) Utils.getClassField(WebPage.currentPage, fieldName)).sendKeys(text);
    }

    @Then("^(?:text|label|link|button) \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void textFromContains(String fieldName, String contains) throws Throwable {
        ((IText) Utils.getClassField(WebPage.currentPage, fieldName)).waitText(contains);
    }

    @And("^(?:text|label|link|button) \"([^\"]*)\" mach \"([^\"]*)\"$")
    public void textFromMach(String fieldName, String regex) throws Throwable {
        ((IText) Utils.getClassField(WebPage.currentPage, fieldName)).waitMatchText(regex);
    }

    @And("^(?:text|label|link|button) \"([^\"]*)\" from \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void textFromContains(String linkName, String containerName, String contains) throws Throwable {
        ((IText) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitText(contains);
    }

    @And("^(?:text|label|link|button) \"([^\"]*)\" from \"([^\"]*)\" much \"([^\"]*)\"$")
    public void textFromMuch(String linkName, String containerName, String regex) throws Throwable {
        ((IText) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchText(regex);
    }
}
