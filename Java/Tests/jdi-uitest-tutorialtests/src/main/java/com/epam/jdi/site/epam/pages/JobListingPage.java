package com.epam.jdi.site.epam.pages;

import com.epam.commons.map.MapArray;
import com.epam.jdi.entities.Job;
import com.epam.jdi.site.epam.CustomElements.JobRecord;
import com.epam.jdi.site.epam.sections.SearchResultItem;
import com.epam.jdi.site.google.custom.SearchResult;
import com.epam.jdi.uitests.core.annotations.Title;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.EntityTable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ByTag;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobListingPage extends WebPage {

    @Css(".search-result__list>.search-result__item")
    public Elements<SearchResultItem> jobsList;

    public void getJobRowByName(String jobName) {
        List<SearchResultItem> result = jobsList.stream()
                .filter(job -> job.name.getText().equals(jobName))
                .collect(Collectors.toList());
        result.get(0).apply.click();
    }

//    public void getJobRow(String jobName) {
//        MapArray<String, ICell> row = jobsList.row(jobName, column(name));
//        row.get(apply.toString()).select();
//    }
}
