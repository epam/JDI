package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.entities.Job;
import com.epam.jdi.site.epam.CustomElements.JobRecord;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.jobDescriptionPage;
import static com.epam.jdi.site.epam.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.WithValue.withValue;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;


public class EntityTableExamples extends TestsBase {
    private EntityTable<Job, JobRecord> jobsTable() {
        return jobListingPage.jobsListEntity;
    }
    
    @BeforeMethod
    public void before(Method method) {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
    }
    @Test
    public void getTableInfo() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        Assert.areEquals(jobsTable().columns().size(), 4);
        Assert.areEquals(jobsTable().rows().size(), 4);
        Assert.areEquals(jobsTable().entities().size(), 4);
        Assert.areEquals(jobsTable().getRows().size(), 4);
        Assert.areEquals(jobsTable().getValue(),
    "||X||name|category|location|apply||\n" +
            "||1||Senior Software Testing Engineer|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||Software Test Automation Engineer (front-end)|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||3||Software Testing Engineer (Life Science Department)|Software Test Engineering|St-Petersburg, Russia|Apply||\n"+
            "||4||Test Automation Engineer (back-end)|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }

    @Test
    public void searchInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        jobsTable()
            .getRow(withValue("Test Automation Engineer (back-end)"), inColumn("name")).apply.click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void findEntity() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        Job job = jobsTable()
                .entity(withValue("Test Automation Engineer (back-end)"), inColumn("name"));
        Assert.entitiesAreEquals(job, new Job("Test Automation Engineer (back-end)", "Software Test Engineering", "St-Petersburg, Russia"));
    }
    @Test
    public void searchContainsInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        jobsTable()
                .rowContains("back-end", inColumn("name")) //TODO
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        jobsTable()
                .rowMatch(".+back-end.*", inColumn("name")) //TODO
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        /*MapArray<String, ICell> firstRow = jobsTable().getRow( //TODO
                "name~=Automation Engineer",
                "category*=.*Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "QA Specialist");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");*/
    }

    @Test
    public void searchByMultiCriteriaInTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        JobRecord firstRow = jobsTable().firstRow(r ->
                textOf(r.name).equals("Test Automation Engineer (back-end)") &&
                textOf(r.category).equals("Software Test Engineering"));

        Assert.areEquals(firstRow.name.getText(), "Test Automation Engineer (back-end)");
        Assert.areEquals(firstRow.category.getText(), "Software Test Engineering");
    }

    @Test
    public void complexTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
        JobRecord firstRow = jobsTable().firstRow(r ->
            r.name.getText().equals("Test Automation Engineer (back-end)") &&
            r.category.getText().equals("Software Test Engineering"));
        firstRow.apply.click();
    }
}
