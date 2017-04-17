package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Тогда;

public class PageFrameworkStepdefs {

    @Дано("^я открываю страницу \"(.*?)\"$")
    public void iMOpen(String pageName) {
        ((WebPage) Utils.getClassField(WebSite.currentSite, pageName)).open();
    }

    @Тогда("^я на странице \"(.*?)\"$")
    public void iMOn(String pageName) throws Throwable {
        ((WebPage) Utils.getClassField(WebSite.currentSite, pageName)).checkOpened();
    }

    @И("^я обновляю страницу$")
    public void iMRefreshPage() throws Throwable {
        WebPage.currentPage.refresh();
    }

    @И("^я перехожу на предыдущую страницу$")
    public void iMGoBack() throws Throwable {
        WebPage.currentPage.back();
    }

    @И("^я перехожу на последующую страницу$")
    public void iMGoForward() throws Throwable {
        WebPage.currentPage.forward();
    }

    @И("^проверяю соответствие адреса текущей страницы$")
    public void checkPageUrlMatch() throws Throwable {
        WebPage.currentPage.url().match();
    }

    @И("^проверяю содержимое адреса текущей страницы$")
    public void checkPageUrlContains() throws Throwable {
        WebPage.currentPage.url().contains();
    }
}
