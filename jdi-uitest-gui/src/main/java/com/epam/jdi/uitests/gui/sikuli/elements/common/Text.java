package com.epam.jdi.uitests.gui.sikuli.elements.common;

import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.gui.sikuli.elements.base.Element;

/**
 * Created by Natalia_Grebenshchikova on 1/19/2016.
 */
public class Text extends Element implements IText {
    /**
     * @return Get Element’s text
     */
    public String getText() {
        return null;
    }

    /**
     * @param text Specify expected text
     * @return Wait while Element’s text contains expected text. Returns Element’s text
     */
    public String waitText(String text) {
        return null;
    }

    /**
     * @param regEx Specify expected regular expression Text
     * @return Wait while Element’s text matches regEx. Returns Element’s text
     */
    public String waitMatchText(String regEx) {
        return null;
    }

    /**
     * @return Get value of Element
     */
    public String getValue() {
        return null;
    }
}
