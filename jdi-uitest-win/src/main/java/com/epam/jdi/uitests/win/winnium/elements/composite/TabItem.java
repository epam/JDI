package com.epam.jdi.uitests.win.winnium.elements.composite;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.ClickableManager;

public class TabItem extends Section implements IClickable {
    private ClickableManager clickableManager = new ClickableManager(this);

    @Override
    public void click() {
        clickableManager.click();
    }
}
