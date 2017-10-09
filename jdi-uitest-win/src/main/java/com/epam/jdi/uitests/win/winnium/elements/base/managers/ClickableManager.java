package com.epam.jdi.uitests.win.winnium.elements.base.managers;

import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import org.openqa.selenium.WebElement;

public class ClickableManager {
    private Element element;

    public ClickableManager(Element element) {
        this.element = element;
    }

    private void clickAction() {
        WebElement webElement = element.getWebElement();
        webElement.click();
    }

    public void click() {
        element.getInvoker().doJAction("Click on Element", this::clickAction, element.toString());
    }
}
