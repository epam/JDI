package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.testing.career.page_objects.dataProviders.AttendeeProvider;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.web.matcher.testng.Assert;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.SOLUTIONS;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.JobListHeaders.APPLY;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.JobListHeaders.JOB_NAME;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.*;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Column.column;


public class SimpleTests extends TestsBase {

    @Test(dataProvider = "attendees", dataProviderClass = AttendeeProvider.class)
    public void fillFormExample(Attendee attendee) {
        // Given
        jobDescriptionPage.isOpened();
        // When
        jobDescriptionPage.addCVForm.submit(attendee);
        // Then
        Assert.contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }

    @Test
    public void searchInTableExample() {
        // Given
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        // When
        jobListingPage.jobsList.row("Senior QA Automation Engineer", column("JOB_NAME"))
            .get("APPLY").select();
        // Then
        jobDescriptionPage.checkOpened();

        jobListingPage.jobsList.row("JOB_NAME=Senior QA Automation Engineer");
    }

    @Test
    public void searchByMultiCriteriaInTableExample() {
        // Given
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        // When
        MapArray<String, ICell> firstRow = jobListingPage.jobsList.rows(
                "JOB_NAME=Senior QA Automation Engineer",
                "JOB_CATEGORY=Software Test Engineering")
                .first().value;

        firstRow.get("APPLY").select();
        // Then
        jobDescriptionPage.checkOpened();
    }
}
