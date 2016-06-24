package com.epam.jdi.uitests.testing.unittests.tests.common;

import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication;
import com.epam.jdi.uitests.win.winium.elements.common.Image;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.homePage;
import static com.epam.jdi.uitests.testing.unittests.tests.complex.CommonActionsData.checkText;

/**
 * Created by Dmitry_Lebedev1 on 15/12/2015.
 */
public class ImageTests extends InitTests {
    private static final String ALT = "ALT";
    private static final String SRC = "http://ecse00100176.epam.com/label/Logo_Epam_Color.svg";
    private IImage clickableItem() { return homePage.logoImage; }

    @BeforeMethod
    public void before(final Method method) {
        isInState(HOME_PAGE, method);
    }

    @Test
    public void clickTest() throws InterruptedException {
        EpamJDIApplication.contactFormPage.open();
        clickableItem().click();
        EpamJDIApplication.homePage.checkOpened();
    }

    @Test
    public void setAttributeTest() {
        String _attributeName = "testAttr";
        String _value = "testValue";
        clickableItem().setAttribute(_attributeName, _value);
        checkText(() -> ((Image) clickableItem()).getWebElement().getAttribute(_attributeName), _value);
    }

    @Test
    public void getSourceTest() {
        checkText(clickableItem()::getSource, SRC);
    }

    @Test
    public void getTipTest() {
        checkText(clickableItem()::getAlt, ALT);
    }
}
