package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow;

import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.complextable.ComplexTableTab;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.contactform.ContactFormTab;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.dates.DatesTab;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.metalsandcolors.MetalsAndColorsTab;
import com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.simpletable.SimpleTableTab;
import com.epam.jdi.uitests.win.winnium.elements.complex.Menu;
import com.epam.jdi.uitests.win.winnium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

public class MainTabPane extends Section {
    @FindBy(id = "ContactFormTab")
    public ContactFormTab contactFormTab;

    @FindBy(id = "Support%s")
    public Menu supportButton;

    @FindBy(id = "DatesView")
    public DatesTab datesTab;

    @FindBy(id = "ComplexTableView")
    public ComplexTableTab complexTablePageTab;

    @FindBy(id = "SimpleTableView")
    public SimpleTableTab simpleTableTab;

    @FindBy(id = "MetalsAndColorsView")
    public MetalsAndColorsTab metalsAndColorsTab;
}
