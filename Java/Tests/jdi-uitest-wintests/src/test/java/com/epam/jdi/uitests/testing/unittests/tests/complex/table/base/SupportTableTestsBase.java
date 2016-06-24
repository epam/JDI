package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.win.winium.elements.complex.table.interfaces.ITable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.supportPage;

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
