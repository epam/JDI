package org.mytests.epam.site.site.pages;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.mytests.epam.site.entities.Job;
import org.mytests.epam.site.site.CustomElements.JobRecord;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobsPage extends WebPage {
    @JTable(
            root = @FindBy(className = "search-result-list"),
            row = @FindBy(xpath = ".//li[%s]//div"),
            column = @FindBy(xpath = ".//li//div[%s]"),
            header = {"name", "category", "location", "apply"})
    public EntityTable<Job, JobRecord> jobs;

}
