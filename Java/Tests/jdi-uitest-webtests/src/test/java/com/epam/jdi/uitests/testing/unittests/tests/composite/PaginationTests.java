package com.epam.jdi.uitests.testing.unittests.tests.composite;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.web.selenium.elements.composite.Pagination;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.enums.Preconditions.SIMPLE_PAGE;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.simpleTablePage;
import static com.epam.jdi.uitests.web.settings.WebSettings.getDriver;
import static com.epam.web.matcher.testng.Assert.assertContains;

/**
 * Created by Dmitry_Lebedev1 on 21/12/2015.
 */
public class PaginationTests extends InitTests {
    private Pagination pagination() { return  simpleTablePage.pagination; }

    public static final String[] pages = {"index.html", "contacts.html", "support.html", "dates.html",
            "complex-table.html", "simple-table.html", "user-table.html", "table-pages.html",
            "different-elements.html", "metals-colors.html"};

    private void checkPageOpened(int num) {
        assertContains(() -> getDriver().getCurrentUrl(), "/" + pages[num]);
    }

    @BeforeMethod
    public void before(final Method method) {
        isInState(SIMPLE_PAGE, method);
    }

    @Test
    public void nextTest() {
        pagination().next();
        checkPageOpened(6);
    }

    @Test
    public void prevTest() {
        pagination().previous();
        checkPageOpened(4);
    }

    @Test
    public void firstTest() {
        pagination().first();
        checkPageOpened(1);
    }

    @Test
    public void lastTest() {
        pagination().last();
        checkPageOpened(pages.length-1);
    }

    @Test
    public void jPaginationAnnotationTest() {
        Assert.assertTrue(simpleTablePage.jPagination.isDisplayed());
    }
}
