package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import org.apache.commons.lang3.SystemUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
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
    public static void verifyImagesExist() throws URISyntaxException {
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

    @Test
    public void layoutVerificationPositiveTest() {
        homePage.open();
        assertTrue(homePage.verifyLayout(jdiHomepageBlock1));
    }

    @Test
    public void layoutVerificationNegativeTest() {
        homePage.open();
        assertFalse(homePage.verifyLayout(notJdiHomepage));
    }

    @Test
    public void layoutVerificationForSeveralFilesTest() throws URISyntaxException {
        homePage.open();
        List<String> matchedImages = homePage.verifyLayoutMatches(imgDirPath);
        assertTrue(matchedImages.contains(jdiHomepageBlock1)
                && matchedImages.contains(jdiHomepageBlock2)
                && !matchedImages.contains(notJdiHomepage));
    }
}
