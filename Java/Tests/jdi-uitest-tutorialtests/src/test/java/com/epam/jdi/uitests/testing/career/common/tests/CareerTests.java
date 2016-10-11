package com.epam.jdi.uitests.testing.career.common.tests;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.testing.career.page_objects.dataProviders.AttendeesProvider;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.settings.WebSettings;
import com.epam.web.matcher.testng.Check;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.commons.Timer.nowTime;
import static com.epam.jdi.uitests.core.settings.JDIData.testName;
import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.SOLUTIONS;
import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.*;
import static org.openqa.selenium.OutputType.FILE;


public class CareerTests extends TestsBase {

    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class, parameters = {})
    public void sendCVTest(Attendee attendee) {
        multipleHeaderMenu.hoverAndClick(SOLUTIONS + "|" + "Product Development");
        productDevelopmentPage.checkOpened();
        headerMenu.select(CAREERS);
        careerPage.checkOpened();
        careerPage.jobFilter.search(attendee.filter);
        jobListingPage.checkOpened();
        new Check("Table is not empty").isFalse(jobListingPage.jobsList::isEmpty);
        jobListingPage.getJobRowByName("Senior QA Automation Engineer");
        jobDescriptionPage.addCVForm.submit(attendee);
        new Check("Captcha").contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
}
