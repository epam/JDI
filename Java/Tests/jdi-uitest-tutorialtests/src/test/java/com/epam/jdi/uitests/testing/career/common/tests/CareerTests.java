package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.dataProviders.AttendeesProvider;
import com.epam.jdi.entities.Attendee;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import static com.epam.jdi.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.site.epam.EpamSite.*;


public class CareerTests extends TestsBase {

    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class)
    public void sendCVTest(Attendee attendee) {
        multipleHeaderMenu.hoverAndClick("SOLUTIONS|Product Development");
        productDevelopmentPage.checkOpened();
        headerMenu.select(CAREERS);
        careerPage.checkOpened();
        careerPage.jobFilter.search(attendee.filter);
        jobListingPage.checkOpened();
        new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.getJobRowByName("Test Automation Engineer (back-end)");
        jobDescriptionPage.addCVForm.submit(attendee);
        new Check("Captcha class contains 'form-field-error'")
            .contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
}
