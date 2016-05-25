package com.epam.jdi.uitests.testing.career.page_objects.site.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.career.page_objects.entities.JobSearchFilter;
import com.epam.jdi.uitests.testing.career.page_objects.enums.JobCategories;
import com.epam.jdi.uitests.testing.career.page_objects.enums.Locations;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobFilter extends Form<JobSearchFilter> {
    @FindBy(className = "job-search-input")
    ITextField keywords;
    IDropDown<JobCategories> category =
            new Dropdown<>(By.className("multi-select-filter"), By.className("blue-checkbox-label"));

    @FindBy(className = "career-location-box")
    IDropDown<Locations> city;

    @FindBy(className = "job-search-button")
    IButton selectButton;

}
