package com.epam.jdi.uitests.testing.career.parallel.tests;

import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.jdi.uitests.testing.career.page_objects.site.EpamSiteParallel;
import com.epam.web.matcher.testng.Check;

import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.uitests.testing.career.parallel.tests.DriverBalancer.getDriverName;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.init;
import static java.lang.Thread.currentThread;

/**
 * Created by Roman_Iovlev on 12/17/2015.
 */
public class TestScenarios {
    protected static void sendCVTest(String testName) {
        Attendee attendee = new Attendee(testName + " Thread: " + currentThread().getId());
        attendee.cv = null;
        EpamSiteParallel site = init(EpamSiteParallel.class, getDriverName());

        site.homePage.open();
        //site.isInState(HOME_PAGE);
        site.headerMenu.select(CAREERS);
        site.careerPage.checkOpened();
        site.careerPage.jobFilter.search(attendee.filter);

        site.jobListingPage.checkOpened();
        new Check("Table is not empty").isFalse(site.jobListingPage.jobsList::isEmpty);
        site.jobListingPage.getJobRowByName("Senior QA Automation Engineer");

        site.jobDescriptionPage.addCVForm.submit(attendee);
        new Check("Captcha").contains(() -> site.jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
}
