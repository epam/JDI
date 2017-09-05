package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.contactform;

import com.epam.jdi.uitests.win.winnium.elements.composite.TabItem;
import org.openqa.selenium.support.FindBy;

public class ContactFormTab extends TabItem {
    @FindBy(className = "ContactFormView")
    public NestedContactFormView nestedContactFormView;
}
