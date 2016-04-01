package com.epam.jdi.uitests.gui.sikuli.elements.common;

import com.epam.commons.TryCatchUtil;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
/**
 * Created by Natalia_Grebenshchikova on 1/19/2016.
 */
public class TextField extends Text implements ITextField {

    public final void input(CharSequence text) {
        actions.input(text, this::inputAction);
    }

    protected void inputAction(CharSequence text) {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().type(this.getPattern(), text.toString()));
    }

    public void newInput(CharSequence text) {

    }

    public void clear() {

    }

    public void focus() {

    }

    public void setValue(String value) {

    }
}
