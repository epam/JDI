package com.epam.jdi.uitests.testing.unittests.enums;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.alwaysMoveToCondition;
import static com.epam.jdi.uitests.testing.unittests.entities.User.DEFAULT;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.contactFormPage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dates;
import static com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions.checkUrl;
import static com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions.openUri;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;

/**
 * Created by 12345 on 03.06.2015.
 */
public enum Preconditions implements WebPreconditions {
    HOME_PAGE("index.html"),
    CONTACT_PAGE("contacts.html"),
    CONTACT_PAGE_FILLED(() -> checkUrl("contacts.html"), () -> {
        openUri("contacts.html");
        contactFormPage.name.newInput(DEFAULT.name);
        contactFormPage.lastName.newInput(DEFAULT.lastName);
        contactFormPage.description.newInput(DEFAULT.description);
    }),
    METALS_AND_COLORS_PAGE("metals-colors.html"),
    DATES_PAGE("dates.html"),
    SUPPORT_PAGE("support.html", true),
    SORTING_TABLE_PAGE("table-pages.html"),
    DYNAMIC_TABLE_PAGE("complex-table.html"),
    SIMPLE_PAGE("simple-table.html"),
    DATES_PAGE_FILLED(
        () -> checkUrl("dates.html"),
        () -> {
        openUri("dates.html");
        WebElement datePicker = getDriver().findElement(dates.datepicker.getLocator());
        datePicker.clear();
        datePicker.sendKeys("09/09/1945");
    });

    public Supplier<Boolean> checkAction;
    public JAction moveToAction;

    Preconditions(Supplier<Boolean> checkAction, JAction moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        alwaysMoveToCondition = true;
    }

    Preconditions(String uri) {
        this(() -> checkUrl(uri), () -> openUri(uri));
    }

    Preconditions(String uri, boolean isStatic) {
        this(uri);
        alwaysMoveToCondition = !isStatic;
    }

    public Boolean checkAction() {
        return checkAction.get();
    }

    public void moveToAction() {
        moveToAction.invoke();
    }
}
