package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;

/**
 * Created by Dmitry_Lebedev1 on 1/22/2016.
 */
public class TextAreaFrameworkStepdefs {

    @When("^I input to \"([^\"]*)\" lines \"([^\"]*)\"$")
    public void iMInputToLines(String filedName, String lines) {
        TextArea textArea = getElementByName(filedName);
        textArea.inputLines(lines);
    }

    @When("^I input to \"([^\"]*)\" new line \"([^\"]*)\"$")
    public void iMInputToNewLine(String filedName, String newLine) {
        TextArea textArea = getElementByName(filedName);
        textArea.addNewLine(newLine);
    }

    //TODO
    @Then("^field \"([^\"]*)\" contains \"([^\"]*)\"$")
    public void fieldContains(String filedName, String contains) {
//        ((IText)getClassField(WebPage.currentPage, filedName)).con
    }

    @When("^I input to \"([^\"]*)\" new input \"([^\"]*)\"$")
    public void iMInputToNewInput(String filedName, String newInput) {
        TextArea textArea = getElementByName(filedName);
        textArea.newInput(newInput);
    }
}