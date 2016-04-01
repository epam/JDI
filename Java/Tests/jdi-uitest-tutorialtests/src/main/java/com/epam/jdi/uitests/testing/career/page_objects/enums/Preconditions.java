package com.epam.jdi.uitests.testing.career.page_objects.enums;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions;

import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.alwaysMoveToCondition;

/**
 * Created by 12345 on 03.06.2015.
 */
public enum Preconditions implements WebPreconditions {
    HOME_PAGE("");

    public Supplier<Boolean> checkAction;
    public JAction moveToAction;

    Preconditions(Supplier<Boolean> checkAction, JAction moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        alwaysMoveToCondition = true;
    }

    Preconditions(String uri) {
        this(() -> WebPreconditions.checkUrl(uri), () -> WebPreconditions.openUri(uri));
    }

    public Boolean checkAction() {
        return checkAction.get();
    }
    public void moveToAction() {
        moveToAction.invoke();
    }

}
