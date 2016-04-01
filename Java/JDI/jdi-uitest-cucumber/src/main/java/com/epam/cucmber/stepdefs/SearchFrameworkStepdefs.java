package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.complex.ISearch;
import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import cucumber.api.java.en.And;

/**
 * Created by Dmitry_Lebedev1 on 1/21/2016.
 */
public class SearchFrameworkStepdefs {
    @And("^I'm find \"([^\"]*)\"$")
    public void iMFind(String findString) throws Throwable {
        ((Search) Utils.getClassFieldAnyway("search", ISearch.class)).find(findString);
    }
}
