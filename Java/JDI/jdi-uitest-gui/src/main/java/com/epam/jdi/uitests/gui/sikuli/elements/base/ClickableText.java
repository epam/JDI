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

    /**
     * @return Get Element’s text
     */
    @Deprecated
    public String getText() {
        return null;
    }

    /**
     * @param text Specify expected text
     * @return Wait while Element’s text contains expected text. Returns Element’s text
     */
    @Deprecated
    public String waitText(String text) {
        return null;
    }

    /**
     * @param regEx Specify expected regular expression Text
     * @return Wait while Element’s text matches regEx. Returns Element’s text
     */
    @Deprecated
    public String waitMatchText(String regEx) {
        return null;
    }

    /**
     * @return Get value of Element
     */
    @Deprecated
    public String getValue() {
        return null;
    }
}