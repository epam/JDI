package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.web.robot.RFileInput;
import com.epam.web.matcher.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.DATES_PAGE_FILLED;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.dates;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.*;
import static java.lang.String.format;

/**
 * Created by Dmitry_Lebedev1 on 11/12/2015.
 */
public class RFileInputTests extends InitTests {
    private RFileInput textField() { return dates.rImageInput; }
    private ILabel uploadedFileName() { return dates.uploadedFileName; }

    @BeforeMethod
    public void before(final Method method) {
        isInState(DATES_PAGE_FILLED, method);
    }

    @Test
    public void inputTest() {
        textField().input(getFPath());
        checkFileLoaded(fileName());
    }

    @Test
    public void sendKeysTest() {
        textField().sendKeys(getFPath());
        checkFileLoaded(fileName());
    }

    @Test
    public void newInputTest() throws Exception {
        textField().newInput(getFPath());
        checkFileLoaded(fileName());
    }

    private void checkFileLoaded(String fileName) {
        checkAction(format("FileUpload: file \"%s\" has been uploaded", fileName));
        Assert.areEquals(uploadedFileName().getText(), fileName);
    }
}
