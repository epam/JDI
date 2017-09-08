package com.epam.jdi.uitests.testing.unittests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.forms.IContactForm;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;

public class FormTwoButtonsTests extends InitTests {
    private Supplier<IContactForm>
            form = () -> mainWindow.mainTabPane.contactFormTab.nestedContactFormView.contactFormTwoButtons;

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
}
