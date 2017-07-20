package com.epam.jdi.cucumber.stepdefs.en;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.interfaces.Application.currentSite;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;

public class PageStepsEN {

    @Given("^I open \"(.*?)\"$")
    public void iMOpen(String pageName) {
        currentPage = getElementByName(currentSite, pageName);
        currentPage.open();
    }

    @Then("^I'm on \"(.*?)\"$")
    public void iMOn(String pageName) {
        currentPage = getElementByName(currentSite, pageName);
        currentPage.checkOpened();
    }

    @When("^I refresh page$")
    public void iMRefreshPage() {
        currentPage.refresh();
    }

    @When("^I go back$")
    public void iMGoBack() {
        currentPage.back();
    }

    @When("^I go forward$")
    public void iMGoForward() {
        currentPage.forward();
    }

    @Then("^I check that page url match$")
    public void checkPageUrlMatch() {
        currentPage.url().match();
    }

    @Then("^I check that page url contains$")
    public void checkPageUrlContains() {
        currentPage.url().contains();
    }
}
