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
    /**
     * Set checkbox checked
     */
    @Override
    public void check() {

    }

    /**
     * Set checkbox unchecked
     */
    @Override
    public void uncheck() {

    }

    /**
     * @return Verify is checkbox checked
     */
    @Override
    public boolean isChecked() {
        return false;
    }

    /**
     * @param value Specify element value
     *              Set value to Element
     */
    @Override
    public void setValue(String value) {

    }

    /**
     * @return Get value of Element
     */
    @Override
    public String getValue() {
        return null;
    }
}
