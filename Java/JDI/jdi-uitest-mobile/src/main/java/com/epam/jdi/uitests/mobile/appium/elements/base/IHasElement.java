package com.epam.jdi.uitests.mobile.appium.elements.base;

import com.epam.jdi.uitests.core.interfaces.base.IHasParent;
import org.openqa.selenium.WebElement;

/**
 * Created by Roman_Iovlev on 8/31/2016.
 */
public interface IHasElement extends IHasParent {
    WebElement getWebElement();
    void setWebElement(WebElement webElement);
}
