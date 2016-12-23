package com.epam.jdi.uitests.testing.unittests.common;

import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static java.lang.String.format;

public class TextTests extends InitTests {
    private String elementType;
    private Preconditions page;
    private Supplier<? extends IText> textItem;
    private String expectedText;
    private String contains;
    private String regEx;

    public TextTests(String elementType, Preconditions page, Supplier<? extends IText> textItem, String expectedText,
                     String contains, String regEx) {
        this.elementType = elementType;
        this.page = page;
        this.textItem = textItem;
        this.expectedText = expectedText;
        this.contains = contains;
        this.regEx = regEx;
    }

    @BeforeMethod
    public void before(final Method method) {
        logger.info(format("=== Start test %s for %s ===", method.getName(), elementType));
        isInState(page, method);
    }

    @Test
    public void getTextTest() {
        areEquals(textItem.get().getText(), expectedText);
    }

    @Test
    public void getValueTest() throws Exception {
        areEquals(textItem.get().getValue(), expectedText);
    }

    @Test
    public void waitMatchText() {
        areEquals(textItem.get().waitMatchText(regEx), expectedText);
    }

    @Test
    public void waitMatchTextParallel() throws InterruptedException {
        isInState(SUPPORT_PAGE);

        Thread thread = new Thread(() -> {
            page.moveToAction();
        });
        thread.start();

        areEquals(textItem.get().waitMatchText(regEx), expectedText);

        thread.join();
    }

    @Test
    public void waitText() {
        areEquals(textItem.get().waitText(contains), expectedText);
    }

    @Test
    public void waitTextParallel() throws InterruptedException {
        isInState(SUPPORT_PAGE);

        Thread thread = new Thread(() -> {
            page.moveToAction();
        });
        thread.start();

        areEquals(textItem.get().waitText(contains), expectedText);

        thread.join();
    }
}
