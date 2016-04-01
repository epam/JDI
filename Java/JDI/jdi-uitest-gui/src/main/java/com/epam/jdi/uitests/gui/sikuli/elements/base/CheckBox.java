package com.epam.jdi.uitests.gui.sikuli.elements.base;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import org.sikuli.script.Pattern;

/**
 * Created by Natalia_Grebenshchikova on 1/21/2016.
 */
public class CheckBox extends Clickable implements ICheckBox {

    String checkedImage;
    String uncheckedImage;
    Pattern patternCheck;
    Pattern patternUncheck;

    public CheckBox(String checkedImage, String uncheckedImage) {
        this.checkedImage = checkedImage;
        this.uncheckedImage = uncheckedImage;

        this.patternCheck = new Pattern(checkedImage);
        this.patternUncheck = new Pattern(uncheckedImage);

    }

    public CheckBox(Pattern pattern) {
        super(pattern);
    }
    @Override
    public void check() {

    }

    @Override
    public void uncheck() {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void setValue(String value) {

    }

    @Override
    public String getValue() {
        return null;
    }
}
