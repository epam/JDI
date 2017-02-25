package org.mytests.epam.site;

import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.*;
import org.mytests.epam.enums.HeaderMenu;
import org.mytests.epam.site.pages.HomePage;
import org.mytests.epam.site.pages.JobDescriptionPage;
import org.mytests.epam.site.pages.JobsPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class EpamSite extends WebSite {

    @JPage(url = "https://www.epam.com/careers/job-listings?query=qa&department%5B%5D=all&city=St-Petersburg&country=Russia")
    public static JobsPage jobsPage;
    @JPage
    public static JobDescriptionPage jobDescriptionPage;

    @FindBy(css = ".tile-menu>li>a")
    public static Menu<HeaderMenu> headerMenu;

}
