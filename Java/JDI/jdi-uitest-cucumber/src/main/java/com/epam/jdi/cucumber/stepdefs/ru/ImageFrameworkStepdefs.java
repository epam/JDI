package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import cucumber.api.java.ru.Тогда;

import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;
import static org.junit.Assert.assertEquals;

public class ImageFrameworkStepdefs {

    @Тогда("^у картинки \"([^\"]*)\" атрибут source равен \"([^\"]*)\"$")
    public void imageSourceIs(String fieldName, String source) throws Throwable {
        assertEquals(((Image) Utils.getClassField(currentPage, fieldName)).getSource(), source);
    }

    @Тогда("^у картинки \"([^\"]*)\" атрибут alt равен \"([^\"]*)\"$")
    public void imageTooltipIs(String fieldName, String alt) throws Throwable {
        assertEquals(((Image) Utils.getClassField(currentPage, fieldName)).getAlt(), alt);
    }
}
