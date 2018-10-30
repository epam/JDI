package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.commons.map.MapArray;
import com.epam.jdi.site.epam.sections.ApplyShareCell;
import com.epam.jdi.site.epam.sections.JobTitle;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.*;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Row.inRow;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.WithValue.withValue;
import static com.epam.web.matcher.testng.Assert.areEquals;

public class TableExamples extends TestsBase {
  @BeforeMethod
  public void before(Method method) {
    acceptCookiePopup.shouldNotBeDisplayed();
    jobListingPage.shouldBeOpened();
    Assert.isFalse(table()::isEmpty);
  }

  private Table table() {
    return jobListingPage.jobs;
  }

  @Test
  public void getTableInfo() {

    areEquals(table().columns().size(), 3);
    areEquals(table().rows().size(), 1);
    areEquals(
        table().getValue(),
        "||X||name|description|applyShareCell||\n||1||Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA|Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.|APPLY\nSHARE||");
  }

  @Test
  public void searchInTable() {
    table()
        .row(
            withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"),
            inColumn("name"))
        .get("applyShareCell")
        .get(ApplyShareCell.class)
        .apply
        .click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void findEntity() {
    MapArray<String, String> valueInColumn =
        new MapArray<>(
            table().column(withValue("APPLY\nSHARE"), inRow("1")),
            p -> p.key,
            p -> p.value.getText());
    Assert.areEquals(valueInColumn.size(), 1);
  }

  @Test
  public void searchContainsInTable() {
    table()
        .rows("name~=back-end")
        .get(0)
        .value
        .get("applyShareCell")
        .get(ApplyShareCell.class)
        .apply
        .click();
    jobDescriptionPage.checkOpened();
  }

  @Test
  public void searchMatchInTableExample() {
    table()
        .rows("name*=.*back-end.*\n.*")
        .get(0)
        .value
        .get("applyShareCell")
        .get(ApplyShareCell.class)
        .apply
        .click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void searchContainsListInTableExample() {
    MapArray<String, ICell> firstRow =
        table().rows("name~=Automation Engineer", "description*=.*back-end.*").get(0).value;

    areEquals(
        firstRow.get("name").getText(),
        "Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA");
    areEquals(
        firstRow.get("description").getText(),
        "Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.");
  }

  @Test
  public void complexTableExample() {
    MapArray<String, ICell> firstRow =
        table().rows("name~=Automation Engineer", "description*=.*back-end.*").get(0).value;
    firstRow.get("applyShareCell").get(ApplyShareCell.class).apply.click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void cellTestSection() {
    ICell cell = table().cell(1, 1);
    JobTitle jobTitle = cell.get(JobTitle.class);
    Assert.areEquals(jobTitle.name.getText(), "Test Automation Engineer (back-end)");
    Assert.areEquals(jobTitle.location.getText(), "ST-PETERSBURG, RUSSIA");
  }
}
