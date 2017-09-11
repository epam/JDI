package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.entities.SupportEntity;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.EntityTable;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static java.util.Arrays.asList;

public class EntityTableTests extends InitTests {
    private static List<SupportEntity> expectedEntities = asList(
            new SupportEntity("Drivers", "Selenium, Custom", "JavaScript, Appium, WinAPI, Sikuli"),
            new SupportEntity("Test Runner", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"),
            new SupportEntity("Asserter", "TestNG, JUnit, Custom", "MSTest, NUnit, Epam"),
            new SupportEntity("Logger", "Log4J, TestNG log, Custom", "Epam, XML/Json logging, Hyper logging"),
            new SupportEntity("Reporter", "Jenkins, Allure, Custom", "Epam Report portal, Serenity, TeamCity, Hudson"),
            new SupportEntity("BDD/DSL", "Custom", "Cucumber, Jbehave, Thucydicles, Specflow"));

    private Supplier<EntityTable<SupportEntity, ?>> entityTableSupplier =
            () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.entityTable;


    @BeforeMethod
    public void before(Method method) {
        isInState(Preconditions.SIMPLE_TABLE_PAGE, method);
    }

    @Test
    public void getCellIntIntTests() {
        List<String> headers =
                entityTableSupplier.get().headers().stream().map(String::toLowerCase).collect(Collectors.toList());
        checkHeaders(headers);

        List<SupportEntity> entities = entityTableSupplier.get().entities();
        Assert.assertEquals(entities, expectedEntities);
    }

    private void checkHeaders(List<String> headers) {
        List<String> expectedHeaders = Arrays.stream(SupportEntity.class.getFields()).map(Field::getName).
                collect(Collectors.toList());

        Assert.assertTrue(expectedHeaders.containsAll(headers));
    }
}
