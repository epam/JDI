package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.complextable;

import com.epam.jdi.uitests.win.winnium.elements.composite.TabItem;
import org.openqa.selenium.support.FindBy;

public class ComplexTableTab extends TabItem {
    @FindBy(className = "ComplexTableView")
    public NestedComplexTableView nestedComplexTableView;
}
