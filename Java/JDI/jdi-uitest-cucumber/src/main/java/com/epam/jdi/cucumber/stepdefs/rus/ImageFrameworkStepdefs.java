package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import cucumber.api.java.en.Then;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dmitry_Lebedev1 on 1/21/2016.
 */
public class ImageFrameworkStepdefs {

    @Then("^image \"([^\"]*)\" has source \"([^\"]*)\"$")
    public void imageSourceIs(String fieldName, String source) {
        Image img = getElementByName(fieldName);
        assertEquals(img.getSource(), source);
    }

    @Then("^image \"([^\"]*)\" has alt \"([^\"]*)\"$")
    public void imageTooltipIs(String fieldName, String alt) {
        Image img = getElementByName(fieldName);
        assertEquals(img.getAlt(), alt);
    }
}
