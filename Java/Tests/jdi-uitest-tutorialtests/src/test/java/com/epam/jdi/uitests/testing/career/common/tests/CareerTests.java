package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.uitests.testing.career.page_objects.dataProviders.AttendeesProvider;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.SOLUTIONS;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.*;


public class CareerTests extends TestsBase {

    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class)
    public void sendCVTest(Attendee attendee) {
        homePage.isOpened();
        multipleHeaderMenu.hoverAndClick("SOLUTIONS|Product Development");
        productDevelopmentPage.checkOpened();
        headerMenu.select(CAREERS);
        careerPage.checkOpened();
        careerPage.jobFilter.search(attendee.filter);
        jobListingPage.checkOpened();
        new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.getJobRowByName("Senior QA Automation Engineer");
        jobDescriptionPage.addCVForm.submit(attendee);
        new Check("Captcha").contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
}
