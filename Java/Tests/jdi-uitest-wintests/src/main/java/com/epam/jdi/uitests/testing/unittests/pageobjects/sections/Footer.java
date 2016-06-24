package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.win.winium.elements.common.Link;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Fail on 15.09.2015.
 */
public class Footer extends Section {
    @FindBy(partialLinkText = "About")
    public Link about;

}
