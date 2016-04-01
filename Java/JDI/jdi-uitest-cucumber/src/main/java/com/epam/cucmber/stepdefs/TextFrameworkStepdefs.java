package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

/**
 * Created by Dmitry_Lebedev1 on 1/14/2016.
 */
public class TextFrameworkStepdefs {

    @And("^I'm fill field \"([^\"]*)\" by text \"([^\"]*)\"$")
    public void iMFillFieldByText(String fieldName, String text) throws Throwable {
        ((ITextField) Utils.getClassField(WebPage.currentPage, fieldName)).sendKeys(text);
    }

    @Then("^(?:Text|Label|Link|Button) \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void textFromContains(String fieldName, String contains) throws Throwable {
        ((IText) Utils.getClassField(WebPage.currentPage, fieldName)).waitText(contains);
    }

    @And("^(?:Text|Label|Link|Button) \"([^\"]*)\" mach \"([^\"]*)\"$")
    public void textFromMach(String fieldName, String regex) throws Throwable {
        ((IText) Utils.getClassField(WebPage.currentPage, fieldName)).waitMatchText(regex);
    }

    @And("^(?:Text|Label|Link|Button) \"([^\"]*)\" from \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void textFromContains(String linkName, String containerName, String contains) throws Throwable {
        ((IText) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitText(contains);
    }

    @And("^(?:Text|Label|Link|Button) \"([^\"]*)\" from \"([^\"]*)\" much \"([^\"]*)\"$")
    public void textFromMuch(String linkName, String containerName, String regex) throws Throwable {
        ((IText) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchText(regex);
    }
}
