package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.entities.Vacancy;
import com.epam.jdi.site.epam.sections.ApplyShareCell;
import com.epam.jdi.site.epam.sections.VacancyRow;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static com.epam.jdi.site.epam.EpamSite.*;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.Column.inColumn;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.WithValue.withValue;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static com.epam.web.matcher.testng.Assert.areEquals;

public class EntityTableExamples extends TestsBase {
  @BeforeMethod
  public void before(Method method) {

    acceptCookiePopup.shouldNotBeDisplayed();
    jobListingPage.shouldBeOpened();

    Assert.isFalse(jobsTable()::isEmpty);
  }

private EntityTable<Vacancy, VacancyRow> jobsTable() {
    return jobListingPage.jobsAsData;
  }

  @Test
  public void getTableInfo() {


    areEquals(jobsTable().columns().size(), 3);
    areEquals(jobsTable().rows().size(), 1);
    areEquals(jobsTable().entities().size(), 1);
    areEquals(jobsTable().getRows().size(), 1);
    areEquals(
        jobsTable().getValue(),
        "||X||name|description|applyShareCell||\n||1||Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA|Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.|APPLY\nSHARE||");
  }

  @Test
  public void searchInTable() {

    EntityTable<Vacancy, VacancyRow> vacancies = jobsTable();
    VacancyRow castedRow =
        vacancies.getRow(
            withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"),
            inColumn("name"));

    ApplyShareCell cell = castedRow.applyShareCell;
    cell.apply.click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void findEntity() {
    EntityTable<Vacancy, VacancyRow> vacancies = jobsTable();

    List<VacancyRow> rows = vacancies.getRows();
    Vacancy vacancy =
        vacancies.entity(
            withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"),
            inColumn("name"));

    Assert.entitiesAreEquals(
        vacancy,
        new Vacancy(
            "Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA",
            "Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger."));

    System.out.println(vacancy);
  }

  @Test
  public void searchContainsInTable() {
    jobsTable().firstRow(r -> textOf(r.name).contains("back-end")).applyShareCell.apply.click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void searchMatchInTableExample() {
     jobsTable().firstRow(r -> textOf(r.name).matches(".*back-end.*\n.*")).applyShareCell.apply.click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void searchContainsListInTableExample() {
    VacancyRow firstRow =
        jobsTable()
            .firstRow(
                r ->
                    textOf(r.name).contains("Automation Engineer")
                        && textOf(r.description).matches(".*back-end.*"));

    areEquals(
        firstRow.name.getText(), "Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA");
    areEquals(
        firstRow.description.getText(),
        "Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.");
  }

  @Test
  public void complexTableExample() {
        jobsTable()
            .firstRow(
                r ->
                    textOf(r.name).contains("Automation Engineer")
                        && textOf(r.description).matches(".*back-end.*")).applyShareCell.apply.click();

    jobDescriptionPage.checkOpened();
  }
}
