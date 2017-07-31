package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.composite.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 12/17/2015.
 */
public class JdiSearch extends Search {
    @FindBy(css = ".search-field input")
    public ITextField searchInput;
    //It does not work for now
    @FindBy(xpath = "//span[@class='icon-search active']")
    public IButton searchButton;

    @Override
    public void findAction(String text) {
        new Button(By.cssSelector(".search>.icon-search")).click();
        searchInput.newInput(text);
        //super.findAction(text);
        searchButton.click();
    }

}
