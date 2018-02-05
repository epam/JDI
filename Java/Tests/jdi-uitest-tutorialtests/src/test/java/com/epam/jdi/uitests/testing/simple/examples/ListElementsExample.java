package com.epam.jdi.uitests.testing.simple.examples;

import com.epam.jdi.site.google.custom.SearchResult;
import com.epam.jdi.uitests.testing.GoogleTestsBase;
import com.epam.jdi.uitests.web.selenium.elements.complex.Elements;
import com.epam.web.matcher.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.StringUtils.LINE_BREAK;
import static com.epam.jdi.site.google.GoogleSite.homePage;
import static com.epam.jdi.site.google.GoogleSite.searchPage;


public class ListElementsExample extends GoogleTestsBase {
    @Test
    public void resultsAsList() {
        homePage.open();
        Assert.contains(homePage.getDriver().getCurrentUrl(),
                "https://www.google.");
        homePage.search("jdi");
        List<SearchResult> jobs = searchPage.jobsE;
//        Assert.areEquals(vacancies.size(), 10);
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
        String results1 = getJobs(jobs);
        //Assert.ignoreCase().each(select(vacancies,
        //        j -> j.description.getText())).contains("jdi");
        String results2 = getJobs(jobs);
        //Assert.ignoreCase().each(select(vacancies,
        //        j -> j.description.getText())).contains("jdi");
        homePage.search("testing");
        String results3 = getJobs(jobs);
        //Assert.ignoreCase().each(select(vacancies,
        //        j -> j.link.getText())).contains("testing");

        System.out.println(results1);
        System.out.println(results2);
        System.out.println(results3);
    }
    private String getJobs(Elements<SearchResult> jobs) {
        return "!!!" + String.join(LINE_BREAK, select(jobs, SearchResult::print));
    }


}
