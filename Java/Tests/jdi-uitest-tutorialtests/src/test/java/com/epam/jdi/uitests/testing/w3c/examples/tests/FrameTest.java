package com.epam.jdi.uitests.testing.w3c.examples.tests;

import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.epam.jdi.site.w3c.W3CSite.dropDownPage;

/**
 * Created by Roman_Iovlev on 4/4/2017.
 */
public class FrameTest extends W3cInitTests {

    @BeforeMethod
    public void before(Method method) {
        dropDownPage.shouldBeOpened();
    }

    @Test
    public void jDropdownExample() {
        dropDownPage.frame.cars.select("Saab");
        Assert.areEquals(dropDownPage.frame.cars.getSelected(), "Saab");
    }
    @Test
    public void simpleDropdownExample() {
        dropDownPage.frame.carsSimple.select("Opel");
        Assert.areEquals(dropDownPage.frame.carsSimple.getSelected(), "Opel");
    }
}
