package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 9/11/2015.
 */
public class Header extends Section {

    @FindBy(xpath = "//img[@src=\"label/Logo_Epam_Color.svg\"]")
    public IImage image;

    public JdiSearch search;
}
