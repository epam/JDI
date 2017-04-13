package com.epam.jdi.cucumber.stepdefs.en;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.common.*;
import cucumber.api.java.en.*;


/**
 * Created by Dmitry_Lebedev1 on 1/14/2016.
 */
public class LinkFrameworkStepdefs {

    @Then("^link \"([^\"]*)\" from \"([^\"]*)\" contains reference \"([^\"]*)\"$")
    public void linkFromContains(String linkName, String containerName, String contains) throws NoSuchFieldException {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitReferenceContains(contains);
    }

    @And("^link \"([^\"]*)\" from \"([^\"]*)\" much reference \"([^\"]*)\"$")
    public void linkFromMuchReference(String linkName, String containerName, String regex) throws Throwable {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchReference(regex);
    }
}
