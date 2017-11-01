package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITable;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.jobDescriptionPage;
import static com.epam.jdi.site.epam.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.WithValue.withValue;


public class TableExamples extends TestsBase {
    private ITable jobsTable() {
        return jobListingPage.jobsList;
    }
    
    @BeforeMethod
    public void before(Method method) {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
    }
    @Test
    public void getTableInfo() {
        Assert.isFalse(jobsTable()::isEmpty);
        Assert.areEquals(jobsTable().columns().count(), 4);
        Assert.areEquals(jobsTable().rows().count(), 4);
        Assert.areEquals(jobsTable().getValue(),
    "||X||name|category|location|apply||\n" +
            "||1||Senior Software Testing Engineer|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||Software Test Automation Engineer (front-end)|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||3||Software Testing Engineer (Life Science Department)|Software Test Engineering|St-Petersburg, Russia|Apply||\n"+
            "||4||Test Automation Engineer (back-end)|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }

    @Test
    public void searchInTable() {
        Assert.isFalse(jobsTable()::isEmpty);
        jobsTable()
            .row(withValue("Test Automation Engineer (back-end)"), inColumn("name"))
            .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsInTable() {
        Assert.isFalse(jobsTable()::isEmpty);
        jobsTable()
            .rowContains("front-end", inColumn("name"))
            .get("apply").select();
        /*jobsTable()
                .rowContains("front-end", inColumn("name"))
                .get("apply").select();*/
        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTable() {
        Assert.isFalse(jobsTable()::isEmpty);
        jobsTable()
                .rowMatch(".+front-end.*", inColumn("name"))
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTable() {
        Assert.isFalse(jobsTable()::isEmpty);
        MapArray<String, ICell> firstRow = jobsTable().rows(
                "name~=front-end",
                "category*=.*Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "Software Test Automation Engineer (front-end)");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");
    }

    @Test
    public void searchByMultiCriteriaInTable() {
        Assert.isFalse(jobsTable()::isEmpty);
        MapArray<String, ICell> firstRow = jobsTable().rows(
                "name=Test Automation Engineer (back-end)",
                "category=Software Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "Test Automation Engineer (back-end)");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");
    }
}
