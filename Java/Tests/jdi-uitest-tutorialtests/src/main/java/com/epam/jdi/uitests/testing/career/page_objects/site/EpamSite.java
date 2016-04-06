package com.epam.jdi.uitests.testing.career.page_objects.site;

import com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu;
import com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderSolutionsMenu;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.CareerPage;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.HomePage;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.JobDescriptionPage;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.JobListingPage;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import static com.epam.jdi.uitests.web.selenium.elements.composite.CheckPageTypes.CONTAIN;
import static com.epam.jdi.uitests.web.selenium.elements.composite.CheckPageTypes.MATCH;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "https://www.epam.com")
public class EpamSite extends WebSite {

    @JPage(url = "/", title = "EPAM | Software Product Development Services")
    public static HomePage homePage;
    @JPage(url = "/careers", title = "Careers")
    public static CareerPage careerPage;
    @JPage(url = "/careers/job-listings", title = "Job Listings", urlCheckType = CONTAIN, titleCheckType = CONTAIN)
    public static JobListingPage jobListingPage;
    @JPage(url = ".*/careers/job-listings/job\\.\\d*#apply", urlCheckType = MATCH)
    public static JobDescriptionPage jobDescriptionPage;

    @FindBy(css = ".tile-menu>li>a")
    public static Menu<HeaderMenu> headerMenu;

    @FindBy(css = ".tile-menu>li>a")
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
}
