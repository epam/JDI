package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.interfaces.Application.currentSite;

public class SearchFrameworkStepdefs {

    @When("^я ищу \"([^\"]*)\"$")
    public void iMFind(String findString) {
        Search search = getElementByName(currentSite, findString);
        search.find(findString);
    }
}
