package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.complex.IPagination;
import cucumber.api.java.en.And;

import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;

/**
 * Created by Dmitry_Lebedev1 on 1/21/2016.
 */
public class PaginationFrameworkStepdefs {

    @And("^I'm use pagination \"([^\"]*)\" to go next$")
    public void iMUsePaginationToGoNext(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).next();
    }

    @And("^I'm use pagination \"([^\"]*)\" to go previous$")
    public void iMUsePaginationToGoPrevious(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).previous();
    }

    @And("^I'm use pagination \"([^\"]*)\" to select (\\d+)$")
    public void iMUsePaginationToSelect(String paginationName, int index) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).selectPage(index);
    }

    @And("^I'm use pagination \"([^\"]*)\" to go last$")
    public void iMUsePaginationToGoLast(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).last();
    }

    @And("^I'm use pagination \"([^\"]*)\" to go first$")
    public void iMUsePaginationToGoFirst(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).first();
    }
}
