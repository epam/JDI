package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class ButtonFrameworkStepdefs {

    @Дано("^я нажимаю на \"([^\"]*)\"(?:(?: кнопку|ссылку)$|$)")
    public void iMClickOnButton(String buttonName) throws Throwable {
        ((IClickable) getElementByName(buttonName)).click();
    }

    @И("^я нажимаю на \"([^\"]*)\" (?:(?: кнопку|ссылку)) из \"([^\"]*)\"$")
    public void iMClickOnLinkFrom(String fieldName, String containerName) throws Throwable {
        ((IClickable) Utils.getClassField(Utils.getClassField(containerName), fieldName)).click();
    }
}
