package org.mytests.angular.site.CustomElements;

import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import org.openqa.selenium.By;

import static com.epam.jdi.uitests.web.selenium.elements.base.JdiStatic.$;

/**
 * Created by Roman_Iovlev on 2/24/2017.
 */
public class AngularCheckbox extends CheckBox{
    public By isCheckedLocator;
    public AngularCheckbox(String name) {
        super(By.cssSelector("[name=" + name + "] .md-container"));
        isCheckedLocator = By.cssSelector("[name=" + name + "]");
    }

    @Override
    protected boolean isCheckedAction() {
        return $(isCheckedLocator).attr("aria-checked").equals("true");
    }
}
