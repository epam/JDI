package com.epam.jdi.uitests.win.winnium.elements.common;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.AttributeTextManager;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.ClickableManager;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class CheckBox extends Element implements ICheckBox {
    private ClickableManager clickableManager;
    private AttributeTextManager attributeTextManager;

    public CheckBox() {
        clickableManager = new ClickableManager(this);
        attributeTextManager = new AttributeTextManager(this);
    }

    private boolean isSelected() {
        return getWebElement().isSelected();
    }

    private void checkAction() {
        if (!isSelected())
            clickableManager.click();
        if (!isSelected())
            throw exception("Can't check element. Verify locator for click or isCheckedAction");
    }

    @Override
    public void check() {
        invoker.doJAction("Check Checkbox", this::checkAction, toString());
    }

    @Override
    public void uncheck() {
        if (isSelected())
            clickableManager.click();
        if (isSelected())
            throw exception("Can't uncheck element. Verify locator for click or isCheckedAction");
    }

    @Override
    public boolean isChecked() {
        return isSelected();
    }

    @Override
    public void click() {
        clickableManager.click();
    }

    @Override
    public void setValue(String value) {
        switch (value.toLowerCase()) {
            case "true":
            case "1":
            case "check":
                check();
                break;
            case "false":
            case "0":
            case "uncheck":
                uncheck();
                break;
        }
    }

    @Override
    public String getValue() {
        return invoker.doJActionResult("Get value", ((Boolean) isChecked())::toString, this.toString());
    }

    @Override
    public String getText() {
        return attributeTextManager.getText();
    }

    @Override
    public String waitText(String text) {
        return attributeTextManager.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return attributeTextManager.waitMatchText(regEx);
    }
}
