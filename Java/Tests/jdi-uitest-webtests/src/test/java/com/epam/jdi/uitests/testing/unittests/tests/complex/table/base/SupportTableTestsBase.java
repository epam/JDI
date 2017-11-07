package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITable;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.entities.SupportEntity;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.supportPage;

/**
 * Created by Roman_Iovlev on 11/20/2015.
 */
public class SupportTableTestsBase extends InitTests {
    protected ITable table() {
        return supportPage.supportTable;
    }

    protected EntityTable<SupportEntity, ?> entityTable() {
        return supportPage.entityTable;
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
