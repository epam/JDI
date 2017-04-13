package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.ru.*;

public class TextAreaFrameworkStepdefs {

    @Когда("^я ввожу в \"([^\"]*)\" ещё строки \"([^\"]*)\"$")
    public void iMInputToLines(String filedName, String lines) throws Throwable {
        ((TextArea) Utils.getClassField(WebPage.currentPage, filedName)).inputLines(lines);
    }

    @И("^я ввожу в \"([^\"]*)\" ещё строку \"([^\"]*)\"$")
    public void iMInputToNewLine(String filedName, String newLine) throws Throwable {
        ((TextArea) Utils.getClassField(WebPage.currentPage, filedName)).addNewLine(newLine);
    }

    //TODO
    @Тогда("^поле \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void fieldContains(String filedName, String contains) throws Throwable {
//        ((IText)getClassField(WebPage.currentPage, filedName)).con
    }

    @И("^я ввожу в \"([^\"]*)\" новые строки \"([^\"]*)\"$")
    public void iMInputToNewInput(String filedName, String newInput) throws Throwable {
        ((TextArea) Utils.getClassField(WebPage.currentPage, filedName)).newInput(newInput);
    }
}