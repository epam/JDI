package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.IImage;
import com.epam.jdi.uitests.testing.unittests.pageobjects.sections.HeaderMenu;
import com.epam.jdi.uitests.win.winium.elements.common.Text;
import com.epam.jdi.uitests.win.winium.elements.common.TextArea;
import com.epam.jdi.uitests.win.winium.elements.composite.Search;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import org.openqa.selenium.remote.ScreenshotException;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
//TODO
public class HomePage extends WebPage {
    @FindBy(id = "menu")
    public HeaderMenu headerMenu;
    @FindBy(id="logTextBox")
    public TextArea log;


    // TODO
    @FindBy(css = ".main-txt")
    public Text text;
    @FindBy(css = ".epam-logo img")
    public IImage logoImage;
    @FindBy(css = "[class=icon-search]")
    public IButton openSearchButton;

    public Search search;
}
