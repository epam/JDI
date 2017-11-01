package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.annotations.Root;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

@FindBy(css = "div.main-content")
public class HomeSection extends Section{
    @Root
    @FindBy(css = "div.row.clerafix.benefits")
    public Section benefits;

    @FindBy(css = "span.icons-benefit.icon-practise")
    public Element practise;
}
