package com.epam.jdi.cucumber.stepdefs.rus;

import com.epam.jdi.uitests.core.interfaces.common.ILink;
import cucumber.api.java.en.Then;

import static com.epam.jdi.cucumber.Utils.getElementByName;


/**
 * Created by Dmitry_Lebedev1 on 1/14/2016.
 */
public class LinkFrameworkStepdefs {

    @Then("^link \"([^\"]*)\" from \"([^\"]*)\" contains reference \"([^\"]*)\"$")
    public void linkFromContains(String linkName, String containerName, String contains) {
        ILink link = getElementByName(containerName, linkName);
        link.waitReferenceContains(contains);
    }

    @Then("^link \"([^\"]*)\" from \"([^\"]*)\" match reference \"([^\"]*)\"$")
    public void linkFromMuchReference(String linkName, String containerName, String regex) {
        ILink link = getElementByName(containerName, linkName);
        link.waitMatchReference(regex);
    }
}
