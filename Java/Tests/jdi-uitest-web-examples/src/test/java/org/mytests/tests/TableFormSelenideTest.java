package org.mytests.tests;

import com.codeborne.selenide.Configuration;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.mytests.InitTestsTableForm;
import org.mytests.epam.site.entities.Attendee;
import org.mytests.epam.site.entities.Job;
import org.mytests.epam.site.selenide.PageJobs;
import org.mytests.epam.site.testdata.CVData;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.mytests.epam.site.selenide.PageJobDescription.pageJobDescription;

public class TableFormSelenideTest extends InitTestsTableForm {

    // Selenide looks like tests written on JDI using J SelenideElement wrapper
    @Test(dataProviderClass=CVData.class, dataProvider = "cvData")
    public void selenideTest(Attendee attendee, Job job) throws IOException {

        //System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
        Configuration.browser="chrome";

        open(PageJobs.url);
        PageJobs.applyLinkFor(job.name, job.category).click();

        WebSettings.useDriver(()->getWebDriver());

        pageJobDescription.submitForm(attendee);
        pageJobDescription.verifyCVForm(attendee);

        WebSettings.initFromProperties();
        }

      }