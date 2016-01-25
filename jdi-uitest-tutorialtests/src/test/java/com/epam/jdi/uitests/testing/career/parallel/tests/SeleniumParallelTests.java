package com.epam.jdi.uitests.testing.career.parallel.tests;

import com.epam.commons.Timer;
import com.epam.commons.map.MapArray;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.commons.CalculationUtils.average;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverUtils.killAllRunWebDrivers;
import static java.lang.String.format;
import static java.lang.System.setProperty;
import static java.lang.Thread.currentThread;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.SECONDS;

public class SeleniumParallelTests {
    private Map<Long, WebDriver> threadsDrivers = new HashMap<>();

    private WebDriver registerNewdriver(long threadId) {
        WebDriver driver = getNewDriver();
        threadsDrivers.put(threadId, driver);
        return driver;
    }

    private WebDriver getDriver() {
        long threadId = currentThread().getId();
        return threadsDrivers.containsKey(threadId)
                ? threadsDrivers.get(threadId)
                : registerNewdriver(threadId);
    }

    private static MapArray<Integer, List<Long>> statistic = new MapArray<>();
    private void update(int name, Long time, Timer t) {
        if (statistic.keys().contains(name))
            statistic.update(name, v -> {
                List<Long> r = new ArrayList<>(v);
                r.add(time);
                return v;});
        else
            statistic.add(name, asList(time));
        t.restart();
    }

    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        killAllRunWebDrivers();
        setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe");
    }
    public WebDriver getNewDriver() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        return driver;
    }
    private void seleniumScenario(int num) {
        System.out.println(format("Start test %s; Thread: %s", num, Thread.currentThread().getId()));
        WebDriver driver = getDriver();
        driver.get("http://www.epam.com");
        System.out.println(format("Site opened: %s; Thread: %s", num, Thread.currentThread().getId()));
        Timer t = new Timer();
        for(int i = 1; i<=5; i++) {
            driver.findElements(By.cssSelector(".tile-menu>li>a")).get(i).click();
            update(i, t.timePassedInMSec(), t);
            System.out.println("Click " + i + " done");
            try {Thread.sleep(1000); } catch (Exception ex) {}
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
        System.out.println(statistic.toMapArray((k, v) -> k, (k, v) -> average(v)).toString());
        killAllRunWebDrivers();
    }
}