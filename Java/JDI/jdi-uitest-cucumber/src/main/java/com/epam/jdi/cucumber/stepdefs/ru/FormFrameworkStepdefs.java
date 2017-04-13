package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import cucumber.api.java.ru.*;

public class FormFrameworkStepdefs {

    @И("^я отправляю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) throws Throwable {
        Utils.processForm(formName, json, FormActions.SUBMIT);
    }

    @И("^я заполняю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) throws Throwable {
        Utils.processForm(formName, json, FormActions.FILL);
    }

    @Тогда("^форма \"([^\"]*)\" заполнена данными \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) throws Throwable {
        Utils.processForm(formName, json, FormActions.CHECK);
    }
}
