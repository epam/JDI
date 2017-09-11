package com.epam.jdi.uitests.testing.unittests.complex.table;

import com.epam.jdi.uitests.testing.unittests.InitTests;
import com.epam.jdi.uitests.testing.unittests.enums.Preconditions;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.function.Supplier;

import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.core.preconditions.PreconditionsState.isInState;
import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;
import static com.epam.web.matcher.testng.Assert.isFalse;


public class SmokeTableTests extends InitTests {
    private static final String tableAsText = "||X||Type|Now|Plans||\n" +
            "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
            "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
            "||5||Reporter|Jenkins, Allure, Custom|Epam Report portal, Serenity, TeamCity, Hudson||\n" +
            "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydicles, Specflow||";

    private Supplier<Table> simpleTableSupplier =
            () -> mainWindow.mainTabPane.simpleTableTab.nestedSimpleTableView.simpleTable;

    @BeforeMethod
    private void before(Method method) throws IOException {
        isInState(Preconditions.SIMPLE_TABLE_PAGE, method);
    }

    @Test
    public void getValueTest() throws InstantiationException, IllegalAccessException {
        new Check("Table print").areEquals(simpleTableSupplier.get().getValue(), tableAsText);
    }

    @Test
    public void getTextTest() throws InstantiationException, IllegalAccessException {
        new Check("Table print").areEquals(simpleTableSupplier.get().getText(), tableAsText);
    }

    @Test
    public void tableDimensionTest() {
        new Check("Dimensions").areEquals("3/6", simpleTableSupplier.get().columns().count() + "/" +
                simpleTableSupplier.get().rows().count());
    }

    @Test
    public void tableColumnHeadersTest() {
        new Check("Columns headers").areEquals("Type, Now, Plans",
                print(simpleTableSupplier.get().columns().headers()));
    }

    @Test
    public void tableHeadersTest() {
        new Check("Table header").areEquals("Type, Now, Plans", print(simpleTableSupplier.get().headers()));
    }

    @Test
    public void tableHeadersAsTextTest() {
        new Check("Table header as text").areEquals("Type, Now, Plans",
                print(simpleTableSupplier.get().header().select((name, value) -> value.getText())));
    }

    @Test
    public void tableRowHeadersTest() {
        new Check("Rows header").areEquals("1, 2, 3, 4, 5, 6", print(simpleTableSupplier.get().rows().headers()));
    }

    @Test
    public void tableIsNotEmptyTest() {
        isFalse(simpleTableSupplier.get().isEmpty());
    }
}
