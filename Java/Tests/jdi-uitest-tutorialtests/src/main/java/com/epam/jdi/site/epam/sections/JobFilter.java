package com.epam.jdi.site.epam.sections;

import com.epam.jdi.entities.JobSearchFilter;
import com.epam.jdi.enums.Locations;
import com.epam.jdi.site.epam.CustomElements.JTree;
import com.epam.jdi.site.epam.CustomElements.TreeDropdown;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.web.selenium.elements.common.Label;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ByClass;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Css;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobFilter extends Form<JobSearchFilter> {
    //Keyword or Vacancy ID
    @ByClass("job-search__input")
    ITextField keywords;

    //Skills
    @JDropdown(
            root = @FindBy(css = ".multi-select-filter"),
            expand = @FindBy(css = ".default-label"),
            list = @FindBy(css = ".multi-select-dropdown li"))
    public IDropDown category;

    @JTree(
            select = @FindBy(css = ".job-search__location"),
            levels = {@FindBy(css = "[role=tree]>li"),
                    @FindBy(xpath = "//..//li")}
    ) TreeDropdown<Locations> location;



    //Submit button
    @ByClass("job-search__submit")
    IButton selectButton;

    //Title
    @Css(".job-search-title")
    public Label label;

//    @Override
//    protected void setValueAction(String text, ISetValue element) {
//        element.setValue(text);
//        label.click();
//    }
}


