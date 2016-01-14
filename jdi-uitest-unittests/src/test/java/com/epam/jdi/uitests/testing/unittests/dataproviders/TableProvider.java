package com.epam.jdi.uitests.testing.unittests.dataproviders;

import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.DynamicTablePage;
import com.epam.jdi.uitests.testing.unittests.pageobjects.pages.SortingTablePage;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;

import java.util.function.Function;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDISite.supportPage;

/**
 * Created by Roman_Iovlev on 11/30/2015.
 */
public class TableProvider {
    private Function<Table, ITable> tableFunc;
    private Function<DynamicTablePage, Boolean> pageFunc;
    private Function<SortingTablePage, SortingTablePage> sortingPageFunc;
    private String name;
    private ITable table;
    private DynamicTablePage page;
    private SortingTablePage sortingPage;

    public TableProvider(String name, Function<Table, ITable> tableFunc) {
        this.name = name;
        this.tableFunc = tableFunc;
        table = supportPage.supportTable;
    }
    public TableProvider(String name, ITable table, Function<Table, ITable> tableFunc) {
        this.name = name;
        this.tableFunc = tableFunc;
        this.table = table;
    }

    public TableProvider(String name, ITable table, Function<Table, ITable> tableFunc, Function<DynamicTablePage, Boolean> pageFunc, DynamicTablePage page){
        this.name = name;
        this.table = table;
        this.tableFunc = tableFunc;
        this.pageFunc = pageFunc;

        this.page = page;
    }

    public TableProvider(String name, ITable table, Function<Table, ITable> tableFunc, Function<SortingTablePage, SortingTablePage> pageFunc, SortingTablePage page){
        this.name = name;
        this.table = table;
        this.tableFunc = tableFunc;
        this.sortingPageFunc = pageFunc;

        this.sortingPage = page;
    }

    public ITable getTable() {
        return tableFunc.apply(table.clone());
    }
    public void changeColumns() {
        pageFunc.apply(page);
    }
    public void changeRows(){
        sortingPageFunc.apply(sortingPage);
    }

    @Override
    public String toString() {
        return name;
    }
}
