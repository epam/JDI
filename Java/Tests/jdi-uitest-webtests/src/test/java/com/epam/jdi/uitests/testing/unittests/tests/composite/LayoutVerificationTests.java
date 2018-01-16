package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.web.matcher.testng.Assert;
import org.apache.commons.lang3.SystemUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.URL;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.HOME_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.homePage;
import static com.epam.web.matcher.testng.Assert.assertFalse;
import static com.epam.web.matcher.testng.Assert.assertTrue;

public class LayoutVerificationTests extends InitTests {

    private static final ClassLoader loader = LayoutVerificationTests.class.getClassLoader();
    private static String jdiHomepageBlock1;
    private static String notExactJdiHomepage;
    private static String notJdiHomepage;
    private static String imgDirPath;
    private static String noExactImgDirPath;

    @BeforeClass
    public static void verifyImagesExist() {
        imgDirPath = verifyResourceExists("imgs/");
        noExactImgDirPath = verifyResourceExists("imgsNotExact/");
        jdiHomepageBlock1 = verifyResourceExists("img/jdi_homepage_block1.png");
        notExactJdiHomepage = verifyResourceExists("img/jdi_homepage_block1_notExact.png");
        notJdiHomepage = verifyResourceExists("img/not_jdi_homepage.png");
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

    @Test
    public void layoutVerificationPositiveTest() {
        homePage.open();
        assertTrue(homePage.verifyElementOnPage(jdiHomepageBlock1));
        homePage.checkThatElementOnPage(jdiHomepageBlock1);
    }

    @Test
    public void layoutVerificationNegativeTest() {
        homePage.open();
        assertFalse(homePage.verifyElementOnPage(notJdiHomepage));
    }

    @Test
    public void layoutVerificationForSeveralFilesTest() {
        homePage.open();
        Assert.assertTrue(homePage.verifyElementsOnPage(imgDirPath));
        //homePage.checkElementsOnPage(imgDirPath);
    }

    @Test
    public void layoutVerificationForSeveralFilesNotExactMatchTest() {
        homePage.open();
        Assert.assertTrue(homePage.verifyElementsOnPage(noExactImgDirPath,40));
    }

    @Test
    public void layoutVerificationForSeveralFilesNotExactMatchNegativeTest() {
        homePage.open();
        Assert.assertFalse(homePage.verifyElementsOnPage(noExactImgDirPath,90));
    }

    @Test
    public void layoutVerificationForNotExactMatchTest() {
        homePage.open();
        Assert.assertTrue(homePage.verifyElementOnPage(notExactJdiHomepage, 30));
    }

    @Test
    public void layoutVerificationForNotExactMatchNegativeTest() {
        homePage.open();
        Assert.assertFalse(homePage.verifyElementOnPage(notExactJdiHomepage, 90));
    }

}
