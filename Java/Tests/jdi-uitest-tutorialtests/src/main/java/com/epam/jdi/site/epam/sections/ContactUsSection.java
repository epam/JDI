package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.extended-contact-us")
public class ContactUsSection extends Section {
    @Root
    @FindBy(css = "#edit-name")
    public Element name;

    @Root
    @FindBy(css = "#edit-mail")
    public Element mail;

    @Root
    @FindBy(css = "#edit-subject-0-value")
    public Element subject;
    @Root
    @FindBy(css = "#edit-message-0-value")
    public Element message;


}
