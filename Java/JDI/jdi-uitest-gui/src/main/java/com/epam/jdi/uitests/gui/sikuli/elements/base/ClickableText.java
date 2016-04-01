package com.epam.jdi.uitests.gui.sikuli.elements.base;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.base.IHasValue;
import com.epam.jdi.uitests.core.interfaces.common.IText;

import org.sikuli.script.Pattern;

/**
 * Created by Natalia_Grebenshchikova on 1/19/2016.
 */
public class ClickableText extends Clickable implements IHasValue, IClickable, IText {

    public ClickableText() {

    }

    public ClickableText(Pattern pattern) {
        super(pattern);
    }

    @Deprecated
    public String getText() {
        return null;
    }

    @Deprecated
    public String waitText(String text) {
        return null;
    }

    @Deprecated
    public String waitMatchText(String regEx) {
        return null;
    }

    @Deprecated
    public String getValue() {
        return null;
    }
}