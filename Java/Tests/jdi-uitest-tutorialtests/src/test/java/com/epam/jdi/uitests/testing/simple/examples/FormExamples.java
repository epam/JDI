package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.uitests.testing.career.common.tests.TestsBase;
import com.epam.jdi.uitests.testing.career.page_objects.dataProviders.AttendeesProvider;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.jobDescriptionPage;


public class FormExamples extends TestsBase {

    @BeforeMethod
    public void before(Method method) throws IOException {
        jobDescriptionPage.isOpened();
    }

    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class)
    public void fillForm(Attendee attendee) {
        jobDescriptionPage.addCVForm.submit(attendee);
        Assert.contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
}
