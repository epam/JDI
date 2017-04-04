package com.epam.jdi.site.epam.pages;

import com.epam.jdi.site.epam.sections.JobFilter;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class CareerPage extends WebPage {
    @FindBy(className = "job-search-form-ui")
    public JobFilter jobFilter;

    @FindBy(css = ".tile-menu>li>a")
    public List<Label> listMenu;

}
