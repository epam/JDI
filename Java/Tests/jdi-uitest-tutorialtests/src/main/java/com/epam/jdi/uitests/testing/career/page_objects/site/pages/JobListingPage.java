package com.epam.jdi.uitests.testing.career.page_objects.site.pages;

import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.support.FindBy;

import static com.epam.jdi.uitests.testing.career.page_objects.enums.JobListHeaders.APPLY;
import static com.epam.jdi.uitests.testing.career.page_objects.enums.JobListHeaders.JOB_NAME;
import static com.epam.jdi.uitests.web.selenium.elements.complex.table.Column.column;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobListingPage extends WebPage {

    @JTable(
        root = @FindBy(className = "search-result-list"),
        row = @FindBy(xpath = ".//li[%s]//div"),
        column = @FindBy(xpath = ".//li//div[%s]"),
        header = {"JOB_NAME", "JOB_CATEGORY", "JOB_LOCATION", "APPLY"})
    public ITable jobsList;

    public void getJobRowByName(String jobName) {
        MapArray<String, ICell> row = jobsList.row(jobName, column(JOB_NAME.toString()));
        row.get(APPLY.toString()).select();
    }
}
