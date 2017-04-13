package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.web.selenium.elements.common.*;
import cucumber.api.java.en.*;

import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.*;
import static org.junit.Assert.*;

/**
 * Created by Dmitry_Lebedev1 on 1/21/2016.
 */
public class ImageFrameworkStepdefs {

    @Then("^image \"([^\"]*)\" source is \"([^\"]*)\"$")
    public void imageSourceIs(String fieldName, String source) throws Throwable {
        assertEquals(((Image) Utils.getClassField(currentPage, fieldName)).getSource(), source);
    }

    @Then("^image \"([^\"]*)\" alt is \"([^\"]*)\"$")
    public void imageTooltipIs(String fieldName, String alt) throws Throwable {
        assertEquals(((Image) Utils.getClassField(currentPage, fieldName)).getAlt(), alt);
    }
}
