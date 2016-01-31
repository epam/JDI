package com.epam.jdi.uitests.testing.unittests.enums;

import com.epam.jdi.uitests.web.selenium.preconditions.IPreconditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.epam.jdi.uitests.testing.unittests.entities.User.DEFAULT;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.contactFormPage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dates;
import static com.epam.jdi.uitests.web.WebSettings.getDriver;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.alwaysMoveToCondition;

/**
 * Created by 12345 on 03.06.2015.
 */
public enum Preconditions implements IPreconditions {
    HOME_PAGE("index.htm"),
    CONTACT_PAGE("page1.htm"),
    CONTACT_PAGE_FILLED(d -> IPreconditions.checkUrl("page1.htm", d), d -> {
        IPreconditions.openUri("page1.htm", d);
        contactFormPage.name.newInput(DEFAULT.name);
        contactFormPage.lastName.newInput(DEFAULT.lastName);
        contactFormPage.description.newInput(DEFAULT.description);
    }),
    METALS_AND_COLORS_PAGE("page2.htm"),
    DATES_PAGE("page4.htm"),
    SUPPORT_PAGE("page3.htm", true),
    SORTING_TABLE_PAGE("page7.htm"),
    DYNAMIC_TABLE_PAGE("page5.htm"),
    SIMPLE_PAGE("page6.htm"),
    DATES_PAGE_FILLED(d -> IPreconditions.checkUrl("page4.htm", d), d -> {
        IPreconditions.openUri("page4.htm", d);
        WebElement datePicker = getDriver().findElement(dates.datepicker.getLocator());
        datePicker.clear();
        datePicker.sendKeys("09/09/1945");
    });

    public String _htmlPageName;

    public Function<WebDriver, Boolean> checkAction;
    public Consumer<WebDriver> moveToAction;

    Preconditions(Function<WebDriver, Boolean> checkAction, Consumer<WebDriver> moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        alwaysMoveToCondition = true;
    }

    Preconditions(String uri) {
        this(d -> IPreconditions.checkUrl(uri, d), d -> IPreconditions.openUri(uri, d));
        _htmlPageName = uri;
    }

    Preconditions(String uri, boolean isStatic) {
        this(uri);
        alwaysMoveToCondition = !isStatic;
    }

    public Function<WebDriver, Boolean> checkAction() {
        return checkAction;
    }

    public Consumer<WebDriver> moveToAction() {
        return moveToAction;
    }
}
