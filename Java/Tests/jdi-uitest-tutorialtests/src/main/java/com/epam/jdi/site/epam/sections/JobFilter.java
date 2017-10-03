package com.epam.jdi.site.epam.sections;

import com.epam.jdi.entities.JobSearchFilter;
import com.epam.jdi.enums.Locations;
import com.epam.jdi.site.epam.CustomElements.JTree;
import com.epam.jdi.site.epam.CustomElements.TreeDropdown;
import com.epam.jdi.uitests.core.interfaces.base.ISetValue;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ClassName;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Value;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class JobFilter extends Form<JobSearchFilter> {
    @FindBy(className = "job-search-input") ITextField keywords;

    @JTree(
        select = @FindBy(className = "career-location-box"),
        levels = {@FindBy(css = ".location-dropdown .optgroup"),
                    @FindBy(xpath = "//..//li")}
    ) TreeDropdown<Locations> location;

    @Value("search")
    IButton selectButton;

    @ClassName("job-search-title")
    public ILabel label;

    @Override
    protected void setValueAction(String text, ISetValue element) {
        element.setValue(text);
        label.click();
    }
}
