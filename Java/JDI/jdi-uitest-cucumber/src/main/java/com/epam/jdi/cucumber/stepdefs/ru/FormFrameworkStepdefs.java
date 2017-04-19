package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.FormActions;
import com.epam.jdi.cucumber.Utils;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Тогда;

public class FormFrameworkStepdefs {

    @И("^я отправляю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) throws Throwable {
        //Utils.getForm(formName, json, FormActions.SUBMIT);
    }

    @И("^я заполняю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) throws Throwable {
        //Utils.getForm(formName, json, FormActions.FILL);
    }

    @Тогда("^форма \"([^\"]*)\" заполнена данными \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) throws Throwable {
        //Utils.getForm(formName, json, FormActions.CHECK);
    }
}
