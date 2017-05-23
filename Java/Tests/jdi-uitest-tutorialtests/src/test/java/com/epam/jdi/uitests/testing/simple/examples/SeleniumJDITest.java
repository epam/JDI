package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.entities.Job;
import com.epam.jdi.selenium.pageobject.SeleniumJdiPage;
import com.epam.jdi.uitests.web.settings.WebSettings;
import org.omg.CORBA.Environment;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.enums.HeaderMenu.CAREERS;
import static com.epam.jdi.uitests.core.settings.JDISettings.logger;
import static com.epam.jdi.uitests.web.selenium.elements.WebCascadeInit.initPageObject;
import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.openUrl;
import static java.lang.System.getProperty;

/**
 * Created by Roman_Iovlev on 5/23/2017.
 */
public class SeleniumJDITest {
    SeleniumJdiPage page;

    @BeforeMethod
    public void before(Method method) throws IOException {
        page = initPageObject(SeleniumJdiPage.class);
    }

    @Test
    public void seleniumJDITest() {
        openUrl("https://www.epam.com/");
        page.logo.click();
        page.menu.get(2).click();
        page.headerMenu.select(CAREERS);
        System.out.println("Entities count: " + page.jobs.size());
        List<Job> jobs = page.jobs.entities();
        //System.out.println(print(select(jobs, Job::toString), getProperty("line.separator")));
    }
}
