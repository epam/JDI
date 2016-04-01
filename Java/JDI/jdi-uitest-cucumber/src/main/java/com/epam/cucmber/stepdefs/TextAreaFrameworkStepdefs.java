package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by Dmitry_Lebedev1 on 1/22/2016.
 */
public class TextAreaFrameworkStepdefs {

    @When("^I'm input to \"([^\"]*)\" lines \"([^\"]*)\"$")
    public void iMInputToLines(String filedName, String lines) throws Throwable {
        ((TextArea) Utils.getClassField(WebPage.currentPage, filedName)).inputLines(lines);
    }

    @And("^I'm input to \"([^\"]*)\" new line \"([^\"]*)\"$")
    public void iMInputToNewLine(String filedName, String newLine) throws Throwable {
        ((TextArea) Utils.getClassField(WebPage.currentPage, filedName)).addNewLine(newLine);
    }

    //TODO
    @Then("^Field \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void fieldContains(String filedName, String contains) throws Throwable {
//        ((IText)getClassField(WebPage.currentPage, filedName)).con
    }

    @And("^I'm input to \"([^\"]*)\" new input \"([^\"]*)\"$")
    public void iMInputToNewInput(String filedName, String newInput) throws Throwable {
        ((TextArea) Utils.getClassField(WebPage.currentPage, filedName)).newInput(newInput);
    }

}