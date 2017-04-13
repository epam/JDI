package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.complex.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.ru.*;

public class SearchFrameworkStepdefs {

    @И("^я ищу \"([^\"]*)\"$")
    public void iMFind(String findString) throws Throwable {
        ((Search) Utils.getClassFieldAnyway("search", ISearch.class)).find(findString);
    }
}
