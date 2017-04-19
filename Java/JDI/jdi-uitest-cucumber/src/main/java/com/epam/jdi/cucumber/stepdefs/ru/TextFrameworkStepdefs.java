package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class TextFrameworkStepdefs {

    @И("^я заполняю поле \"([^\"]*)\" текстом \"([^\"]*)\"$")
    public void iMFillFieldByText(String fieldName, String text) throws Throwable {
        ((ITextField) getElementByName(fieldName)).sendKeys(text);
    }

    @Тогда("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void textFromContains(String fieldName, String contains) throws Throwable {
        ((IText) getElementByName(fieldName)).waitText(contains);
    }

    @И("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void textFromMach(String fieldName, String regex) throws Throwable {
        ((IText) getElementByName(fieldName)).waitMatchText(regex);
    }

    @И("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" из \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void textFromContains(String linkName, String containerName, String contains) throws Throwable {
        ((IText) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitText(contains);
    }

    @И("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" из \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void textFromMuch(String linkName, String containerName, String regex) throws Throwable {
        ((IText) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchText(regex);
    }
}
