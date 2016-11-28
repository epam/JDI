package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.uitests.testing.career.common.tests.TestsBase;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Job;
import com.epam.jdi.uitests.testing.career.page_objects.site.CustomElements.JobRecord;
import com.epam.web.matcher.testng.Assert;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.WithValue.withValue;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.jobDescriptionPage;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.jobListingPage;


public class EntityTableExamples extends TestsBase {
    @BeforeMethod
    public void before(Method method) {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
    }
    @Test
    public void getTableInfo() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        Assert.areEquals(jobListingPage.jobsListEntity.columns().size(), 4);
        Assert.areEquals(jobListingPage.jobsListEntity.rows().size(), 2);
        Assert.areEquals(jobListingPage.jobsListEntity.entities().size(), 2);
        Assert.areEquals(jobListingPage.jobsListEntity.getRows().size(), 2);
        Assert.areEquals(jobListingPage.jobsListEntity.getValue(),
            "||X||name|category|location|apply||\n" +
            "||1||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||Senior QA Automation Engineer|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }

    @Test
    public void searchInTable() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        jobListingPage.jobsListEntity
            .getRow(withValue("Senior QA Automation Engineer"), inColumn("name")).apply.click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void findEntity() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        Job job = jobListingPage.jobsListEntity
                .entity(withValue("Senior QA Automation Engineer"), inColumn("name"));

        Assert.entitiesAreEquals(job, new Job("Senior QA Automation Engineer", "Software Test Engineering", "St-Petersburg, Russia"));
    }
    @Test
    public void searchContainsInTable() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        jobListingPage.jobsListEntity
                .rowContains("Automation Engineer", inColumn("name")) //TODO
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        jobListingPage.jobsListEntity
                .rowMatch(".+ Automation Engineer", inColumn("name")) //TODO
                .get("apply").select();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        /*MapArray<String, ICell> firstRow = jobListingPage.jobsListEntity.getRow( //TODO
                "name~=Automation Engineer",
                "category*=.*Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("name").getText(), "Senior QA Automation Engineer");
        Assert.areEquals(firstRow.get("category").getText(), "Software Test Engineering");*/
    }

    @Test
    public void searchByMultiCriteriaInTableExample() {
        jobListingPage.isOpened();
        Assert.isFalse(jobListingPage.jobsListEntity::isEmpty);
        JobRecord firstRow = jobListingPage.jobsListEntity.getRows(
                "name=Senior QA Automation Engineer",
                "category=Software Test Engineering").get(0);

        Assert.areEquals(firstRow.name.getText(), "Senior QA Automation Engineer");
        Assert.areEquals(firstRow.category.getText(), "Software Test Engineering");
    }
}
