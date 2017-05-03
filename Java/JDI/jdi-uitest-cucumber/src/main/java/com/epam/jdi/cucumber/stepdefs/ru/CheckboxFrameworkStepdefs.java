package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class CheckboxFrameworkStepdefs {

    @Тогда("^я отмечаю \"([^\"]*)\"$")
    public void iMCheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        checkbox.check();
    }

    @Тогда("^я снял отметку с \"([^\"]*)\"$")
    public void iMUncheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        checkbox.uncheck();
    }

    @Когда("^флажок \"([^\"]*)\" отмечен$")
    public void checkboxIsCheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        if (!checkbox.isChecked()) {
            throw exception("Checkbox is not checked.");
        }
    }

    @Когда("^флажок \"([^\"]*)\" не отмечен$")
    public void checkboxIsUncheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        if (checkbox.isChecked()) {
            throw exception("Checkbox is checked.");
        }
    }
}
