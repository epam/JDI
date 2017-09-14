package com.epam.jdi.site.epam.pages;

import com.epam.commons.map.MapArray;
import com.epam.jdi.entities.Job;
import com.epam.jdi.site.epam.CustomElements.JobRecord;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.support.FindBy;

import static com.epam.jdi.enums.JobListHeaders.apply;
import static com.epam.jdi.enums.JobListHeaders.name;
import static com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column.column;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobListingPage extends WebPage {

    @JTable(
        root = @FindBy(className = "search-result-list"),
        row = @FindBy(xpath = ".//li[%s]//div"),
        column = @FindBy(xpath = ".//li//div[%s]"),
        header = {"name", "category", "location", "apply"})
    public ITable jobsList;

    @JTable(
            root = @FindBy(className = "search-result-list"),
            row = @FindBy(xpath = ".//li[%s]//div"),
            column = @FindBy(xpath = ".//li//div[%s]"),
            header = {"name", "category", "location", "apply"})
    public EntityTable<Job, JobRecord> jobsListEntity = new EntityTable<>(Job.class, JobRecord.class);

    public void getJobRowByName(String jobName) {
        JobRecord row = jobsListEntity.getRow(jobName, column(name.toString()));
        row.apply.click();
    }
    public void getJobRow(String jobName) {
        MapArray<String, ICell> row = jobsList.row(jobName, column(name.toString()));
        row.get(apply.toString()).select();
    }
}
