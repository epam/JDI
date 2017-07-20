package org.mytests.tests;

import com.epam.web.matcher.testng.Assert;
import org.mytests.InitTestsTableForm;
import org.mytests.epam.site.entities.Attendee;
import org.mytests.epam.site.entities.Job;
import org.mytests.epam.site.testdata.CVData;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static org.mytests.epam.site.site.EpamSite.jobDescriptionPage;
import static org.mytests.epam.site.site.EpamSite.jobsPage;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class TableFormTests extends InitTestsTableForm {

    //It was so.  before 29/06/2017
    //private static BinaryOperator<Netapi32Util.User> op;


    //It was so. before 29/06/2017
    //Test takes two parameters but the dataprovider has three

/*
    @DataProvider
    public static Object[][] cvData() {
        return new Object[][]{
                { new Attendee(),
                        new Job("QA Specialist",
                                "Software Test Engineering"),
                        op }
        };
    }*/


    @Test(dataProviderClass=CVData.class, dataProvider = "cvData")
    public void tableFormTest(Attendee attendee, Job job) {
        jobsPage.open();
        jobsPage.jobs.firstRow(r ->
            textOf(r.name).equals(job.name) &&
            textOf(r.category).equals(job.category))
            .apply.click();
        jobDescriptionPage.addCVForm.submit(attendee);
        jobDescriptionPage.addCVForm.check(attendee);
        Assert.isTrue(jobDescriptionPage.addCVForm.verify(attendee).size() != 0);
        Assert.entitiesAreEquals(
            jobDescriptionPage.addCVForm.getEntity(),
            attendee);

    }
}
