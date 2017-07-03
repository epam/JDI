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
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.FilterDsl.textOf;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;
import static org.mytests.epam.site.selenide.PageJobDescription.pageJobDescription;
import static org.mytests.epam.site.site.EpamSite.jobDescriptionPage;
import static org.mytests.epam.site.site.EpamSite.jobsPage;

/**
 * Created by Alexander_Petrovskiy on 5/23/2016.
 */
public class TableFormTests extends InitTestsTableForm {

    //It was so. Commented 29/06/2017
    //private static BinaryOperator<Netapi32Util.User> op;


    //It was so. Commented 29/06/2017
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


    @DataProvider
    public static Object[][] cvData() {
        return new Object[][]{
            { new Attendee(),
              new Job("QA Specialist",
                    "Software Test Engineering"),
                   }
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
        Assert.isTrue(jobDescriptionPage.addCVForm.verify(attendee).size() == 0);
        Assert.entitiesAreEquals(
            jobDescriptionPage.addCVForm.getEntity(),
            attendee);

    }

    // Selenide looks like tests written on JDI using J SelenideElement wrapper
    @Test(dataProvider = "cvData")
    public void selenideTest(Attendee attendee, Job job) {

        /*System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver.exe");
        Configuration.browser="firefox";*/
        /*System.setProperty("phantomjs.binary.path", "C:\\Selenium\\phantomjs.exe");
        Configuration.browser="phantomjs";*/

        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        Configuration.browser="chrome";


        open(PageJobs.url); // download and put geckodriver because Selenide not support driver auto loading
        PageJobs.applyLinkFor(job.name, job.category).click();

        getDriver().get(url());


       pageJobDescription.submitForm(attendee);
       pageJobDescription.verifyCVForm(attendee);
    }


}
