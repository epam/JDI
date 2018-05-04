package com.epam.jdi.uitests.testing.unittests.pageobjects;

import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.*;
import com.epam.jdi.uitests.testing.unittests.pageobjects.sections.Footer;
import com.epam.jdi.uitests.testing.unittests.pageobjects.sections.Header;
import com.epam.jdi.uitests.web.selenium.elements.complex.TextList;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 9/10/2015.
 */

//@JSite(value = "https://jdi-framework.github.io/tests")
@JSite("https://epam.github.io/JDI")
public class EpamJDISite extends WebSite {

    @JPage(url = "/index.html", title = "Home Page")
    public static HomePage homePage;
    @JPage(url = "/contacts.html", title = "Contact Form")
    public static ContactPage contactFormPage;
    @JPage(url = "/metals-colors.html", title = "Metal and Colors")
    public static MetalsColorsPage metalsColorsPage;
    @JPage(url = "/support.html", title = "Support")
    public static SupportPage supportPage;
    @JPage(url = "/table-pages.html", title = "Support")
    public static SortingTablePage sortingTablePage;
    @JPage(url = "/complex-table.html", title = "Table sort")
    public static DynamicTablePage dynamicTablePage;
    @JPage(url = "/simple-table.html", title = "Table Scroll")
    public static SimpleTablePage simpleTablePage;
    @JPage(url = "/dates.html", title = "Simple Table")
    public static DatesPage dates;
    @FindBy(css = ".uui-profile-menu")
    public static Login login;
    @FindBy(css = ".uui-header")
    public static Header header;
    @FindBy(css = ".footer-content")
    public static Footer footer;
    @FindBy(css = ".logs li")
    public static TextList actionsLog;

}