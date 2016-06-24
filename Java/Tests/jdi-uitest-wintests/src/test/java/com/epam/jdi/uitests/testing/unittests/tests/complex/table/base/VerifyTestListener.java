package com.epam.jdi.uitests.testing.unittests.tests.complex.table.base;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.List;

import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.web.matcher.verify.Verify.getFails;
import static java.lang.String.format;
import static org.testng.ITestResult.FAILURE;

/**
 * Created by Natalia_Grebenshchikova on 12/16/2015.
 */
public class VerifyTestListener extends TestListenerAdapter {

    @Override
    public void onTestSuccess(ITestResult result) {
        checkVerificationExceptions(result);
        super.onTestSuccess(result);
    }

    @Override
    public void onTestFailure(ITestResult result){
        checkVerificationExceptions(result);
        super.onTestFailure(result);
    }

    private ITestResult checkVerificationExceptions(ITestResult result) {
        List<String> verificationFails =  getFails();
        if (verificationFails.size() > 0) {
            result.setStatus(FAILURE);
            result.setThrowable(new AssertionError(format("Test '%s(%s)' has %d verification issues: " + LINE_BREAK + "%s",
                    result.getName(),
                    print(select(result.getParameters(), Object::toString)),
                    verificationFails.size(),
                    print(verificationFails, LINE_BREAK))));
        }
        return result;
    }
}