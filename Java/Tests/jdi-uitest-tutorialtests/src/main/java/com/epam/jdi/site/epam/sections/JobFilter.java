package com.epam.jdi.site.epam.sections;

import com.epam.jdi.entities.JobSearchFilter;
import com.epam.jdi.enums.JobCategories;
import com.epam.jdi.enums.Locations;
import com.epam.jdi.site.epam.CustomElements.TreeDropdown;
import com.epam.jdi.uitests.core.interfaces.base.ISetValue;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import static java.util.Arrays.asList;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobFilter extends Form<JobSearchFilter> {
    @FindBy(className = "job-search-input")
    ITextField keywords;

    @JDropdown(
            root = @FindBy(className = "multi-select-department"),
            value = @FindBy(className = "multi-select-filter"),
            list = @FindBy(className = "blue-checkbox-label")
    )
    IDropDown<JobCategories> category;

    TreeDropdown<Locations> location = new TreeDropdown<>(
            By.className("career-location-box"),
            asList(By.cssSelector(".location-dropdown .optgroup"),
                By.xpath("//..//li")
            ));

    @FindBy(className = "job-search-button")
    IButton selectButton;

    @FindBy(className = "job-search-title")
    public ILabel label;

    @Override
    protected void setValueAction(String text, ISetValue element) {
        element.setValue(text);
        label.click();
    }
}
