package com.epam.jdi.uitests.gui.sikuli.elements.common;

import com.epam.commons.TryCatchUtil;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by Natalia_Grebenshchikova on 1/19/2016.
 */
public class TextField extends Text implements ITextField {
    public String labelLocator;
    /**
     * @param text Specify text to input to TextField
     *             Input text in textfield
     */
    public final void input(CharSequence text) {
        actions.input(text, this::inputAction);
    }

    protected void inputAction(CharSequence text) {
        TryCatchUtil.tryGetResult(() -> this.getParentRegion().type(this.getPattern(), text.toString()));
    }
    public String label() {
        if (labelLocator == null)
            throw exception("Label locator no specified");
        Text label = new Text();
        label.setParent(getParent());
        return label.getText();
    }

    /**
     * @param text Specify text to input to TextField
     *             Clear and input text in textfield
     */
    public void newInput(CharSequence text) {

    }

    /**
     * Clear textfield
     */
    public void clear() {

    }

    /**
     * Focus(click) on textfield
     */
    public void focus() {

    }

    /**
     * @param value Specify element value
     *              Set value to Element
     */
    public void setValue(String value) {

    }
}
