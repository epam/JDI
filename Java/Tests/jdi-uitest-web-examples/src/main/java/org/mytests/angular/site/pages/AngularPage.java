package org.mytests.angular.site.pages;

import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.mytests.angular.site.CustomElements.AngularCheckbox;
import org.mytests.angular.site.CustomElements.Toggle;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class AngularPage extends WebPage {
    public ICheckBox acceptTerms = new AngularCheckbox("tos");
    public Toggle specialOptions = new Toggle("special");

}
