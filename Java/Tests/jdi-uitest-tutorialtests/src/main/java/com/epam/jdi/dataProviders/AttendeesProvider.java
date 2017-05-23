package com.epam.jdi.dataProviders;

import com.epam.jdi.entities.Attendee;
import org.testng.annotations.DataProvider;


/**
 * Created by Roman_Iovlev on 9/2/2015.
 */
public final class AttendeesProvider {
    private AttendeesProvider() { }

    @DataProvider(name = "attendees")
    public static Object[][] attendees() {
        return new Object[][]{
                { new Attendee() }
        };
    }
}
