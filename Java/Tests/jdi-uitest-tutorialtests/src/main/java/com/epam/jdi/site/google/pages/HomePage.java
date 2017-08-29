package com.epam.jdi.site.google.pages;

import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class HomePage extends WebPage {
    @FindBy(css = "[name=q]")
    public ITextField search;
    public void search(String text) {
        search.newInput(text + Keys.ENTER);
    }
}
