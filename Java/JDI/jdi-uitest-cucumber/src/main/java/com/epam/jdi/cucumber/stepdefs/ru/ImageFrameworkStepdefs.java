package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import cucumber.api.java.ru.*;

import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.*;
import static org.junit.Assert.*;

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
