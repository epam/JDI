package com.epam.jdi.uitests.testing.career.page_objects.site.pages;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Job;
import com.epam.jdi.uitests.testing.career.page_objects.site.CustomElements.JobRecord;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.jdi.uitests.testing.career.page_objects.enums.JobListHeaders.*;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Column.column;

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
    public EntityTable<Job, JobRecord> jobList = new EntityTable<>(Job.class, JobRecord.class);

    public void getJobRowByName(String jobName) {
        JobRecord row = jobList.getRow(jobName, column(name.toString()));
        List<JobRecord> rows = jobList.getRows();
        Job job = jobList.entity(jobName, column(name.toString()));
        List<Job> jobs = jobList.entities();
        row.apply.click();
    }
    public void getJobRow(String jobName) {
        MapArray<String, ICell> row = jobsList.row(jobName, column(name.toString()));
        row.get(apply.toString()).select();
    }
}
