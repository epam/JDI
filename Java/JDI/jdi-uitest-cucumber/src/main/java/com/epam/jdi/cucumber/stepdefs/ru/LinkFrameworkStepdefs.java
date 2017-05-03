package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.common.ILink;
import cucumber.api.java.en.Then;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class LinkFrameworkStepdefs {

    @Then("^ссылка \"([^\"]*)\" из \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void linkFromContains(String linkName, String containerName, String contains) {
        ILink link = getElementByName(containerName, linkName);
        link.waitReferenceContains(contains);
    }

    @Then("^ссылка \"([^\"]*)\" из \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void linkFromMuchReference(String linkName, String containerName, String regex) {
        ILink link = getElementByName(containerName, linkName);
        link.waitMatchReference(regex);
    }
}
