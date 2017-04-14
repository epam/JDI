package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.complex.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.en.*;

/**
 * Created by Dmitry_Lebedev1 on 1/21/2016.
 */
public class SearchFrameworkStepdefs {

    @And("^I'm find \"([^\"]*)\"$")
    public void iMFind(String findString) throws Throwable {
        ((Search) Utils.getClassFieldAnyway("search", ISearch.class)).find(findString);
    }
}
