package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Тогда;

public class CheckboxFrameworkStepdefs {

    @И("^я отмечаю \"([^\"]*)\"$")
    public void iMCheck(String fieldName) {
        ((ICheckBox) getElementByName(fieldName)).check();
    }

    @И("^я снял отметку с \"([^\"]*)\"$")
    public void iMUncheck(String fieldName) {
        ((ICheckBox) getElementByName(fieldName)).uncheck();
    }

    @Тогда("^Флажок \"([^\"]*)\" отмечен$")
    public void checkboxIsCheck(String fieldName) {
        if (!((ICheckBox) getElementByName(fieldName)).isChecked()) {
            throw new Exception("Checkbox is not checked.");
        }
    }

    @Тогда("^Флажок \"([^\"]*)\" не отмечен$")
    public void checkboxIsUncheck(String fieldName) {
        if (((ICheckBox) getElementByName(fieldName)).isChecked()) {
            throw new Exception("Checkbox is checked.");
        }
    }
}
