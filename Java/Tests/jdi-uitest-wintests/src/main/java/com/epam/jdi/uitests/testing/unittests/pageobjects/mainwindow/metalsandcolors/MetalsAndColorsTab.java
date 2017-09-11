package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.metalsandcolors;

import com.epam.jdi.uitests.win.winnium.elements.composite.TabItem;
import org.openqa.selenium.support.FindBy;

public class MetalsAndColorsTab extends TabItem {
    @FindBy(className = "MetalsAndColorsView")
    public NestedMetalsAndColorsView nestedMetalsAndColorsView;
}
