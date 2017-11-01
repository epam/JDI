package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.extended-contact-us")
public class ContactUsSection extends Section{
    @Root
    @FindBy(css = "li.headquarters")
    public Element headquarters;

    @Root
    @FindBy(css = "li.events")
    public Element events;

    @Root
    @FindBy(css = "li.share")
    public Element share;
}
