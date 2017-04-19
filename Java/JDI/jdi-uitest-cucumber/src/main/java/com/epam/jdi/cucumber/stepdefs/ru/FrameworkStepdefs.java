package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import cucumber.api.java.ru.Дано;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class FrameworkStepdefs {

    @Дано("^я заполняю \"([^\"]*)\" значением \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) throws Throwable {
        ((TextField) getElementByName(fieldName)).setValue(data);
    }
}
