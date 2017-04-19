package com.epam.jdi.cucumber.stepdefs.ru;

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;

import static com.epam.jdi.cucumber.Utils.getElementByName;

public class ElementFrameworkStepdefs {

    @И("^у элемента \"([^\"]*)\" я устанавливаю атрибуту \"([^\"]*)\" значение \"([^\"]*)\"$")
    public void forElementISetAttributeOn(String fieldName, String attributeName, String attributeValue) throws Throwable {
        ((IElement) getElementByName(fieldName)).setAttribute(attributeName, attributeValue);
    }

    @Тогда("^элемент \"([^\"]*)\" содержит атрибут \"([^\"]*)\" со значением \"([^\"]*)\"$")
    public void elementHasAttributeWithValue(String fieldName, String attributeName, String attributeValue) throws Throwable {
        Assert.assertTrue(((IElement) getElementByName(fieldName)).getAttribute(attributeName).equals(attributeValue));
    }
}