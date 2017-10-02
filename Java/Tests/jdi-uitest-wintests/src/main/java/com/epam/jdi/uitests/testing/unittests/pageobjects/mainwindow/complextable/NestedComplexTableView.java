package com.epam.jdi.uitests.testing.unittests.pageobjects.mainwindow.complextable;

import com.epam.jdi.uitests.testing.unittests.composite.DynamicTable;
import com.epam.jdi.uitests.win.winnium.elements.composite.Section;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.JTable;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.TableHeaderTypes;
import org.openqa.selenium.support.FindBy;

public class NestedComplexTableView extends Section {
    @JTable(root = @FindBy(className = "DataGrid"),
            columnHeadersInTableXpath = "/*[contains(@ControlType,'ControlType.Header')]/*[contains(@ControlType, 'ControlType.HeaderItem')]",
            rowsInTableXpath = "/*[contains(@ControlType,'ControlType.DataItem')]",
            columnsInRowXpath = "/*[contains(@ControlType,'ControlType.Custom')]",
            headerInRowXpath = "/*[contains(@ControlType,'ControlType.HeaderItem')]/*[contains(@ControlType, 'ControlType.Text')]",
            headerType = TableHeaderTypes.COLUMNS_HEADERS)
    public DynamicTable table;
}
