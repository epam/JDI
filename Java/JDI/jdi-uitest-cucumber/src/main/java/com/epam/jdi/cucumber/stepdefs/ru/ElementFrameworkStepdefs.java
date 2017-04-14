package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.cucumber.*;
import com.epam.jdi.uitests.core.interfaces.base.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.*;
import cucumber.api.java.ru.*;
import org.junit.*;

public class ElementFrameworkStepdefs {

    @И("^у элемента \"([^\"]*)\" я устанавливаю атрибуту \"([^\"]*)\" значение \"([^\"]*)\"$")
    public void forElementISetAttributeOn(String fieldName, String attributeName, String attributeValue) throws Throwable {
        ((IElement) Utils.getClassField(WebPage.currentPage, fieldName)).setAttribute(attributeName, attributeValue);
    }

    @Тогда("^элемент \"([^\"]*)\" содержит атрибут \"([^\"]*)\" со значением \"([^\"]*)\"$")
    public void elementHasAttributeWithValue(String fieldName, String attributeName, String attributeValue) throws Throwable {
        Assert.assertTrue(((IElement) Utils.getClassField(WebPage.currentPage, fieldName)).getAttribute(attributeName).equals(attributeValue));
    }
}