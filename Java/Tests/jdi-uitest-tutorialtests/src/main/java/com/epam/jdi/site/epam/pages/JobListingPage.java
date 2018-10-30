package com.epam.jdi.site.epam.pages;

import com.epam.commons.map.MapArray;
import com.epam.jdi.entities.Vacancy;
import com.epam.jdi.site.epam.sections.VacancyRow;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.epam.commons.LinqUtils.where;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobListingPage extends WebPage {

    @JTable(root = @FindBy(css = ".search-result__list"),
            row = @FindBy(xpath = ".//article[%s]/*"),
            column = @FindBy(xpath = ".//article/*[%s]"),
            header = {"name", "description", "applyShareCell"})
    public EntityTable<Vacancy, VacancyRow> jobsAsData;

    @JTable(root = @FindBy(css = ".search-result__list"),
            row = @FindBy(xpath = ".//article[%s]/*"),
            column = @FindBy(xpath = ".//article/*[%s]"),
            header = {"name", "description", "applyShareCell"})
    public Table jobs;

    @Css(".search-result__list>.search-result__item")
    public Elements<VacancyRow> jobsList;

    public void getJobRowByName(String jobName) {
        List<VacancyRow> result = getRowsByName(jobName);
    //    result.get(0).apply.click();
    }

public List<VacancyRow> getRowsByName(String jobName) {
    return where(jobsList, job -> job.name.getText().equals(jobName));
}

/*    public void getJobRow(String jobName) {
        MapArray<String, ICell> row = jobsList.row(jobName, column(name));
        row.get(apply.toString()).select();
    }*/
}
