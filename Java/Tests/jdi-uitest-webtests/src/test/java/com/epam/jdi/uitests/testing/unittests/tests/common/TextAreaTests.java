package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.web.selenium.elements.common.TextArea;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.*;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.entities.User.DEFAULT;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE_FILLED;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.contactFormPage;
import static com.epam.jdi.uitests.web.settings.WebSettings.getJSExecutor;
import static org.testng.Assert.assertEquals;

/**
 * Created by Dmitry_Lebedev1 on 11/12/2015.
 */
public class TextAreaTests extends InitTests {
    final String[] LINES = {"line1", "line2", "line3"};
    private TextArea textItem() { return contactFormPage.description; }

    private String[] getStringArray() {
        return getJSExecutor().executeScript("return arguments[0].value", textItem().getWebElement()).toString().split("\n");
    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(CONTACT_PAGE, method);
    }

    @Test
    public void inputLinesTest() {
        textItem().inputLines(LINES);
        String[] out = getStringArray();
        textItem().getText();
        assertEquals(LINES, out);
    }

    @Test
    public void addNewLineTest() {
        textItem().sendKeys("garbageString");
        String newLine = "newLine";
        textItem().addNewLine(newLine);
        String[] out = getStringArray();
        assertEquals(newLine, out[1]);
    }

    @Test
    public void getLinesTest() {
        textItem().sendKeys(LINES[0] + "\n" + LINES[1] + "\n" + LINES[2]);
        assertEquals(textItem().getLines(), LINES);
    }

    @Test
    public void getLinesLengthTest() {
        textItem().sendKeys(LINES[0] + "\n" + LINES[1] + "\n" + LINES[2]);
        assertEquals(textItem().getLines().length, 3);
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextFieldTests("ITextArea", CONTACT_PAGE_FILLED,
                        this::textItem, "text123", "text123",
                        DEFAULT.description, "pti", ".escriptio.")
        };
    }

    @Test
    public void imageIsDisplayedTest(){
        Assert.assertTrue(textItem().isDisplayed());
    }

    @Test
    public void shouldTest(){
        textItem().shouldHave(attribute("rows"), attribute("cols"), id("Description"))
                .shouldBe(empty);
    }
}
