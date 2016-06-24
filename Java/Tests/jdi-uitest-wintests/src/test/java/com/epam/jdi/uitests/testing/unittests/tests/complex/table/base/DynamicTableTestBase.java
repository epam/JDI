package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.composite.DynamicTable;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.DYNAMIC_TABLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.dynamicTablePage;

/**
 * Created by Natalia_Grebenshchikova on 12/17/2015.
 */
public class DynamicTableTestBase extends InitTests{

    protected DynamicTable dynamic() {
        return dynamicTablePage.dynamicTable;
    }

    @BeforeMethod
    protected void before(Method method) throws IOException {
        isInState(DYNAMIC_TABLE_PAGE, method);
        dynamicTablePage.refresh();
        dynamic().clean();
        Verify.getFails();
    }
}
