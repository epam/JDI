package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData;
import com.epam.jdi.uitests.win.winium.elements.base.Element;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SUPPORT_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.homePage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.runParallel;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.waitTimeOut;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.isTrue;
import static java.lang.String.format;

public class TextTests extends InitTests {

    private String elementType = "Text";
    private Preconditions page = HOME_PAGE;
    private Supplier<IText> textItem = () -> homePage.text;
    private String expectedText = ("Lorem ipsum dolor sit amet, consectetur adipisicing elit,"
            + " sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
            + " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris"
            + " nisi ut aliquip ex ea commodo consequat Duis aute irure dolor in"
            + " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.").toUpperCase();
    private String contains = "ENIM AD MINIM VENIAM, QUIS NOSTRUD";
    private String regEx = ".* IPSUM DOLOR SIT AMET.*";

    public TextTests() {
    }

    public TextTests(String elementType, Preconditions page, Supplier<IText> textItem, String expectedText, String contains, String regEx) {
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
        areEquals(textItem.get()::getValue, expectedText);
    }

    @Test
    public void waitMatchText() {
        areEquals(textItem.get().waitMatchText(regEx), expectedText);
    }

    @Test
    public void waitMatchTextParallel() {
        isInState(SUPPORT_PAGE);
        runParallel(page::open);
        areEquals(textItem.get().waitMatchText(regEx), expectedText);
        isTrue(timer.timePassedInMSec() > waitTimeOut);
    }

    @Test
    public void waitText() {
        areEquals(textItem.get().waitText(contains), expectedText);
    }

    @Test
    public void waitTextParallel() {
        isInState(SUPPORT_PAGE);
        runParallel(page::open);
        areEquals(textItem.get().waitText(contains), expectedText);
        isTrue(timer.timePassedInMSec() > waitTimeOut);
    }

    @Test
    public void setAttributeTest() {
        String _attributeName = "testAttr";
        String _value = "testValue";
        textItem.get().setAttribute(_attributeName, _value);
        CommonActionsData.checkText(() -> ((Element)textItem.get()).getWebElement().getAttribute(_attributeName), _value);
    }
}