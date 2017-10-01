package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.complex.IMenu;
import com.epam.jdi.uitests.web.selenium.elements.common.Image;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JMenu;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */

public class HomePage extends WebPage {

    @JFindBy(css = ".m-l8")
    public IMenu menu1;

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

    @JFindBy(css = ".search")
    public IButton openSearch;

    @JSearch(
            jroot = @JFindBy(css = ".search"),
            jInput = @JFindBy(tagName = "input"),
            jSearchButton = @JFindBy(css = ".icon-search")
    ) public Search jSearchRootInputSearchButton;

    @JSearch(
            jInput = @JFindBy(tagName = "input"),
            jSearchButton = @JFindBy(css = ".icon-search")
    ) public Search jSearchInputSearchButton;

    @JSearch(
            jroot = @JFindBy(css = ".search"),
            jInput = @JFindBy(tagName = "input")
    ) public Search jSearchRootInput;


    @JMenu(
            level1 = @JFindBy (css = ".uui-navigation.nav.navbar-nav.m-l8>li>a"),
            level2 = @JFindBy (css = ".dropdown-menu>li>a")
    ) public IMenu menu;
}
