package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.web.matcher.verify.Verify;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Natalia_Grebenshchikova on 12/17/2015.
 */
public class TableTestBase extends InitTests{


    @BeforeMethod
    protected void before(Method method) throws IOException {
        Verify.getFails();
    }
}
