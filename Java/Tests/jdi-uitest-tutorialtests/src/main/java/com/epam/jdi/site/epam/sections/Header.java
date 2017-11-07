package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.header-menu-wrapper.fixed-element")
public class Header extends Section{
    @FindBy(css = "div.header-menu-button.header-menu-contact-us-button")
    public Button contactButton;
}
