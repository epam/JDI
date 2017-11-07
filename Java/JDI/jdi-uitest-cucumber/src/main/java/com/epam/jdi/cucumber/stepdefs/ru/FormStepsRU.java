package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.createFromJSON;
import static com.epam.jdi.cucumber.Utils.getElementByName;

public class FormStepsRU {
    @Когда("^я отправляю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.submit(entity);
    }

    @Когда("^я заполняю форму \"([^\"]*)\" данными \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.fill(entity);
    }

    @Тогда("^форма \"([^\"]*)\" заполнена данными \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.check(entity);
    }
}
