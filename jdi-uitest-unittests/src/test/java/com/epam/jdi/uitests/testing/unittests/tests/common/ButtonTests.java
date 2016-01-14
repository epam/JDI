package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.METALS_AND_COLORS_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.metalsColorsPage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.checkCalculate;
import static com.epam.jdi.uitests.web.selenium.preconditions.PreconditionsState.isInState;

public class ButtonTests extends InitTests {
    private Supplier<IClickable> button = () -> metalsColorsPage.calculateButton;
    private Preconditions page = METALS_AND_COLORS_PAGE;
    private String expectedText = "CALCULATE";
    private String contains = "CUL";
    private String regEx = ".*LCU.*";
    private String elementType = "Button";

    public ButtonTests() {
    }

    public ButtonTests(String elementType, Preconditions page, Supplier<IClickable> textItem, String expectedText, String contains, String regEx){
        this.elementType = elementType;
        this.page = page;
        this.button = textItem;
        this.expectedText = expectedText;
        this.contains = contains;
        this.regEx = regEx;
    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(page, method);
    }

    @Test
    public void clickTest() {
        button.get().click();
        checkCalculate("Summary: 3");
    }

    @Factory
    public Object[] textTests() {
        return new Object[]{
                new TextTests(elementType, page, () -> (IText) button.get(), expectedText, contains, regEx)
        };
    }
}
