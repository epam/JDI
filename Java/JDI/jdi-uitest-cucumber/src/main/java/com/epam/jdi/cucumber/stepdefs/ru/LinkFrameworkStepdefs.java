package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.Utils;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Тогда;

public class
LinkFrameworkStepdefs {

    @Тогда("^ссылка \"([^\"]*)\" из \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void linkFromContains(String linkName, String containerName, String contains) throws NoSuchFieldException {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitReferenceContains(contains);
    }

    @И("^ссылка \"([^\"]*)\" из \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void linkFromMuchReference(String linkName, String containerName, String regex) throws Throwable {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchReference(regex);
    }
}
