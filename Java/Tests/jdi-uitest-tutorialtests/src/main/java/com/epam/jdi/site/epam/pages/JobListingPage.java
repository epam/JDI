package com.epam.jdi.site.epam.pages;

import com.epam.jdi.site.epam.sections.SearchResultItem;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;

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
