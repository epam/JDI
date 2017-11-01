package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.dataProviders.AttendeesProvider;
import com.epam.jdi.entities.Attendee;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.jobDescriptionPage;


public class FormExamples extends TestsBase {

    @BeforeMethod
    public void before(Method method) throws IOException {
        jobDescriptionPage.shouldBeOpened();
    }
    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class)
    public void fillForm(Attendee attendee) {
        jobDescriptionPage.addCVForm.submit(attendee);
        Assert.contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
}
