package com.epam.jdi.uitests.testing.career.page_objects.site.pages;

import com.epam.jdi.uitests.testing.career.page_objects.site.sections.JobFilter;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class CareerPage extends WebPage {
    @FindBy(className = "job-search")
    public JobFilter jobFilter;

    @FindBy(css = ".tile-menu>li>a")
    public List<Label> listMenu;


}
