package com.epam.jdi.uitests.testing.career.parallel.tests;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.testing.career.common.tests.TestsBaseParallel;
import com.epam.jdi.uitests.testing.career.page_objects.site.EpamSiteParallel;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.*;
import static com.epam.jdi.uitests.testing.career.parallel.tests.DriverBalancer.getDriverName;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebDrivers;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebSite.init;
import static java.lang.String.format;

public class JDIParallelSimpleTests extends TestsBaseParallel {

    private void seleniumScenario(int num) {
        logger.info(format("Start test %s; Thread: %s", num, Thread.currentThread().getId()));
        EpamSiteParallel site = init(EpamSiteParallel.class, getDriverName());
        site.homePage.open();
        logger.info(format("Site opened: %s; Thread: %s", num, Thread.currentThread().getId()));
        Timer t = new Timer();
        try {
            site.headerMenu.select(INDUSTRIES);
            site.headerMenu.select(ABOUT);
            site.headerMenu.select(IDEAS);
            site.headerMenu.select(CAREERS);
            site.headerMenu.select(CONTACT);
        }
        catch (Exception ex) {
            throw new RuntimeException("ThreadId: " + Thread.currentThread().getId() + " " + site.headerMenu.avatar.getDriverName() + " " + ex.getMessage());
        }
    }

    private void seleniumScenario2(int num) {
        logger.info(format("Start test %s; Thread: %s ", num, Thread.currentThread().getId()));
        EpamSiteParallel site = init(EpamSiteParallel.class, getDriverName());
        site.homePage.open();
        logger.info(format("Site opened: %s; Thread: %s", num, Thread.currentThread().getId()));
        for(int i = 2; i <= 6; i++)
            site.headerMenu.select(i);
    }

    @Test
    public void simpleParallelTest() {
        seleniumScenario(1);
    }
    @Test
    public void simpleParallelTest2() {
        seleniumScenario2(2);
    }
    @Test
    public void simpleParallelTest3() {
        seleniumScenario(3);
    }
    @Test
    public void simpleParallelTest4() {
        seleniumScenario2(4);
    }
    @Test
    public void simpleParallelTest5() {
        seleniumScenario2(5);
    }
    @Test
    public void simpleParallelTest6() {
        seleniumScenario(6);
    }
    @Test
    public void simpleParallelTest7() {
        seleniumScenario2(7);
    }
    @Test
    public void simpleParallelTest8() {
        seleniumScenario(8);
    }


    @AfterSuite(alwaysRun = true)
    public static void jdiTearDown() {
        killAllRunWebDrivers();
    }
}