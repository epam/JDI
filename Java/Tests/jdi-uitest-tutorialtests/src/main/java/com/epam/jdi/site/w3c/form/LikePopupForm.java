package com.epam.jdi.site.w3c.form;

import com.epam.jdi.uitests.core.annotations.functions.CloseButton;
import com.epam.jdi.uitests.core.annotations.functions.OkButton;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.PopupForm;
import org.openqa.selenium.support.FindBy;

public class LikePopupForm extends PopupForm{
    @CloseButton
    @FindBy(css = "button.w3-button.w3-dark-grey.w3-hover-black")
    public Button close;
}
