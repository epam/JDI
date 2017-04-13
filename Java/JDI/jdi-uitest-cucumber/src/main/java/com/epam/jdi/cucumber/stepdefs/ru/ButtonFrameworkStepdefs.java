package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.base.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.ru.*;

public class ButtonFrameworkStepdefs {

    @Дано("^я нажимаю на \"([^\"]*)\"(?:(?: кнопку|ссылку)$|$)")
    public void iMClickOnButton(String buttonName) throws Throwable {
        ((IClickable) Utils.getClassField(WebPage.currentPage, buttonName)).click();
    }

    @И("^я нажимаю на \"([^\"]*)\" (?:(?: кнопку|ссылку)) из \"([^\"]*)\"$")
    public void iMClickOnLinkFrom(String fieldName, String containerName) throws Throwable {
        ((IClickable) Utils.getClassField(Utils.getClassField(containerName), fieldName)).click();
    }
}
