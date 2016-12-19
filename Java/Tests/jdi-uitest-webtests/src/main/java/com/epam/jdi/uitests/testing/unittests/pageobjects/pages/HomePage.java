package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */

public class HomePage extends WebPage {

    @FindBy(css = ".main-title")
    public Label title;
    @FindBy(css = ".main-txt")
    public Text text;
    @FindBy(css = ".epam-logo img")
    public Image logoImage;
    @FindBy(linkText = "About")
    public Link about;

    @FindBy(css = "[class=icon-search]")
    public IButton openSearchButton;

    public Label titleConstructor = new Label(By.cssSelector(".main-title"));

    @JFindBy(text = "About")
    public Link aboutJ;

    public Search search;
}
