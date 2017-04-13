package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.ru.*;

public class CheckboxFrameworkStepdefs {

    @И("^я отмечаю \"([^\"]*)\"$")
    public void iMCheck(String fieldName) throws Throwable {
        ((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).check();
    }

    @И("^я снял отметку с \"([^\"]*)\"$")
    public void iMUncheck(String fieldName) throws Throwable {
        ((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).uncheck();
    }

    @Тогда("^Флажок \"([^\"]*)\" отмечен$")
    public void checkboxIsCheck(String fieldName) throws Throwable {
        if (!((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).isChecked()) {
            throw new Exception("Checkbox is not checked.");
        }
    }

    @Тогда("^Флажок \"([^\"]*)\" не отмечен$")
    public void checkboxIsUncheck(String fieldName) throws Throwable {
        if (((ICheckBox) Utils.getClassField(WebPage.currentPage, fieldName)).isChecked()) {
            throw new Exception("Checkbox is checked.");
        }
    }
}
