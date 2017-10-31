package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.tile-list.section")
public class IndustriesSection extends Section{
    @Root
    @FindBy(css = "div.tile-item.hidden-description")
    public Elements<Label> industries;
}
