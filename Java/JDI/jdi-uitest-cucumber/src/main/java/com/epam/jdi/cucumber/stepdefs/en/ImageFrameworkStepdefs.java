package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import cucumber.api.java.en.Then;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static org.junit.Assert.assertEquals;

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
