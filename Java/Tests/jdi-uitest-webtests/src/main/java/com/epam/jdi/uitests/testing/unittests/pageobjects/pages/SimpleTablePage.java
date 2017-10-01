package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ITable;
import com.epam.jdi.uitests.testing.unittests.pageobjects.sections.JdiPaginator;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.By;

/**
 * Created by Natalia_Grebenshchik on 10/22/2015.
 */
public class SimpleTablePage extends WebPage {
    private Table simpleTable;
    public JdiPaginator pagination;



    @JTable(
            jRoot = @JFindBy(css = ".tbl-height"),
            header = {"Column 1",  "Column 2"}
    )public ITable table;

    public ITable getTable(By rowHeader, By columnHeader, By row, By column) {

        simpleTable = new Table(rowHeader, columnHeader, row, column, -1, -1);
        simpleTable.setAvatar(By.xpath("*//table"));

        return simpleTable;
    }
    public ITable getTable(By rowHeader, By columnHeader, By row, By column, int rowStartIndex, int columnStartIndex) {

        simpleTable = new Table(rowHeader, columnHeader, row, column, rowStartIndex, columnStartIndex);
        simpleTable.setAvatar(By.xpath("*//table"));

        return simpleTable;
    }

    public ITable getTable(By rowHeader, By columnHeader, int rowStartIndex, int columnStartIndex) {

        simpleTable = new Table(rowHeader, columnHeader, null, null, rowStartIndex, columnStartIndex);
        simpleTable.setAvatar(By.xpath("*//table"));

        return simpleTable;
    }

    public Table getTable(boolean hasRowHeaders, boolean hasColumnHeaders) {

        simpleTable = new Table();
        simpleTable.setAvatar(By.xpath("*//table"));

        simpleTable.hasAllHeaders();
        return simpleTable;
    }

    public ITable getTable(boolean hasRowHeaders, boolean hasColumnHeaders, By rowHeaderLocator, By columnHeaderLocator) {

        simpleTable = new Table(rowHeaderLocator, columnHeaderLocator, null, null, -1, -1);

        simpleTable.setAvatar(By.xpath("*//table"));

        simpleTable.hasAllHeaders();

        return simpleTable;
    }

}
