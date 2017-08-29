package com.epam.jdi.site.google.custom;

import com.epam.jdi.uitests.core.interfaces.common.ILabel;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 8/23/2017.
 */
public class SearchResult extends Section {
    @FindBy(css = "h3>a")
    public ILabel name;
    @FindBy(className = "st")
    public IText description;
    @FindBy(tagName = "cite")
    public ILink link;

    public String print() {
        return "SearchResult{" +
                "NAME=" + name.getText() +
                ", DESCRIPTION=" + description.getText() +
                ", LINK=" + link.getText() +
                '}';
    }
}
