package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.entities.Vacancy;
import com.epam.jdi.site.epam.sections.VacancyRow;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.jobDescriptionPage;
import static com.epam.jdi.site.epam.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.WithValue.withValue;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static com.epam.web.matcher.testng.Assert.areEquals;


public class EntityTableExamples extends TestsBase {
    private EntityTable<Vacancy, VacancyRow> jobsTable() {
        return jobListingPage.jobsAsData;
    }

    @BeforeMethod
    public void before(Method method) {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(jobsTable()::isEmpty);
    }
    @Test
    public void getTableInfo() {
        areEquals(jobsTable().columns().size(), 3);
        areEquals(jobsTable().rows().size(), 2);
        areEquals(jobsTable().entities().size(), 2);
        areEquals(jobsTable().getRows().size(), 2);
        areEquals(jobsTable().getValue(),
    "||X||name|description|apply||\n" +
            "||1||Test Automation Engineer (back-end)\n" +
            "ST-PETERSBURG, RUSSIA|Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.|APPLY||\n" +
            "||2||Software Test Automation Engineer (front-end)\n" +
            "ST-PETERSBURG, RUSSIA|Currently we are looking for a Software Test Automation Engineer (front-end) for our St. Petersburg office to make the team even stronger.|APPLY||");
    }

    @Test
    public void searchInTable() {
        jobsTable().getRow(withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"),
            inColumn("name")).apply.click();
        jobDescriptionPage.checkOpened();
    }
    @Test
    public void findEntity() {
        Vacancy vacancy = jobsTable()
            .entity(withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"), inColumn("name"));
        Assert.entitiesAreEquals(vacancy, new Vacancy("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA", "Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger."));
    }
    @Test
    public void searchContainsInTable() {
        jobsTable().firstRow(r->textOf(r.name).contains("back-end"))
                .apply.click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTableExample() {
        jobsTable().firstRow(r->textOf(r.name).matches(".*back-end.*\n.*"))
                .apply.click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTableExample() {
        VacancyRow firstRow = jobsTable().firstRow(r->
            textOf(r.name).contains("Automation Engineer") &&
            textOf(r.description).matches(".*back-end.*"));

        areEquals(firstRow.name.getText(), "Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA");
        areEquals(firstRow.description.getText(), "Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.");
    }

    @Test
    public void complexTableExample() {
        VacancyRow firstRow = jobsTable().firstRow(r->
            textOf(r.name).contains("Automation Engineer") &&
            textOf(r.description).matches(".*back-end.*"));
        firstRow.apply.click();

        jobDescriptionPage.checkOpened();
    }
}
