package com.epam.jdi.site.yandex.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.simple.ByText;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class SearchPage extends WebPage {
    @ByText("Картинки") public IButton pictures;
}
