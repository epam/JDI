package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.dates;

import com.epam.jdi.uitests.win.winnium.elements.composite.TabItem;
import org.openqa.selenium.support.FindBy;

public class DatesTab extends TabItem {
    @FindBy(className = "DatesView")
    public NestedDatesView nestedDatesView;
}
