package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.site.google.custom.SearchResult;
import com.epam.jdi.uitests.testing.GoogleTestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.jdi.site.google.GoogleSite.homePage;
import static com.epam.jdi.site.google.GoogleSite.searchPage;


public class ListElementsExample extends GoogleTestsBase {
    @Test
    public void resultsAsList() {
        homePage.open();
        Assert.contains(homePage.getDriver().getCurrentUrl(),
                "https://www.google.");
        homePage.search("jdi");
        List<SearchResult> jobs = searchPage.jobsL;
//        Assert.areEquals(jobs.size(), 10);
        for (SearchResult job : jobs)
            System.out.println(job.print());
    }
    @Test
    public void resultsAsElements() {
        homePage.open();
        Assert.contains(homePage.getDriver().getCurrentUrl(),
                "https://www.google.");
        homePage.search("jdi");
        Assert.isTrue(searchPage.gitHubJdi.isDisplayed());
        Elements<SearchResult> jobs = searchPage.jobsE;
//        Assert.areEquals(jobs.size(), 10);
        for (SearchResult job : jobs)
            System.out.println(job.print());
    }

}
