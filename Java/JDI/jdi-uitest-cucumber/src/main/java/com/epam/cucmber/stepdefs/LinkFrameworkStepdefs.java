package com.epam.cucmber.stepdefs;

import com.epam.jdi.uitests.core.interfaces.common.ILink;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;


/**
 * Created by Dmitry_Lebedev1 on 1/14/2016.
 */
public class LinkFrameworkStepdefs {

    @Then("^Link \"([^\"]*)\" from \"([^\"]*)\" contains reference \"([^\"]*)\"$")
    public void linkFromContains(String linkName, String containerName, String contains) throws NoSuchFieldException {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitReferenceContains(contains);
    }

    @And("^Link \"([^\"]*)\" from \"([^\"]*)\" much reference \"([^\"]*)\"$")
    public void linkFromMuchReference(String linkName, String containerName, String regex) throws Throwable {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchReference(regex);
    }
}
