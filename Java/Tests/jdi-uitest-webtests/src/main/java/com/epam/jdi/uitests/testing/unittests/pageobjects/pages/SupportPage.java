package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
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
    @JTable(
            root = @FindBy(css = "root locator"),
            header = {"header1", "header2"},
            rowsHeader = {"row1", "row2"},
            cell = @FindBy(css = "root locator"),
            row = @FindBy(css = "root locator"),
            column = @FindBy(css = "root locator"),
            useCache = false,
            height = 5,
            width = 1,
            colStartIndex = 1,
            rowStartIndex = 1)
    public ITable jTable;

}
