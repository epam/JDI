package org.mytests.tests;

import com.codeborne.selenide.Configuration;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.web.matcher.testng.Assert;
import com.sun.jna.platform.win32.Netapi32Util;
import org.mytests.InitTestsTableForm;
import org.mytests.epam.site.entities.Attendee;
import org.mytests.epam.site.entities.Job;
import org.mytests.epam.site.selenide.PageJobs;
import org.mytests.epam.site.site.EpamSite;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.function.BinaryOperator;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;
import static org.mytests.epam.site.selenide.PageJobDescription.pageJobDescription;
import static org.mytests.epam.site.site.EpamSite.jobDescriptionPage;
import static org.mytests.epam.site.site.EpamSite.jobsPage;
import org.mytests.epam.site.testdata.CVData;

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
