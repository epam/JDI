package com.epam.jdi.cucumber.stepdefs.ru;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.core.interfaces.Application.currentSite;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;

public class PageFrameworkStepdefs {

    @Given("^я открываю страницу \"(.*?)\"$")
    public void iMOpen(String pageName) {
        currentPage = getElementByName(currentSite, pageName);
        currentPage.open();
    }

    @Then("^я на странице \"(.*?)\"$")
    public void iMOn(String pageName) {
        currentPage = getElementByName(currentSite, pageName);
        currentPage.checkOpened();
    }

    @When("^я обновляю страницу$")
    public void iMRefreshPage() {
        currentPage.refresh();
    }

    @When("^я перехожу на предыдущую страницу$")
    public void iMGoBack() {
        currentPage.back();
    }

    @When("^я перехожу на последующую страницу$")
    public void iMGoForward() {
        currentPage.forward();
    }

    @Then("^я проверяю соответствие адреса текущей страницы$")
    public void checkPageUrlMatch() {
        currentPage.url().match();
    }

    @Then("^я проверяю содержимое адреса текущей страницы$")
    public void checkPageUrlContains() {
        currentPage.url().contains();
    }
}
