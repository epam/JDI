package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = ".detail-pages-list__holder")
public class InsightsSection extends Section{
    @Root
    @FindBy(css = ".detail-pages-list__item")
    public Elements<Section> items;

    @FindBy(css = ".detail-pages-list__img")
    public Elements<Image> photos;
}
