package com.epam.jdi.site.yandex.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Id;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.Tag;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class HomePage extends WebPage {
    @Id("text") public ITextField searchYa;
    @Tag("button") public IButton searchButton;

    public void search(String key){
        searchYa.sendKeys(key);
        searchButton.click();
    }
}
