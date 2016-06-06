package com.epam.jdi.uitests.testing.career.page_objects.site.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

public class BasePage extends WebPage {
    @FindBy(css = "public")
    public IButton publicParent;
    @FindBy(css = "private")
    private Table privateParent;
}
