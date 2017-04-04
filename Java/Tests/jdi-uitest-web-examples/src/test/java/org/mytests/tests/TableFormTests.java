package org.mytests.tests;

import com.sun.jna.platform.win32.Netapi32Util;
import org.mytests.InitTestsTableForm;
import org.mytests.epam.site.entities.Attendee;
import org.mytests.epam.site.entities.Job;
import org.mytests.epam.site.selenide.PageJobs;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.BinaryOperator;

import static com.codeborne.selenide.Selenide.open;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static org.mytests.epam.site.selenide.PageJobDescription.pageJobDescription;
import static org.mytests.epam.site.site.EpamSite.jobDescriptionPage;
import static org.mytests.epam.site.site.EpamSite.jobsPage;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class TableFormTests extends InitTestsTableForm {

    private static BinaryOperator<Netapi32Util.User> op;

    @DataProvider
    public static Object[][] cvData() {
        return new Object[][]{
            { new Attendee(),
              new Job("QA Specialist",
                    "Software Test Engineering"),
                    op }
        };
    }
    // JDI test
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

    // Selenide looks like tests written on JDI using J SelenideElement wrapper
    @Test(dataProvider = "cvData")
    public void selenideTest(Attendee attendee, Job job) {
        System.setProperty("webdriver.gecko.driver", "D:\\Work\\Projects\\Java\\JDI\\Github\\Java\\Tests\\jdi-uitest-web-examples\\src\\main\\resources\\driver\\geckodriver.exe");
        open(PageJobs.url); // download and put geckodriver because Selenide not support driver auto loading
        PageJobs.applyLinkFor(job.name, job.category).click();
        pageJobDescription.submitForm(attendee);
        pageJobDescription.verifyCVForm(attendee);
    }


}
