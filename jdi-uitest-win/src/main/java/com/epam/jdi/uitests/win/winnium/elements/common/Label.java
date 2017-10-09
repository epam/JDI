package com.epam.jdi.uitests.win.winnium.elements.common;

import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.AttributeTextManager;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.ClickableManager;

public class Label extends Element implements ILabel {
    private ClickableManager clickableManager = new ClickableManager(this);
    private AttributeTextManager attributeTextManager = new AttributeTextManager(this);

    @Override
    public void click() {
        clickableManager.click();
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

    @Override
    public String getValue() {
        return attributeTextManager.getValue();
    }
}
