package com.epam.jdi.uitests.testing.career.page_objects.enums;

import com.epam.jdi.uitests.web.selenium.preconditions.IPreconditions;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;
import java.util.function.Function;

import static com.epam.jdi.uitests.web.selenium.preconditions.IPreconditions.checkUrl;
import static com.epam.jdi.uitests.web.selenium.preconditions.IPreconditions.openUri;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.alwaysMoveToCondition;

/**
 * Created by 12345 on 03.06.2015.
 */
public enum Preconditions implements IPreconditions {
    HOME_PAGE("");

    public Function<WebDriver, Boolean> checkAction;
    public Consumer<WebDriver> moveToAction;

    Preconditions(Function<WebDriver, Boolean> checkAction, Consumer<WebDriver> moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        alwaysMoveToCondition = true;
    }
    Preconditions(String uri) {
        this(d -> checkUrl(uri, d), d -> openUri(uri, d));
    }

    public Function<WebDriver, Boolean> checkAction() {
        return checkAction;
    }
    public Consumer<WebDriver> moveToAction() {
        return moveToAction;
    }

}
