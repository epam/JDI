package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.complex.IPagination;
import com.epam.jdi.uitests.web.selenium.elements.composite.Pagination;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;

public class PaginationFrameworkStepdefs {

    @When("^On pagination \"([^\"]*)\" I press next$")
    public void iMUsePaginationToGoNext(String paginationName) {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).next();
    }

    @When("^On pagination \"([^\"]*)\" I press previous$")
    public void iMUsePaginationToGoPrevious(String paginationName) {
        Pagination pagination = getElementByName(paginationName);
        pagination.previous();
    }

    @When("^On pagination \"([^\"]*)\" I select page number (\\d+)$")
    public void iMUsePaginationToSelect(String paginationName, int index) {
        Pagination pagination = getElementByName(paginationName);
        pagination.selectPage(index);
    }

    @When("^On pagination \"([^\"]*)\" I go to last$")
    public void iMUsePaginationToGoLast(String paginationName) {
        Pagination pagination = getElementByName(paginationName);
        pagination.last();
    }

    @When("^On pagination \"([^\"]*)\" I go to first$")
    public void iMUsePaginationToGoFirst(String paginationName) {
        Pagination pagination = getElementByName(paginationName);
        pagination.first();
    }
}
