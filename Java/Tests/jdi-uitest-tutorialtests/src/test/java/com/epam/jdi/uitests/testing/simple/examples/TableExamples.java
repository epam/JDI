package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.jobDescriptionPage;
import static com.epam.jdi.site.epam.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.WithValue.withValue;


public class TableExamples extends TestsBase {
    @BeforeMethod
    public void before(Method method) {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
    }
    @Test
    public void getTableInfo() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        Assert.areEquals(jobListingPage.jobsList.columns().count(), 4);
        Assert.areEquals(jobListingPage.jobsList.rows().count(), 2);
        Assert.areEquals(jobListingPage.jobsList.getValue(),
            "||X||name|category|location|apply||\n" +
            "||1||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }

    @Test
    public void searchInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.jobsList
            .row(withValue("QA Specialist"), inColumn("name"))
            .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.jobsList
                .rowContains("Automation Engineer", inColumn("name"))
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.jobsList
                .rowMatch(".+ Automation Engineer", inColumn("name"))
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        MapArray<String, ICell> firstRow = jobListingPage.jobsList.rows(
                "name~=Automation Engineer",
                "category*=.*Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "QA Specialist");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");
    }

    @Test
    public void searchByMultiCriteriaInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsList::isEmpty);
        MapArray<String, ICell> firstRow = jobListingPage.jobsList.rows(
                "name=QA Specialist",
                "category=Software Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "QA Specialist");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");
    }
}
