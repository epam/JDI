package com.epam.jdi.uitests.gui.sikuli.elements.base;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import org.sikuli.script.Pattern;

import com.epam.commons.TryCatchUtil;

/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */
public class Clickable extends Element implements IClickable {
    public Clickable() {
    }

    public Clickable(Pattern pattern) {
        super(pattern);
    }

    public final void click() {
        actions.click(this::clickAction);
    }

    protected void clickAction() {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().click(this.getPattern()));
    }

}
