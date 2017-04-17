package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.complex.ISearch;
import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import cucumber.api.java.ru.И;

public class SearchFrameworkStepdefs {

    @И("^я ищу \"([^\"]*)\"$")
    public void iMFind(String findString) throws Throwable {
        ((Search) Utils.getClassFieldAnyway("search", ISearch.class)).find(findString);
    }
}
