package org.mytests.uiobjects.wwwepam;

import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JSite;
import org.mytests.uiobjects.wwwepam.enums.HeaderMenu;
import org.mytests.uiobjects.wwwepam.pages.CareerPage;
import org.mytests.uiobjects.wwwepam.pages.HomePage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "https://www.epam.com")
public class EpamSite extends WebSite {

    @JPage(url = "/", title = "EPAM | Software Product Development Services")
    public static HomePage homePage;

    @FindBy(css = ".tile-menu>li>a")
    public static Menu<HeaderMenu> headerMenu;

    @JPage(url = "/careers", title = "Careers")
    public static CareerPage careerPage;
}
