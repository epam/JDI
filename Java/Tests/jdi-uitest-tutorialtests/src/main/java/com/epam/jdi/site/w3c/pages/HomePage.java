package com.epam.jdi.site.w3c.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.PopupForm;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

import java.nio.Buffer;

public class HomePage extends WebPage{
    @FindBy(css = "i.fa.fa-thumbs-o-up.w3-xxlarge")
    public Button like;

    PopupForm popupForm;

}
