package com.epam.jdi.uitests.testing.career.grid_remote_tests;

import com.epam.commons.Timer;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu;
import com.epam.jdi.uitests.testing.career.page_objects.site.EpamSiteParallel;
import com.epam.jdi.uitests.web.WebSettings;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.CalculationUtils.average;
import static com.epam.jdi.uitests.core.settings.JDISettings.driverFactory;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.testing.career.grid_remote_tests.TestScenarios.getDriverName;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu.*;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebDrivers;
import static java.lang.String.format;
import static java.util.Arrays.asList;

public class JDIParallelTests {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        killAllRunWebDrivers();
        WebSettings.init();
        try {
            driverFactory.setDriverPath("C:\\Selenium");
        } catch (Exception ex) {
            throw new RuntimeException("Can't init selenium driver");
        }
    }

    private static MapArray<HeaderMenu, List<Long>> statistic = new MapArray<>();
    private void update(HeaderMenu name, Long time, Timer t) {
        if (statistic.keys().contains(name))
            statistic.update(name, v -> {
                List<Long> r = new ArrayList<>(v);
                r.add(time);
                return v;});
        else
            statistic.add(name, asList(time));
        t.restart();
    }

    private void seleniumScenario(int num) {
        logger.info(format("Start test %s; Thread: %s", num, Thread.currentThread().getId()));
        EpamSiteParallel site = new EpamSiteParallel(getDriverName());
        site.homePage.open();
        logger.info(format("Site opened: %s; Thread: %s", num, Thread.currentThread().getId()));
        Timer t = new Timer();
        try {
            site.headerMenu.select(INDUSTRIES);
            update(INDUSTRIES, t.timePassedInMSec(), t);
            site.headerMenu.select(ABOUT);
            update(ABOUT, t.timePassedInMSec(), t);
            site.headerMenu.select(IDEAS);
            update(IDEAS, t.timePassedInMSec(), t);
            site.headerMenu.select(CAREERS);
            update(CAREERS, t.timePassedInMSec(), t);
            site.headerMenu.select(CONTACT);
            update(CONTACT, t.timePassedInMSec(), t);
        }
        catch (Exception ex) {
            throw new RuntimeException("ThreadId: " + Thread.currentThread().getId() + " " + site.headerMenu.avatar.getDriverName() + " " + ex.getMessage());
        }
    }

    private void seleniumScenario2(int num) {
        logger.info(format("Start test %s; Thread: %s ", num, Thread.currentThread().getId()));
        EpamSiteParallel site = new EpamSiteParallel(getDriverName());
        site.homePage.open();
        logger.info(format("Site opened: %s; Thread: %s", num, Thread.currentThread().getId()));
        for(int i = 1; i<=5; i++) {
            site.headerMenu.select(i);
            System.out.println("Click " + i + " done");
        }
    }

    @Test
    public void simpleParallelTest() {
        seleniumScenario(1);
    }
    @Test
    public void simpleParallelTest2() {
        seleniumScenario(2);
    }
    @Test
    public void simpleParallelTest3() {
        seleniumScenario(3);
    }
    @Test
    public void simpleParallelTest4() {
        seleniumScenario(4);
    }
    @Test
    public void simpleParallelTest5() {
        seleniumScenario(5);
    }
    @Test
    public void simpleParallelTest6() {
        seleniumScenario(6);
    }


    @AfterSuite(alwaysRun = true)
    public static void jdiTearDown() {
        logger.info(statistic.toMapArray((k,v) -> k, (k,v) -> average(v)).toString());
        killAllRunWebDrivers();
    }
}