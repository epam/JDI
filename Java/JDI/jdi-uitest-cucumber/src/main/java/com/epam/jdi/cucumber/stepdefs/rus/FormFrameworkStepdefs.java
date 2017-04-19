package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.core.interfaces.complex.IForm;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.createFromJSON;
import static com.epam.jdi.cucumber.Utils.getElementByName;

/**
 * Created by Dmitry_Lebedev1 on 1/19/2016.
 */
public class FormFrameworkStepdefs {

    @Тогда("^я отправляю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iSubmitFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.submit(entity);
    }

    @Тогда("^я заполняю форму \"([^\"]*)\" с данными \"([^\"]*)\"$")
    public void iFillFormData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.fill(entity);
    }

    @Когда("^я проверяю что форма \"([^\"]*)\" заполнена данными \"([^\"]*)\"$")
    public void formContainsData(String formName, String json) {
        IForm form = getElementByName(formName);
        Object entity = createFromJSON(json, form);
        form.check(entity);
    }
}
