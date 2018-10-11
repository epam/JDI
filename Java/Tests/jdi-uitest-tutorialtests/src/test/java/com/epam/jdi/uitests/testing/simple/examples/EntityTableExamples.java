package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.entities.Attendee;
import com.epam.jdi.entities.Vacancy;
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
    /*     headerMenu.select(CAREERS);
    careerPage.jobFilter.search(new Attendee().filter);

    jobListingPage.getJobRowByName("Test Automation Engineer (back-end)");*/

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
        "||X||name|description|apply||\n||1||Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA|Currently we are looking for a Test Automation Engineer (back-end) for our St. Petersburg office to make the team even stronger.|APPLY\nSHARE||");
  }

  @Test
  public void searchInTable() {

   /* VacancyRow vRow = jobListingPage.getRowsByName("Test Automation Engineer (back-end)").get(0);
    System.out.println(vRow);



    */
    EntityTable<Vacancy, VacancyRow> vacancies = jobsTable();
    VacancyRow castedRow =
        vacancies.getRow(
            withValue("Test Automation Engineer (back-end)\nST-PETERSBURG, RUSSIA"),
            inColumn("name"));
    System.out.println();
    System.out.println();


    System.out.println(castedRow);
   // vRow.apply.click();
    castedRow.apply.click();

    /*      List<VacancyRow> result = where(jobsList, job -> job.name.getText().equals(jobName));
    result.get(0).apply.click();*/
  //  jobDescriptionPage.checkOpened();
  }

  @Test
  public void findEntity() {
    EntityTable<Vacancy, VacancyRow> vacancies = jobsTable();

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
    jobsTable().firstRow(r -> textOf(r.name).contains("back-end")).apply.click();

    jobDescriptionPage.checkOpened();
  }

  @Test
  public void searchMatchInTableExample() {
    jobsTable().firstRow(r -> textOf(r.name).matches(".*back-end.*\n.*")).apply.click();

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
    VacancyRow firstRow =
        jobsTable()
            .firstRow(
                r ->
                    textOf(r.name).contains("Automation Engineer")
                        && textOf(r.description).matches(".*back-end.*"));
    firstRow.apply.click();

    jobDescriptionPage.checkOpened();
  }
}
