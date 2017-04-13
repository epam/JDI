package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.common.*;
import cucumber.api.java.ru.*;

public class LinkFrameworkStepdefs {

    @Тогда("^ссылка \"([^\"]*)\" из \"([^\"]*)\" содержит \"([^\"]*)\"$")
    public void linkFromContains(String linkName, String containerName, String contains) throws NoSuchFieldException {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitReferenceContains(contains);
    }

    @И("^ссылка \"([^\"]*)\" из \"([^\"]*)\" соответствует \"([^\"]*)\"$")
    public void linkFromMuchReference(String linkName, String containerName, String regex) throws Throwable {
        ((ILink) Utils.getClassField(Utils.getClassField(containerName), linkName)).waitMatchReference(regex);
    }
}
