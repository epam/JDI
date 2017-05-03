package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class TextFrameworkStepdefs {

    @And("^я заполняю поле \"([^\"]*)\" текстом \"([^\"]*)\"$")
    public void iMFillFieldByText(String fieldName, String text) {
        ITextField textField = getElementByName(fieldName);
        textField.sendKeys(text);
    }

    @Then("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void textFromContains(String fieldName, String contains) {
        IText text = getElementByName(fieldName);
        text.waitText(contains);
    }

    @Then("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void textFromMach(String fieldName, String regex) {
        IText text = getElementByName(fieldName);
        text.waitMatchText(regex);
    }

    @Then("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" из \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void textFromContains(String linkName, String containerName, String contains) {
        IText text = getElementByName(containerName, linkName);
        text.waitText(contains);
    }

    @Then("^(?:текст|лейбл|ссылка|кнопка) \"([^\"]*)\" из \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void textFromMuch(String linkName, String containerName, String regex) {
        IText text = getElementByName(containerName, linkName);
        text.waitMatchText(regex);
    }
}
