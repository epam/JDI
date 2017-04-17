package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.getElementByName;

/**
 * Created by Dmitry_Lebedev1 on 1/15/2016.
 */
public class ButtonFrameworkStepdefs {

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
