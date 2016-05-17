package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;

import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

/**
 * Created by 12345 on 17.05.2016.
 */
public class FillFromAnnotationRules {

    public static void setUpTable(ITable table, JTable jTable) {
        table.setUp(findByToBy(jTable.root()), findByToBy(jTable.cell()),
                findByToBy(jTable.row()), findByToBy(jTable.column()), findByToBy(jTable.footer()),
                jTable.colStartIndex(), jTable.rowStartIndex());

        if (jTable.header().length > 0)
            table.hasColumnHeaders(asList(jTable.header()));
        if (jTable.rowsHeader().length > 0)
            table.hasRowHeaders(asList(jTable.rowsHeader()));

        if (jTable.height() > 0)
            table.setColumnsCount(jTable.height());
        if (jTable.width() > 0)
            table.setRowsCount(jTable.width());
        if (!jTable.size().equals("")) {
            String[] split;
            split = jTable.size().split("x");
            if (split.length == 1)
                split = jTable.size().split("X");
            if (split.length != 2)
                throw exception("Can't setup Table from attribute. Bad size: " + jTable.size());
            table.setColumnsCount(parseInt(split[0]));
            table.setRowsCount(parseInt(split[1]));
        }

        switch (jTable.headerType()) {
            case COLUMN_HEADERS:
                table.hasOnlyColumnHeaders();
            case ROWS_HEADERS:
                table.hasOnlyRowHeaders();
            case ALL_HEADERS:
                table.hasAllHeaders();
            case NO_HEADERS:
                table.hasNoHeaders();
        }
        table.useCache(jTable.useCache());
    }

    public static void setUpDropdown(Dropdown dropdown, JDropdown jDropdown) {
        dropdown.setUp(findByToBy(jDropdown.root()), findByToBy(jDropdown.value()),
                findByToBy(jDropdown.list()), findByToBy(jDropdown.expand()),
                findByToBy(jDropdown.elementByName()));
    }
}
