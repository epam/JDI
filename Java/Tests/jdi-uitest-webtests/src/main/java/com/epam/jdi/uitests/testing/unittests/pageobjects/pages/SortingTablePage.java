package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.complex.ISelector;
import com.epam.jdi.uitests.testing.unittests.enums.RowNumbers;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.Pagination;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;


/**
 * Created by Natalia_Grebenshchik on 10/14/2015.
 */
public class SortingTablePage extends WebPage {

    @FindBy(id = "DataTables_Table_0")
    public Table sortingTable;

    public Pagination tablePagination = new Pagination(By.id("DataTables_Table_0_wrapper"),
            By.linkText("Next"),
            By.linkText("Previous"));

    @FindBy(name = "DataTables_Table_0_length")
    public ISelector rowsNumberInPageDD;

    @FindBy(xpath = ".//input[@type='search']")
    public TextField search;

    public SortingTablePage setRowCount(RowNumbers number) {
        this.rowsNumberInPageDD.select(number.value);

        return this;
    }

    public SortingTablePage sortTableByHeader(String columnName) {
        sortingTable.header(columnName).click();

        return this;
    }

    public void search(String value) {
        search.input(value);
    }
}
