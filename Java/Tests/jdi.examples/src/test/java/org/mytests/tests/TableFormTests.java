package org.mytests.tests;

import org.mytests.InitTestsTableForm;
import org.mytests.epam.entities.Attendee;
import org.mytests.epam.entities.Job;
import org.mytests.epam.selenide.PageJobs;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static org.mytests.epam.selenide.PageJobDescription.pageJobDescription;
import static org.mytests.epam.site.EpamSite.jobDescriptionPage;
import static org.mytests.epam.site.EpamSite.jobsPage;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class TableFormTests extends InitTestsTableForm {

    @DataProvider
    public static Object[][] cvData() {
        return new Object[][]{
            { new Attendee(),
              new Job("Senior QA Automation Engineer",
                    "Software Test Engineering")}
        };
    }

    @Test(dataProvider = "cvData")
    public void tableFormTest(Attendee attendee, Job job) {
        jobsPage.open();
        jobsPage.jobs.firstRow(r ->
            textOf(r.name).equals(job.name) &&
            textOf(r.category).equals(job.category))
            .apply.click();
        jobDescriptionPage.addCVForm.submit(attendee);
        jobDescriptionPage.addCVForm.check(attendee);
        //Assert.isTrue(jobDescriptionPage.addCVForm.verify(attendee).size() == 0);
        /*Assert.entitiesAreEquals(
            jobDescriptionPage.addCVForm.getEntity(),
            attendee);*/

    }

    @Test(dataProvider = "cvData")
    public void selenideTest(Attendee attendee, Job job) {
        System.setProperty("webdriver.gecko.driver", "D:\\Work\\Projects\\Java\\JDI\\Github\\Java\\Tests\\jdi.examples\\src\\main\\resources\\driver\\geckodriver.exe");
        open(PageJobs.url);
        PageJobs.applyLinkFor(job.name, job.category).click();
        pageJobDescription.submitForm(attendee);
        pageJobDescription.verifyCVForm(attendee);
    }


}
