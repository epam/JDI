package com.epam.jdi.uitests.testing.unittests.tests.complex;

import com.epam.commons.Timer;
import com.epam.commons.linqinterfaces.JAction;
import org.openqa.selenium.By;
//import ru.yandex.qatools.allure.annotations.Step;

import java.io.File;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication.*;
import static com.epam.jdi.uitests.win.settings.WinSettings.getDriver;
import static com.epam.web.matcher.testng.Assert.areEquals;
import static com.epam.web.matcher.testng.Assert.assertContains;

/**
 * Created by Roman_Iovlev on 9/18/2015.
 */
public class CommonActionsData {
    public static final String noElementsMessage = "No elements selected. Override getSelectedAction or place locator to <select> tag";
    public static final long waitTimeOut = 1000;
    public static Timer timer;
    private static String _name = null;
    private static String _path = null;

    public static void checkActionThrowError(JAction action, String msg) {
        try {
            action.invoke();
        } catch (Exception | AssertionError ex) {
            assertContains(ex.getMessage(), msg);
            return;
        }
        throw exception("Exception not thrown");
    }

    public static long getTimePassed() {
        return timer.timePassedInMSec();
    }

    public static void runParallel(final JAction action) {
        new Thread() {
            @Override
            public void run() {
                timer = new Timer();
                Timer.sleep(waitTimeOut);
                action.invoke();
            }
        }.run();
    }

    private static void createFile() {
        try {
            File temp = File.createTempFile("tmp", ".tmp");
            _name = temp.getName();
            _path = temp.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFPath() {
        if (_path == null) {
            createFile();
        }
        return _path;
    }

    public static String fileName() {
        if (_name == null) {
            createFile();
        }
        return _name;
    }

   // @Step
    public static void checkAction(String text) {
        assertContains(actionsLog::getFirstText, text);
    }

    public static void looseFocus() {
        getDriver().findElement(By.className("footer-content")).click();
    }

   // @Step
    public static void checkText(Supplier<String> func, String expected) {
        areEquals(func.get(), expected);
    }

   // @Step
    public static void checkCalculate(String text) {
        assertContains(metalsColorsPage.calculateText::getText, text);
    }

  //  @Step
    public static void checkResult(String text) {
        assertContains(contactFormPage.result::getText, text);
    }

}
