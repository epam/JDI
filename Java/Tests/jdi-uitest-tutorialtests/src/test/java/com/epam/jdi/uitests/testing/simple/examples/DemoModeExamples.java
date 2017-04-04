package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.dataProviders.AttendeesProvider;
import com.epam.jdi.entities.Attendee;
import com.epam.jdi.uitests.testing.TestsBase;
import com.epam.jdi.uitests.testing.career.common.tests.CareerTests;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.site.epam.EpamSite.homePage;


public class DemoModeExamples extends TestsBase {
    @BeforeMethod
    public void before(Method method) throws IOException {
        homePage.shouldBeOpened();
    }

    //TODO in progress
    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class, enabled = false)
    public void sendCVTest(Attendee attendee) {
        WebSettings.isDemoMode = true;      // you can also switch on demo mode in test.properties
        new CareerTests().sendCVTest(attendee);
    }
}
