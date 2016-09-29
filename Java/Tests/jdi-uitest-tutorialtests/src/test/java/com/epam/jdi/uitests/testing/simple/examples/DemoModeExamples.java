package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.uitests.testing.career.common.tests.CareerTests;
import com.epam.jdi.uitests.testing.career.common.tests.TestsBase;
import com.epam.jdi.uitests.testing.career.page_objects.dataProviders.AttendeesProvider;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.testing.career.page_objects.site.EpamSite.*;


public class DemoModeExamples extends TestsBase {
    @BeforeMethod
    public void before(Method method) throws IOException {
        homePage.isOpened();
    }

    //TODO in progress
    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class, enabled = false)
    public void sendCVTest(Attendee attendee) {
        WebSettings.isDemoMode = true;      // you can also switch on demo mode in test.properties
        new CareerTests().sendCVTest(attendee);
    }
}
