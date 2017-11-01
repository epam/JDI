package com.epam.jdi.site.epam.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.acs-commons-resp-colctrl-row.colctrl-row.section")
public class SolutionsSection extends Section{
    @Root
    @FindBy(xpath = "//div[@class = 'no section-full-width section-ui section-color-white']")
    public Section solutions;
}
