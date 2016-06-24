package com.epam.jdi.uitests.testing.unittests.tests.complex.table;

import com.epam.jdi.uitests.testing.unittests.tests.complex.table.base.SupportTableTestsBase;
import com.epam.web.matcher.testng.Check;
import org.testng.annotations.Test;

import static com.epam.commons.PrintUtils.print;
import static com.epam.web.matcher.testng.Assert.isFalse;

/**
 * Created by Natalia_Grebenshchik on 10/21/2015.
 */
public class SmokeTableTests extends SupportTableTestsBase {

    private static final String tableAsText = "||X||Type|Now|Plans||\n" +
            "||1||Drivers|Selenium, Custom|JavaScript, Appium, WinAPI, Sikuli||\n" +
            "||2||Test Runner|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||3||Asserter|TestNG, JUnit, Custom|MSTest, NUnit, Epam||\n" +
            "||4||Logger|Log4J, TestNG log, Custom|Epam, XML/Json logging, Hyper logging||\n" +
            "||5||Reporter|Jenkins, Allure, Custom|EPAM Report portal, Serenity, TimCity, Hudson||\n" +
            "||6||BDD/DSL|Custom|Cucumber, Jbehave, Thucydides, SpecFlow||";

    @Test
    public void getValueTest() {
        new Check("Table print").areEquals(table().getValue(), tableAsText);
    }

    @Test
    public void getTextTest() {
        new Check("Table print").areEquals(table().getText(), tableAsText);
    }

    @Test
    public void tableDimensionTest() {
        new Check("Dimensions").areEquals("3/6", table().columns().count() + "/" + table().rows().count());
    }

    @Test
    public void tableColumnHeadersTest() {
        new Check("Columns headers").areEquals("Type, Now, Plans", print(table().columns().headers()));
    }

    @Test
    public void tableHeadersTest() {
        new Check("Table header").areEquals("Type, Now, Plans", print(table().headers()));
    }

    @Test
    public void tableHeadersAsTextTest() {
        new Check("Table header as text").areEquals("Type, Now, Plans", print(table().header().select((name, value) -> value.getText())));
    }

    @Test
    public void tableRowHeadersTest() {
        new Check("Rows header").areEquals("1, 2, 3, 4, 5, 6", print(table().rows().headers()));
    }

    @Test
    public void tableIsNotEmptyTest() {
        isFalse(table().isEmpty());
    }
}
