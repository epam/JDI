package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.entities.Job;
import com.epam.jdi.site.epam.CustomElements.JobRecord;
import com.epam.jdi.uitests.testing.TestsBase;
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
    @BeforeMethod
    public void before(Method method) {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
    }
    @Test
    public void getTableInfo() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        Assert.areEquals(jobListingPage.jobsListEntity.columns().size(), 4);
        Assert.areEquals(jobListingPage.jobsListEntity.rows().size(), 2);
        Assert.areEquals(jobListingPage.jobsListEntity.entities().size(), 2);
        Assert.areEquals(jobListingPage.jobsListEntity.getRows().size(), 2);
        Assert.areEquals(jobListingPage.jobsListEntity.getValue(),
            "||X||name|category|location|apply||\n" +
            "||1||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }

    @Test
    public void searchInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        jobListingPage.jobsListEntity
            .getRow(withValue("QA Specialist"), inColumn("name")).apply.click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void findEntity() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        Job job = jobListingPage.jobsListEntity
                .entity(withValue("QA Specialist"), inColumn("name"));

        Assert.entitiesAreEquals(job, new Job("QA Specialist", "Software Test Engineering", "St-Petersburg, Russia"));
    }
    @Test
    public void searchContainsInTable() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        jobListingPage.jobsListEntity
                .rowContains("Automation Engineer", inColumn("name")) //TODO
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        jobListingPage.jobsListEntity
                .rowMatch(".+ Automation Engineer", inColumn("name")) //TODO
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        /*MapArray<String, ICell> firstRow = jobListingPage.jobsListEntity.getRow( //TODO
                "name~=Automation Engineer",
                "category*=.*Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "QA Specialist");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");*/
    }

    @Test
    public void searchByMultiCriteriaInTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        JobRecord firstRow = jobListingPage.jobsListEntity.firstRow(r ->
                textOf(r.name).equals("QA Specialist") &&
                textOf(r.category).equals("Software Test Engineering"));

        Assert.areEquals(firstRow.name.getText(), "QA Specialist");
        Assert.areEquals(firstRow.category.getText(), "Software Test Engineering");
    }

    @Test
    public void complexTableExample() {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        JobRecord firstRow = jobListingPage.jobsListEntity.firstRow(r ->
                r.name.getText().equals("QA Specialist") &&
                r.category.getText().equals("Software Test Engineering"));
        firstRow.apply.click();
    }
}
