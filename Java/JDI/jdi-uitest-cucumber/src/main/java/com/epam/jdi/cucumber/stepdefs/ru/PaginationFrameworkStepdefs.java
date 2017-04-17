package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.complex.IPagination;
import cucumber.api.java.ru.И;

import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;

public class PaginationFrameworkStepdefs {

    @И("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на следующую страницу$")
    public void iMUsePaginationToGoNext(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).next();
    }

    @И("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на предыдущую страницу$")
    public void iMUsePaginationToGoPrevious(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).previous();
    }

    @И("^я использую постраничную навигацию \"([^\"]*)\", чтобы выбрать страницу (\\d+)$")
    public void iMUsePaginationToSelect(String paginationName, int index) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).selectPage(index);
    }

    @И("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на последнюю страницу$")
    public void iMUsePaginationToGoLast(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).last();
    }

    @И("^я использую постраничную навигацию \"([^\"]*)\", чтобы перейти на первую страницу$")
    public void iMUsePaginationToGoFirst(String paginationName) throws Throwable {
        ((IPagination) Utils.getClassField(currentPage, paginationName)).first();
    }
}
