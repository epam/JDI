package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.header;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.supportPage;

/**
 * Created by Dmitry_Lebedev1 on 10/15/2015.
 */
public class SearchTests extends InitTests {
    @BeforeMethod
    public void before(final Method method) {
        isInState(HOME_PAGE, method);
    }

    @Test
    public void fillTest() {
        header.search.find("something");
        supportPage.checkOpened();
    }
}