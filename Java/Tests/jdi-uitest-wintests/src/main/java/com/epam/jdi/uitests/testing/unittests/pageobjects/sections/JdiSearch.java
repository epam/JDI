package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.win.winium.elements.common.Button;
import com.epam.jdi.uitests.win.winium.elements.composite.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 12/17/2015.
 */
public class JdiSearch extends Search {
    @FindBy(css = ".search-field input")
    public ITextField searchInput;

    @FindBy(css = ".search .search-active .icon-search")
    public IButton searchButton;

    @Override
    public void findAction(String text) {
        new Button(By.cssSelector(".search>.icon-search")).click();
        super.findAction(text);
    }

}
