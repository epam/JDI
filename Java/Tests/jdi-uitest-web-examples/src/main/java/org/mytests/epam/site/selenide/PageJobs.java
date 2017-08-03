package org.mytests.epam.site.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Roman_Iovlev on 2/21/2017.
 */
public class PageJobs {
    public static String url = "https://www.epam.com/careers/job-listings?query=test&department%5B%5D=all&city=St-Petersburg&country=Russia";

    public static SelenideElement applyLinkFor(String name, String category) {
        return $(By.xpath(String.format("//li[@class='search-result-item' and div[a[.='%s']] " +
            "and *[@class='department' and .='%s']]/*[@class='button-apply']/a", name, category)));
    }
    public static SelenideElement applyLinkFor(String name, String category, String location) {
        String locator = "//li[@class='search-result-item' and ";
        if (name != null && !name.equals(""))
            locator += "*[@class='position-name ' and a[.='"+name+"']]";
        if (category != null && !category.equals(""))
            locator += "*[@class='department' and .='"+category+"']";
        if (location != null && !location.equals(""))
            locator += "*[@class='location' and .='"+location+"']";
        locator += "]/*[@class='button-apply']/a";
        return $(By.xpath(locator));
    }
}
