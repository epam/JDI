package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.IContactForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.Contact.DEFAULT;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.contactFormPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.checkResult;

/**
 * Created by Dmitry_Lebedev1 on 10/15/2015.
 */
public class FormTwoButtonsTests extends InitTests {
    Supplier<IContactForm> form = () -> contactFormPage.contactFormTwoButtons;

    @BeforeMethod
    public void before(final Method method) {
        isInState(CONTACT_PAGE, method);
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new FormTests(form)
        };
    }

    @Test
    public void submitSpecButtonStringTest() {
        form.get().submit(DEFAULT, "calculate");
        checkResult("Summary: 3");
    }
}