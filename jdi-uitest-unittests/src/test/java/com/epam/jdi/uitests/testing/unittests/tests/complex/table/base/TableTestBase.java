package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.composite.DynamicTable;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.DYNAMIC_TABLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dynamicTablePage;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.isInState;

/**
 * Created by Natalia_Grebenshchikova on 12/17/2015.
 */
public class TableTestBase extends InitTests{


    @BeforeMethod
    protected void before(Method method) throws IOException {
        Verify.getFails();
    }
}
