package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class TextAreaFrameworkStepdefs {

    @Когда("^я ввожу в \"([^\"]*)\" ещё строки \"([^\"]*)\"$")
    public void iMInputToLines(String filedName, String lines) throws Throwable {
        ((TextArea) getElementByName(filedName)).inputLines(lines);
    }

    @И("^я ввожу в \"([^\"]*)\" ещё строку \"([^\"]*)\"$")
    public void iMInputToNewLine(String filedName, String newLine) throws Throwable {
        ((TextArea) getElementByName(filedName)).addNewLine(newLine);
    }

    //TODO
    @Тогда("^поле \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void fieldContains(String filedName, String contains) throws Throwable {
//        ((IText)getClassField(WebPage.currentPage, filedName)).con
    }

    @И("^я ввожу в \"([^\"]*)\" новые строки \"([^\"]*)\"$")
    public void iMInputToNewInput(String filedName, String newInput) throws Throwable {
        ((TextArea) getElementByName(filedName)).newInput(newInput);
    }
}