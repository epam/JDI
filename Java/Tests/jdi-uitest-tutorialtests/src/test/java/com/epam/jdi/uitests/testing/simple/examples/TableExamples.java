package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.commons.map.MapArray;
import com.epam.jdi.site.epam.sections.JobTitle;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.jobDescriptionPage;
import static com.epam.jdi.site.epam.EpamSite.jobListingPage;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Row.inRow;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.WithValue.withValue;
import static com.epam.web.matcher.testng.Assert.areEquals;


public class TableExamples extends TestsBase {
    private Table table() {
        return jobListingPage.jobs;
    }

    @BeforeMethod
    public void before(Method method) {
        jobListingPage.shouldBeOpened();
        Assert.isFalse(table()::isEmpty);
    }
    @Test
    public void getTableInfo() {
        areEquals(table().columns().size(), 3);
        areEquals(table().rows().size(), 2);
        areEquals(table().getValue(),
    "||X||name|description|apply||\n" +
            "||1||Test Automation Engineer (back-end)\n" +
            "ST-PETERSBURG, RUSSIA|Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.|APPLY||\n" +
            "||2||Software Test Automation Engineer (front-end)\n" +
            "ST-PETERSBURG, RUSSIA|Currently we are looking for a Software Test Automation Engineer (front-end) for our St. Petersburg office to make the team even stronger.|APPLY||");
    }

    @Test
    public void searchInTable() {
        table().row(withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"),
            inColumn("name")).get("apply").click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void findEntity() {
        MapArray<String, String> column =
            new MapArray<>(table().column(withValue("APPLY"), inRow("1")),
                p -> p.key, p -> p.value.getText());
        Assert.areEquals(column.size(), 2);
    }
    @Test
    public void searchContainsInTable() {
        table().rows("name~=back-end")
            .get(0).value.get("apply").click();
        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchMatchInTableExample() {
        table().rows("name*=.*back-end.*\n.*")
            .get(0).value.get("apply").click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void searchContainsListInTableExample() {
        MapArray<String, ICell> firstRow =
                table().rows("name~=Automation Engineer",
                                          "description*=.*back-end.*").get(0).value;

        areEquals(firstRow.get("name").getText(), "Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA");
        areEquals(firstRow.get("description").getText(), "Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.");
    }

    @Test
    public void complexTableExample() {
        MapArray<String, ICell> firstRow =
                table().rows("name~=Automation Engineer",
                        "description*=.*back-end.*").get(0).value;
        firstRow.get("apply").click();

        jobDescriptionPage.checkOpened();
    }
    @Test
    public void cellTest() {
        ICell cell = table().cell(1,1);
        Assert.areEquals(cell.getText(), "Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA");
        table().cell("apply",1).click();
        jobDescriptionPage.checkOpened();
    }
    @Test
    public void cellTestSection() {
        ICell cell = table().cell(1,1);
        JobTitle jobTitle = cell.get(JobTitle.class);
        Assert.areEquals(jobTitle.name.getText(), "Test Automation Engineer (back-end)");
        Assert.areEquals(jobTitle.location.getText(), "ST-PETERSBURG, RUSSIA");
    }
}
