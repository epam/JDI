package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 8/17/2015.
 */
public class SupportPage extends WebPage {
    @FindBy(css = ".uui-table")
    public ITable supportTable;
    @FindBy(css = ".uui-table")
    public ITable tableWithHeaders = new Table().hasAllHeaders();
    @FindBy(css = ".uui-table")
    public ITable tableWithoutHeaders = new Table().hasNoHeaders();

}
