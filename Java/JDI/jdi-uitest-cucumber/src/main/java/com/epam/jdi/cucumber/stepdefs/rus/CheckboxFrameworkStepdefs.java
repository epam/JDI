package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Dmitry_Lebedev1 on 1/20/2016.
 */
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

    @Когда("^Флажок \"([^\"]*)\" отмечен$")
    public void checkboxIsCheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        if (!checkbox.isChecked()) {
            throw exception("Checkbox is not checked.");
        }
    }

    @Когда("^Флажок \"([^\"]*)\" не отмечен$")
    public void checkboxIsUncheck(String fieldName) {
        ICheckBox checkbox = getElementByName(fieldName);
        if (checkbox.isChecked()) {
            throw exception("Checkbox is checked.");
        }
    }
}
