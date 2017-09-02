package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.web.matcher.testng.Assert;
import org.apache.commons.lang3.SystemUtils;
import org.sikuli.script.App;
import org.sikuli.script.Region;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.URL;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;
import static com.epam.web.matcher.testng.Assert.assertFalse;
import static com.epam.web.matcher.testng.Assert.assertTrue;

public class LayoutVerificationTests extends InitTests {

    private static final ClassLoader loader = LayoutVerificationTests.class.getClassLoader();
    private static final String IMG_DIR_NAME = "img/";
    private static final String HOMEPAGE_BLOCK_1 = "img/jdi_homepage_block1.png";
    private static final String HOMEPAGE_BLOCK_2 = "img/jdi_homepage_block2.png";
    private static final String NOT_JDI_HOMEPAGE = "img/not_jdi_homepage.png";
    private static String jdiHomepageBlock1;
    private static String jdiHomepageBlock2;
    private static String notJdiHomepage;
    private static String imgDirPath;

    @BeforeClass
    public static void verifyImagesExist() {
        imgDirPath = verifyResourceExists(IMG_DIR_NAME);
        jdiHomepageBlock1 = verifyResourceExists(HOMEPAGE_BLOCK_1);
        jdiHomepageBlock2 = verifyResourceExists(HOMEPAGE_BLOCK_2);
        notJdiHomepage = verifyResourceExists(NOT_JDI_HOMEPAGE);
    }

    private static String verifyResourceExists(String resourceName) {
        URL resource = loader.getResource(resourceName);
        assert resource != null;
        if (SystemUtils.IS_OS_WINDOWS) {
            return resource.getPath().replaceFirst("/", "").replace("/", "\\");
        }
        return resource.getPath();
    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(HOME_PAGE, method);
    }

    //@Test
    public void layoutVerificationPositiveTest() {
        homePage.open();
        assertTrue(homePage.verifyElementOnPage(jdiHomepageBlock1));
        homePage.checkThatElementOnPage(jdiHomepageBlock1);
    }

    //@Test
    public void layoutVerificationNegativeTest() {
        homePage.open();
        assertFalse(homePage.verifyElementOnPage(notJdiHomepage));
        homePage.checkThatElementOnPage(notJdiHomepage);
    }

    //@Test
    public void layoutVerificationForSeveralFilesTest() {
        homePage.open();
        App.focus("Chrome");
        Region r = App.focusedWindow();
        boolean f = r.exists("D:\\Work\\Projects\\Java\\JDI\\GitHub\\Java\\Tests\\jdi-uitest-webtests\\target\\test-classes\\img\\jdi_homepage_block1.png") != null;
        Assert.assertTrue(homePage.verifyElementsOnPage(imgDirPath));
        homePage.checkElementsOnPage(imgDirPath);
    }
}
