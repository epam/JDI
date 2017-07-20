package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class ButtonStepsRU {

    @Тогда("^я нажимаю на \"([^\"]*)\"(?:(?: кнопку|ссылку)$|$)")
    public void iMClickOnButton(String buttonName) {
        IClickable cl = getElementByName(buttonName);
        cl.click();
    }

    @Когда("^я нажимаю на \"([^\"]*)\" (?:(?: кнопку|ссылку)) из \"([^\"]*)\"$")
    public void iMClickOnLinkFrom(String fieldName, String containerName) {
        IClickable cl = getElementByName(containerName, fieldName);
        cl.click();
    }
}
