package com.epam.jdi.uitests.testing.unittests.common;


import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.CONTACT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static org.testng.Assert.assertEquals;


public class TextBoxTests extends InitTests {
    final String[] LINES = {"line1", "line2", "line3"};

    private Supplier<ITextArea> textFieldSupplier =
            () -> mainWindow.mainTabPane.contactFormTab.nestedContactFormView.descriptionTextBox;

    @BeforeMethod
    public void before(final Method method) {
        isInState(CONTACT_PAGE, method);
    }

    @Test
    public void inputLinesTest() {
        textFieldSupplier.get().inputLines(LINES);
        String[] out = textFieldSupplier.get().getLines();
        Assert.assertEquals(LINES, out);
    }

    @Test
    public void addNewLineTest() {
        textFieldSupplier.get().sendKeys("garbageString");
        String newLine = "newLine";
        textFieldSupplier.get().addNewLine(newLine);
        String[] out = textFieldSupplier.get().getLines();
        assertEquals(newLine, out[1]);
    }

    @Test
    public void getLinesTest() {
        textFieldSupplier.get().sendKeys(LINES[0] + "\n" + LINES[1] + "\n" + LINES[2]);
        assertEquals(textFieldSupplier.get().getLines(), LINES);
    }

    @Test
    public void getLinesLengthTest() {
        textFieldSupplier.get().sendKeys(LINES[0] + "\n" + LINES[1] + "\n" + LINES[2]);
        assertEquals(textFieldSupplier.get().getLines().length, 3);
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextFieldTests("ITextArea", Preconditions.CONTACT_PAGE_FILLED, textFieldSupplier, "text123",
                        "text123", "Description", "pti", ".escriptio.")
        };
    }
}
