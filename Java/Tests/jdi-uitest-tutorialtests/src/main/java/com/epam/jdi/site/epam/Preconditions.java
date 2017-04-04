package com.epam.jdi.site.epam;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.preconditions.IPreconditions;
import com.epam.web.matcher.testng.Assert;
import com.google.common.base.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.alwaysMoveToCondition;
import static com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions.checkUrl;
import static com.epam.jdi.uitests.web.selenium.preconditions.WebPreconditions.openUri;

/**
 * Created by Roman_Iovlev on 9/10/2016.
 */
public enum Preconditions implements IPreconditions {
    JOBS_LIST_SHOWN(
        () -> EpamSite.jobListingPage.verifyOpened() && !EpamSite.jobListingPage.jobsList.isEmpty(),
        () -> {
            EpamSite.jobListingPage.shouldBeOpened();
            Assert.isFalse(() -> EpamSite.jobListingPage.jobsList.isEmpty());
        }
    ),
    CAREERS_PAGE("/careers");

    public Supplier<Boolean> checkAction;
    public JAction moveToAction;

    Preconditions(Supplier<Boolean> checkAction, JAction moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        alwaysMoveToCondition = true;
    }

    Preconditions(String uri) { this(() -> checkUrl(uri), () -> openUri(uri)); }

    public Boolean checkAction() { return checkAction.get();}

    public void moveToAction() { moveToAction.invoke(); }
}
