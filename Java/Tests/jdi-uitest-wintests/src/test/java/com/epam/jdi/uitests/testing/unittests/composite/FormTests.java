package com.epam.jdi.uitests.testing.unittests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Buttons;
import com.epam.jdi.uitests.testing.unittests.pageobjects.forms.IContactForm;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.entities.Contact.DEFAULT;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.*;


public class FormTests extends InitTests {
    private Supplier<IContactForm> form = () -> mainWindow.mainTabPane.contactFormTab.nestedContactFormView.contactForm;

    public FormTests() {}

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
        Assert.assertContains(Arrays.toString(mainWindow.passwordTextBox.getLines()), DEFAULT.toString());
    }

    @Test
    public void submitSpecButtonStringTest() {
        form.get().submit(DEFAULT, "submit");
        Assert.assertContains(Arrays.toString(mainWindow.passwordTextBox.getLines()), DEFAULT.toString());
    }

    @Test
    public void submitSpecButtonEnumTest() throws Exception {
        form.get().submit(DEFAULT, Buttons.SUBMIT);
        Assert.assertContains(Arrays.toString(mainWindow.passwordTextBox.getLines()), DEFAULT.toString());
    }

    @Test
    public void submitStringTest() throws Exception {
        form.get().submit(DEFAULT.name);
        Assert.assertContains(String.format("Summary: 3\nName: %s", DEFAULT.name), DEFAULT.name);
    }

    @Test
    public void verifyTest() {
        form.get().fillForm(DEFAULT);
        List<String> verify = form.get().verify(DEFAULT);
        isEmpty(verify);
    }

    @Test
    public void checkTest() {
        form.get().fillForm(DEFAULT);
        hasNoExceptions(() -> form.get().check(DEFAULT));
    }
}
