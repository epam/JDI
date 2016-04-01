package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import cucumber.api.java.en.Then;

import static com.epam.jdi.uitests.web.selenium.elements.composite.WebPage.currentPage;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitry_Lebedev1 on 1/21/2016.
 */
public class ImageFrameworkStepdefs {

    @Then("^Image \"([^\"]*)\" source is \"([^\"]*)\"$")
    public void imageSourceIs(String fieldName, String source) throws Throwable {
        assertEquals(((Image) Utils.getClassField(currentPage, fieldName)).getSource(), source);
    }

    @Then("^Image \"([^\"]*)\" alt is \"([^\"]*)\"$")
    public void imageTooltipIs(String fieldName, String alt) throws Throwable {
        assertEquals(((Image) Utils.getClassField(currentPage, fieldName)).getAlt(), alt);
    }
}
