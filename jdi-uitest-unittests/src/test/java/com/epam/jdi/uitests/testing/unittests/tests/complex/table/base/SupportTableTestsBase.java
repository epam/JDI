package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.web.matcher.testng.Check;
import com.epam.web.matcher.verify.Verify;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dynamicTablePage;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.supportPage;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.isInState;

/**
 * Created by Roman_Iovlev on 11/20/2015.
 */
public class SupportTableTestsBase extends InitTests {
    protected ITable table() {
        return supportPage.supportTable;
    }

    @BeforeMethod
    public void before(Method method) {
        isInState(SUPPORT_PAGE, method);
        table().clear();
    }

    @AfterMethod
    public void tearDown(){
        table().clear();
    }
}
