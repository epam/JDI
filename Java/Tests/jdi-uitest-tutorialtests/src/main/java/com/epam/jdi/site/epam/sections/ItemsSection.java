package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.filterable-result")
public class ItemsSection extends Section{
    @Root
    @FindBy(css = "div.filterable-item")
    public Elements<Section> items;

    @FindBy(css = "img.filterable-item-image.image-replace")
    public Elements<Image> photos;
}
