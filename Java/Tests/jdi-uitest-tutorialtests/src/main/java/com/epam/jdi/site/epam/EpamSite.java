package com.epam.jdi.site.epam;

import com.epam.jdi.enums.HeaderMenu;
import com.epam.jdi.enums.HeaderSolutionsMenu;
import com.epam.jdi.site.epam.pages.*;
import com.epam.jdi.site.epam.sections.Header;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JMenu;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes.CONTAINS;
import static com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.CheckPageTypes.MATCH;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite("https://www.epam.com")
public class EpamSite extends WebSite {

    @JPage(url = "/", title = "EPAM|Software Product Development Services")
    public static HomePage homePage;

    @JPage("/careers")
    public static CareerPage careerPage;
    public static ProductDevelopmentPage productDevelopmentPage;

    @JPage(url = "/careers/job-listings?query=test&country=Russia&city=St-Petersburg&department=Software+Test+Engineering",
            urlTemplate = "/careers/job-listings", title = "Job Listings",
            urlCheckType = CONTAINS, titleCheckType = CONTAINS)
    public static JobListingPage jobListingPage;


    @JPage(url = "/careers/job-listings/job.11516", urlTemplate = ".*/careers/job-listings/job\\.\\d*",
            urlCheckType = MATCH)
    public static JobDescriptionPage jobDescriptionPage;

    @JPage(url = "/our-work", title = "Our Work")
    public static ourWorkPage ourWorkPage;

    @JPage(url = "/contact-us", title = "Contact Us | EPAM Systems")
    public static ContactUs contactUs;

    @JPage(url = "/insights", title = "Insights")
    public static InsightsPage insightsPage;

    @FindBy(css = ".top-navigation__list")
    public static Menu<HeaderMenu> headerMenu;

    @JMenu(level1 = @JFindBy(css = "ul.top-navigation__list>li span a"),
           level2 = @JFindBy(css = "ul.top-navigation__grand-sub-list>li a"))
    public static Menu multipleHeaderMenu;

    @FindBy(css = ".tile-menu .submenu a")
    public static Menu<HeaderSolutionsMenu> headerSolutionsMenu = new Menu<HeaderSolutionsMenu>() {

        @Override
        protected void selectAction(String name) {
            Actions action = new Actions(getDriver());
            WebElement el = getDriver().findElements(By.cssSelector(".tile-menu a")).get(0);
            action.moveToElement(el).build().perform();
            super.selectAction(name);
        }
    };

    public static Header header;
}
