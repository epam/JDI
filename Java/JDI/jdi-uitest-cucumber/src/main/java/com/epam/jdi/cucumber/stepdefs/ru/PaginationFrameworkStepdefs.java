package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.complex.IPagination;
import com.epam.jdi.uitests.web.selenium.elements.composite.Pagination;
import cucumber.api.java.en.When;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;

public class PaginationFrameworkStepdefs {

    @When("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на следующую страницу$")
    public void iMUsePaginationToGoNext(String paginationName) {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).next();
    }

    @When("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на предыдущую страницу$")
    public void iMUsePaginationToGoPrevious(String paginationName) {
        Pagination pagination = getElementByName(paginationName);
        pagination.previous();
    }

    @When("^я использую постраничную навигацию \"([^\"]*)\", чтобы выбрать страницу (\\d+)$")
    public void iMUsePaginationToSelect(String paginationName, int index) {
        Pagination pagination = getElementByName(paginationName);
        pagination.selectPage(index);
    }

    @When("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на последнюю страницу$")
    public void iMUsePaginationToGoLast(String paginationName) {
        Pagination pagination = getElementByName(paginationName);
        pagination.last();
    }

    @When("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на первую страницу$")
    public void iMUsePaginationToGoFirst(String paginationName) {
        Pagination pagination = getElementByName(paginationName);
        pagination.first();
    }
}
