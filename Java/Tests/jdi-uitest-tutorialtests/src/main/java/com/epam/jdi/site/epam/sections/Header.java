package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "header__wrapper")
public class Header extends Section {

    @FindBy(css = "li.top-navigation__item:nth-child(8) > a:nth-child(1)")
    public Button contactButton;
}


