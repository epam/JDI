package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 9/11/2015.
 */
public class Header extends Section {

    @FindBy(xpath = "//img[@src='images/Logo_Epam_Color.svg']")
    public IImage image;

    @FindBy(xpath = "//div[@class='search-field']/input[@type='text']")
    public ITextField searchInput;

    @FindBy(xpath = "//span[@class='icon-search']")
    public IButton searchButton;

    public JdiSearch jdiSearch;

}


