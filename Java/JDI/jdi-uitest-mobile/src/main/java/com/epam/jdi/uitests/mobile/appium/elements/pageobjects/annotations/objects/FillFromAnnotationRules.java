package com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.objects;

import com.epam.jdi.uitests.mobile.appium.elements.complex.Dropdown;
import com.epam.jdi.uitests.mobile.appium.elements.complex.Menu;
import com.epam.jdi.uitests.mobile.appium.elements.complex.table.Table;
import com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.AppiumAnnotationsUtil;

import static com.epam.commons.LinqUtils.select;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.AppiumAnnotationsUtil.findByToBy;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

/**
 * Created by 12345 on 17.05.2016.
 */
public class FillFromAnnotationRules {

    public static void setUpTable(Table table, JTable jTable) {
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
            String[] split = jTable.size().split("x");
            if (split.length == 1)
                split = jTable.size().split("X");
            if (split.length != 2)
                throw exception("Can't setup Table from attribute. Bad size: " + jTable.size());
            table.setColumnsCount(parseInt(split[0]));
            table.setRowsCount(parseInt(split[1]));
        }

        switch (jTable.headerType()) {
            case COLUMNS_HEADERS:
                table.hasOnlyColumnHeaders();
                break;
            case ROWS_HEADERS:
                table.hasOnlyRowHeaders();
                break;
            case ALL_HEADERS:
                table.hasAllHeaders();
                break;
            case NO_HEADERS:
                table.hasNoHeaders();
                break;
        }
        table.useCache(jTable.useCache());
    }

    public static void setUpDropdown(Dropdown dropdown, JDropdown jDropdown) {
        dropdown.setUp(findByToBy(jDropdown.root()), findByToBy(jDropdown.value()),
                findByToBy(jDropdown.list()), findByToBy(jDropdown.expand()),
                findByToBy(jDropdown.elementByName()));
    }
    public static void setUpMenu(Menu menu, JMenu jMenu) {
        menu.setUp(select(asList(jMenu.levelLocators()), AppiumAnnotationsUtil::findByToBy));
        if (!jMenu.separator().equals(""))
            menu.useSeparator(jMenu.separator());
    }
}
