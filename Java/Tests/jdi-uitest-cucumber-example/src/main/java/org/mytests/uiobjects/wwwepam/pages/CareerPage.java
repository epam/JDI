package org.mytests.uiobjects.wwwepam.pages;

import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class CareerPage extends WebPage {
    @FindBy(css = ".job-search-input")
    public ITextField keywords;
}
