package com.epam.jdi.uitests.testing.unittests.pageobjects.w3cSite;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */

public class FrameSection extends Section {
    @FindBy(tagName = "button")
    public Button label;
}
