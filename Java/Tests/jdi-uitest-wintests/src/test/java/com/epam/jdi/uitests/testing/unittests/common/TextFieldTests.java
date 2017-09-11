package com.epam.jdi.uitests.testing.unittests.common;

import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE_FILLED;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static org.testng.Assert.assertEquals;


public class TextFieldTests extends InitTests {
    private String elementType = "TextBox";
    private Preconditions page = CONTACT_PAGE_FILLED;
    private Supplier<? extends ITextField> textFieldSupplier =
            () -> mainWindow.mainTabPane.contactFormTab.nestedContactFormView.nameTextBox;
    private String inputText = "text123!@#$%^&*()";
    private String expectedText = "text123!@#$%^&*()";
    private String text = "Name";
    private String contains = "ame";
    private String regex = ".am.";

    public TextFieldTests() {}

    public TextFieldTests(String elementType, Preconditions page, Supplier<? extends ITextField> textFieldSupplier,
                          String inputText, String expectedText, String text, String contains, String regex) {
        this.elementType = elementType;
        this.page = page;
        this.textFieldSupplier = textFieldSupplier;
        this.inputText = inputText;
        this.expectedText = expectedText;
        this.text = text;
        this.contains = contains;
        this.regex = regex;
    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(page, method);
    }

    @Test
    public void inputTest() throws Exception {
        textFieldSupplier.get().input(inputText);
        Assert.assertEquals(textFieldSupplier.get().getText(), text + expectedText);
    }

    @Test
    public void sendKeyTest() throws Exception {
        textFieldSupplier.get().input(inputText);
        Assert.assertEquals(textFieldSupplier.get().getText(), text + expectedText);
    }

    @Test
    public void newInputTest() throws Exception {
        textFieldSupplier.get().newInput(inputText);
        assertEquals(textFieldSupplier.get().getText(), expectedText);
    }

    @Test
    public void clearTest() throws Exception {
        textFieldSupplier.get().clear();
        assertEquals(textFieldSupplier.get().getText(), "");
    }

    @Test
    public void multiKeyTest() throws Exception {
        for(char letter : inputText.toCharArray())
            textFieldSupplier.get().sendKeys(Character.toString(letter));
        assertEquals(textFieldSupplier.get().getText(), text + expectedText);
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{ new TextTests(elementType, page, textFieldSupplier, text, contains, regex) };
    }
}
