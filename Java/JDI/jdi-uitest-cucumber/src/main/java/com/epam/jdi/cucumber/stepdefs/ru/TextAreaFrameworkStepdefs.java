package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class TextAreaFrameworkStepdefs {

    @When("^я ввожу в \"([^\"]*)\" ещё строки \"([^\"]*)\"$")
    public void iMInputToLines(String filedName, String lines) {
        TextArea textArea = getElementByName(filedName);
        textArea.inputLines(lines);
    }

    @When("^я ввожу в \"([^\"]*)\" ещё строку \"([^\"]*)\"$")
    public void iMInputToNewLine(String filedName, String newLine) {
        TextArea textArea = getElementByName(filedName);
        textArea.addNewLine(newLine);
    }

    //TODO
    @Then("^поле \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void fieldContains(String filedName, String contains) {
//        ((IText)getClassField(WebPage.currentPage, filedName)).con
    }

    @When("^я ввожу в \"([^\"]*)\" новые строки \"([^\"]*)\"$")
    public void iMInputToNewInput(String filedName, String newInput) {
        TextArea textArea = getElementByName(filedName);
        textArea.newInput(newInput);
    }
}