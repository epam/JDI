package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.win.winium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Natalia_Grebenshchik on 10/14/2015.
 */
public class ClickableTablePage extends WebPage {

    @FindBy(className = "uui-dynamic-table")
    public ITable clickableTable;

}
