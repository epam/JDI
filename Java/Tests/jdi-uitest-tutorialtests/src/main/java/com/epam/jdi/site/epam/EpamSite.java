package com.epam.jdi.site.epam;

import com.epam.jdi.enums.HeaderMenu;
import com.epam.jdi.enums.HeaderSolutionsMenu;
import com.epam.jdi.site.epam.pages.*;
import com.epam.jdi.site.epam.sections.ContactUsSection;
import com.epam.jdi.site.epam.sections.Header;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JMenu;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
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
//            "/careers/job-listings?sort=best_match&query=Engineer&department=Software+Test+Engineering&city=St-Petersburg&country=Russia",
            urlTemplate = "/careers/job-listings", title = "Job Listings",
            urlCheckType = CONTAINS, titleCheckType = CONTAINS)
    public static JobListingPage jobListingPage;

    @JPage(url = "/careers/job-listings/job.11516#apply", urlTemplate = ".*/careers/job-listings/job\\.\\d*#apply",
            urlCheckType = MATCH)
    public static JobDescriptionPage jobDescriptionPage;

    @FindBy(css = ".top-navigation__list")
    public static Menu<HeaderMenu> headerMenu;

    @JMenu(level1 = @JFindBy(css = ".hamburger-menu__button"),
            level2 = @JFindBy(css = "ul.hamburger-menu__list>li a"),
            level3 = @JFindBy(css = "ul.hamburger-menu__sub-list>li a"))
    public static Menu multipleHeaderMenu;

    @Css(".tile-menu>li>a")
    public static Elements<Button> listMenu;

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

    @JPage(url = "/our-work", title = "Our Work")
    public static ourWorkPage ourWorkPage;

    @JPage(url = "/insights", title = "Insights")
    public static InsightsPage insightsPage;

    public static Header header;
    public static ContactUsSection contactUs;
}
