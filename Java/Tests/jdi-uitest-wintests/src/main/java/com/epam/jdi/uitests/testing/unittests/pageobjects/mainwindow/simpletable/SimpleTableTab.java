package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.simpletable;

import com.epam.jdi.uitests.win.winnium.elements.composite.TabItem;
import org.openqa.selenium.support.FindBy;

public class SimpleTableTab extends TabItem {
    @FindBy(className = "SimpleTableView")
    public NestedSimpleTableView nestedSimpleTableView;
}
