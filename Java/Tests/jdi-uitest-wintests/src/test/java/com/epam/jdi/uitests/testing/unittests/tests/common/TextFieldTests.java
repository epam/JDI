package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.User;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE_FILLED;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.contactFormPage;
import static com.epam.jdi.uitests.win.settings.WinSettings.getDriver;
import static org.testng.Assert.assertEquals;

/**
 * Created by Dmitry_Lebedev1 on 11/12/2015.
 */
public class TextFieldTests extends InitTests {
    public String text = User.DEFAULT.name;
    public String contains = "ame";
    public String regex = ".am.";
    Supplier<ITextField> textField = () -> contactFormPage.name;
    private String inputText = "text123!@#$%^&*()";
    private String expectedText = "text123!@#$%^&*()";
    private Preconditions page = CONTACT_PAGE_FILLED;
    private String elementType = "TextField";

    public TextFieldTests() {
    }

    public TextFieldTests(String elementType, Preconditions page, Supplier<ITextField> textItem,
                          String inputText, String expectedText, String text,
                          String contains, String  regex){
        this.elementType = elementType;
        this.page = page;
        this.textField = textItem;
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
        textField.get().input(inputText);
        assertEquals(textField.get().getText(), text + expectedText);
    }

    @Test
    public void sendKeyTest() throws Exception {
        textField.get().sendKeys(inputText);
        assertEquals(textField.get().getText(), text + expectedText);
    }

    @Test
    public void newInputTest() throws Exception {
        textField.get().newInput(inputText);
        assertEquals(textField.get().getText(), expectedText);
    }

    @Test
    public void clearTest() throws Exception {
        textField.get().clear();
        assertEquals(textField.get().getText(), "");
    }

    @Test
    public void multiKeyTest() throws Exception {
        for(char letter : inputText.toCharArray())
            textField.get().sendKeys(Character.toString(letter));
        assertEquals(textField.get().getText(), text + expectedText);
    }

    @Test
    public void focusTest() throws Exception {
        String value = "value";
        String attrName = "focusTest";
        textField.get().setAttribute(attrName, value);
        textField.get().focus();
        String resValue = getDriver().switchTo().activeElement().getAttribute(attrName);
        assertEquals(value, resValue);
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextTests(elementType, page, textField::get, text, contains, regex)
        };
    }
}
