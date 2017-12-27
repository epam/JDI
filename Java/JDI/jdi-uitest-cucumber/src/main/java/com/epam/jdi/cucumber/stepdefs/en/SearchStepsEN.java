package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.interfaces.Application.currentSite;

public class SearchStepsEN {

    @When("^I find \"([^\"]*)\"$")
    public void iMFind(String findString) {
        Search search = getElementByName(currentSite, findString);
        search.find(findString);
    }
}
