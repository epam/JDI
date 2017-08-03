package org.mytests.epam.site.testdata;

import org.mytests.epam.site.entities.Attendee;
import org.mytests.epam.site.entities.Job;
import org.testng.annotations.DataProvider;

public class CVData {
    @DataProvider(name = "cvData")
    public static Object[][] cvData() {
        return new Object[][]{
                { new Attendee(),
                        new Job("Test Automation Engineer (back-end)",
                                "Software Test Engineering")
                }
        };
    }
}


