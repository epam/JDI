package com.epam.jdi.uitests.testing.career.common.tests.simple.examples;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.testing.career.common.tests.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.jobDescriptionPage;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Column.inColumn;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.WithValue.withValue;


public class TableExamples extends TestsBase {
    @BeforeMethod
    public void before(Method method) throws IOException {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
    }
    @Test
    public void getTableInfoExample() {
        Assert.areEquals(jobListingPage.jobsList.columns().count(), 4);
        Assert.areEquals(jobListingPage.jobsList.rows().count(), 2);
        Assert.areEquals(jobListingPage.jobsList.getValue(),
            "||X||JOB_NAME|JOB_CATEGORY|JOB_LOCATION|APPLY||\n" +
            "||1||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||Senior QA Automation Engineer|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }

    @Test
    public void searchInTableExample() {
        jobListingPage.jobsList
            .row(withValue("Senior QA Automation Engineer"), inColumn("JOB_NAME"))
            .get("APPLY").select();

        jobDescriptionPage.checkOpened();
    }

    @Test
    public void searchByMultiCriteriaInTableExample() {
        MapArray<String, ICell> firstRow = jobListingPage.jobsList.rows(
                "JOB_NAME=Senior QA Automation Engineer",
                "JOB_CATEGORY=Software Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("JOB_LOCATION").getText(), "St-Petersburg, Russia");
    }
}
