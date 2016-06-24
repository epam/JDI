package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.IContactForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.Contact.DEFAULT;
import static com.epam.jdi.uitests.testing.unittests.enums.Buttons.SUBMIT;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.contactFormPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.checkResult;
import static com.epam.web.matcher.testng.Assert.*;
import static java.lang.String.format;

/**
 * Created by Dmitry_Lebedev1 on 10/15/2015.
 */
public class FormTests extends InitTests {
    Supplier<IContactForm> form = () -> contactFormPage.contactForm;

    public FormTests() { }
    public FormTests(Supplier<IContactForm> form) {
        this.form = form;
    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(CONTACT_PAGE, method);
    }

    @Test
    public void fillTest() {
        form.get().fill(DEFAULT);
        assertEquals(form.get().getFormValue(), DEFAULT.toList());
    }

    @Test
    public void submitTest() {
        form.get().submit(DEFAULT);
        checkResult(DEFAULT.toString());
    }

    @Test
    public void submitSpecButtonStringTest() {
        form.get().submit(DEFAULT, "submit");
        checkResult(DEFAULT.toString());
    }

    @Test
    public void submitSpecButtonEnumTest() throws Exception {
        form.get().submit(DEFAULT, SUBMIT);
        checkResult(DEFAULT.toString());
    }

    @Test
    public void submitStringTest() throws Exception {
        form.get().submit(DEFAULT.name);
        checkResult(format("Summary: 3\nName: %s", DEFAULT.name));
    }

    @Test
    public void verifyTest() {
        form.get().fillForm(DEFAULT);
        isEmpty(form.get().verify(DEFAULT));
    }

    @Test
    public void checkTest() {
        form.get().fillForm(DEFAULT);
        hasNoExceptions(() -> form.get().check(DEFAULT));
    }
}