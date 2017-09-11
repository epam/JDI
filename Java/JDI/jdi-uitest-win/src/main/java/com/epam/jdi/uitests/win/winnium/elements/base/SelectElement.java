package com.epam.jdi.uitests.win.winnium.elements.base;


import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.AttributeTextManager;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.ClickableManager;
import org.openqa.selenium.WebElement;

public class SelectElement extends Element implements ISelect {
    private AttributeTextManager textManager = new AttributeTextManager(this);
    private ClickableManager clickableManager = new ClickableManager(this);

    public SelectElement() {
    }

    public SelectElement(WebElement webElement) {
        super(webElement);
    }

    @Override
    public void select() {
        if (!isSelected())
            click();
    }

    @Override
    public boolean isSelected() {
        return invoker.doJActionResult("Is Selected", () -> getWebElement().isSelected(), this.toString());
    }

    @Override
    public void click() {
        clickableManager.click();
    }

    @Override
    public String getText() {
        return textManager.getText();
    }

    @Override
    public String waitText(String text) {
        return textManager.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return textManager.waitMatchText(regEx);
    }

    @Override
    public String getValue() {
        return getText();
    }
}
