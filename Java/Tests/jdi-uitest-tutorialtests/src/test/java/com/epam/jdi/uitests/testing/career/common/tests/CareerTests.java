package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.jdi.dataProviders.AttendeesProvider;
import com.epam.jdi.entities.Attendee;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.site.epam.EpamSite.*;


public class CareerTests extends TestsBase {

    @BeforeMethod
    public void before(Method method) {
        homePage.shouldBeOpened();
    }

    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class)
    public void sendCVTest(Attendee attendee) {
        multipleHeaderMenu.hoverAndClick("SOLUTIONS|Product Development");
        productDevelopmentPage.checkOpened();
        headerMenu.select(CAREERS);
        careerPage.checkOpened();
        careerPage.jobFilter.search(attendee.filter);
        jobListingPage.checkOpened();
        new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.getJobRowByName("QA Specialist");
        jobDescriptionPage.addCVForm.submit(attendee);
        new Check("Captcha class contains 'form-field-error'")
            .contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");

    }
}
