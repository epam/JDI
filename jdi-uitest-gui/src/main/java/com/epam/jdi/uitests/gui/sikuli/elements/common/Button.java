package com.epam.jdi.uitests.gui.sikuli.elements.common;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.gui.sikuli.elements.base.ClickableText;
import org.sikuli.script.Pattern;

/**
 * Created by Natalia_Grebenshchikova on 1/18/2016.
 */
public class Button extends ClickableText implements IButton {
    public Button() {
    }

    public Button(Pattern pattern) {
        super(pattern);
    }

}
