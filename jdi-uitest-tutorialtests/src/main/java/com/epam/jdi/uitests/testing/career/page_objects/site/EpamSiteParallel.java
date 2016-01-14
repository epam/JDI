package com.epam.jdi.uitests.testing.career.page_objects.site;

import com.epam.jdi.uitests.testing.career.page_objects.enums.HeaderMenu;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.CareerPage;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.HomePage;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.JobDescriptionPage;
import com.epam.jdi.uitests.testing.career.page_objects.site.pages.JobListingPage;
import com.epam.jdi.uitests.web.selenium.driver.DriverTypes;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.Site;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import org.openqa.selenium.support.FindBy;

import static com.epam.jdi.uitests.web.selenium.elements.composite.CheckPageTypes.CONTAIN;
import static com.epam.jdi.uitests.web.selenium.elements.composite.CheckPageTypes.MATCH;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "http://www.epam.com")
public class EpamSiteParallel extends Site {
    @JPage(url = "/", title = "EPAM | Software Product Development Services")
    public HomePage homePage;
    @JPage(url = "/careers", title = "Careers")
    public CareerPage careerPage;
    @JPage(url = "/careers/job-listings", title = "Job Listings", urlCheckType = CONTAIN, titleCheckType = CONTAIN)
    public JobListingPage jobListingPage;
    @JPage(url = ".*/careers/job-listings/job\\.\\d*#apply", urlCheckType = MATCH)
    public JobDescriptionPage jobDescriptionPage;

    @FindBy(css = ".tile-menu>li>a")
    public Menu<HeaderMenu> headerMenu;

    public EpamSiteParallel() { }
    public EpamSiteParallel(DriverTypes driver) { super(driver); }
}
