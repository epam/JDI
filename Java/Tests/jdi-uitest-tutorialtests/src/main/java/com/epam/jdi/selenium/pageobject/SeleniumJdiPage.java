package com.epam.jdi.selenium.pageobject;

import com.epam.jdi.entities.Job;
import com.epam.jdi.enums.HeaderMenu;
import com.epam.jdi.site.epam.CustomElements.JobRecord;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Roman_Iovlev on 5/23/2017.
 */
public class SeleniumJdiPage {
    @FindBy(css = ".logo")
    public WebElement logo;
    @FindBy(css = ".tile-menu>li>a")
    public List<WebElement> menu;
    @FindBy(css = ".tile-menu>li>a")
    public Menu<HeaderMenu> headerMenu;
    @JTable(
            root = @FindBy(className = "search-result-list"),
            row = @FindBy(xpath = ".//li[%s]//div"),
            column = @FindBy(xpath = ".//li//div[%s]"),
            header = {"name", "category", "location", "apply"})
    public EntityTable<Job, JobRecord> jobs = new EntityTable<>(Job.class, JobRecord.class);
}
