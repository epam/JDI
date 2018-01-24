package com.epam.jdi.selenium.pageobject;
import com.epam.jdi.entities.Job;
import com.epam.jdi.enums.HeaderMenu;
import com.epam.jdi.site.epam.CustomElements.JobRecord;
import com.epam.jdi.site.epam.sections.SearchResultItem;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

/**
 * Created by Roman_Iovlev on 5/23/2017.
 */
public class SeleniumJdiPage {
    @FindBy(css = ".header__logo")
    public WebElement logo;

    @FindBy(css = "ul.top-navigation__list>li span a")
    public List<WebElement> menu;

    @FindBy(css = ".top-navigation__list")
    public Menu<HeaderMenu> headerMenu;

    @FindBy(css = ".job-search__submit")
    public WebElement selectButton;

    @Css(".search-result__list>.search-result__item")
    public Elements<JobRecord> jobs;
//    <Job, JobRecord> jobs;
}
