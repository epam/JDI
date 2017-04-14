package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.ru.*;

public class FrameworkStepdefs {

    @Дано("^я заполняю \"([^\"]*)\" значением \"([^\"]*)\"$")
    public void iMFillBy(String fieldName, String data) throws Throwable {
        ((TextField) Utils.getClassField(WebPage.currentPage, fieldName)).setValue(data);
    }
}
