package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.entities.SupportEntity;
import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * Created by Sergey_Mishanin on 11/21/16.
 */
public class EntityTableTests extends SupportTableTestsBase {
    private static List<SupportEntity> expectedEntities = asList(
        new SupportEntity("Drivers", "Selenium, Custom", "JavaScript, Appium, WinAPI, Sikuli"),
        new SupportEntity("Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"),
        new SupportEntity("Asserter", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"),
        new SupportEntity("Logger", "Log4J, TestNG log, Custom", "Epam, XML/Json logging, Hyper logging"),
        new SupportEntity("Reporter", "Jenkins, Allure, Custom", "EPAM Report portal, Serenity, TimCity, Hudson"),
        new SupportEntity("BDD/DSL", "Custom", "Cucumber, Jbehave, Thucydides, SpecFlow"));

    @Test
    public void getCellIntIntTests() {
        List<String> headers = entityTable().headers();
        checkHeaders(headers);

        List<SupportEntity> entities = entityTable().entities();
        Assert.assertEquals(entities, expectedEntities);
    }

    private void checkHeaders(List<String> headers)
    {
        List<String> expectedHeaders =
                Arrays.stream(SupportEntity.class.getFields())
                        .map(Field::getName).collect(Collectors.toList());

        headers.forEach(header -> Assert.assertTrue(expectedHeaders.contains(header)));
    }
}
