package com.epam.jdi.uitests.win.winnium.elements.common;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.AttributeTextManager;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.ClickableManager;

public class Button extends Element implements IButton {
    private ClickableManager clickableManager = new ClickableManager(this);
    private AttributeTextManager textFromAttribute = new AttributeTextManager(this);


    @Override
    public String getText() {
        return textFromAttribute.getText();
    }

    @Override
    public String getValue() {
        return textFromAttribute.getValue();
    }

    @Override
    public String waitText(String text) {
        return textFromAttribute.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return textFromAttribute.waitMatchText(regEx);
    }

    @Override
    public void click() {
        clickableManager.click();
    }
}
