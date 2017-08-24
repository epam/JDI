package com.epam.jdi.uitests.web.selenium.elements.complex.table;

import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ITable;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDynamicTable;
import org.openqa.selenium.By;

import java.lang.reflect.Field;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;
import static java.lang.Integer.parseInt;
import static java.util.Arrays.asList;

/**
 * Created by 12345 on 10.05.2016.
 */
public class DynamicTable extends Table {

    public DynamicTable() {
        super();
        cache = false;
    }

    public static void setUp(BaseElement el, Field field) {
        if (!fieldHasAnnotation(field, JDynamicTable.class, ITable.class)) {
            return;
        }
        ((DynamicTable) el).setUp(field.getAnnotation(JDynamicTable.class));
    }

    public ITable setUp(JDynamicTable jDynamicTable) {
        By root = findByToBy(jDynamicTable.root());
        if (root == null) {
            root = findByToBy(jDynamicTable.jRoot());
        }
        setAvatar(root);
        By headers = findByToBy(jDynamicTable.headers());
        By rowNames = findByToBy(jDynamicTable.rowNames());
        cellLocatorTemplate = findByToBy(jDynamicTable.cell());
        columns.lineTemplate = findByToBy(jDynamicTable.column());
        rows.lineTemplate = findByToBy(jDynamicTable.row());
        footerLocator = findByToBy(jDynamicTable.footer());
        columns.startIndex = jDynamicTable.colStartIndex();
        rows.startIndex = jDynamicTable.rowStartIndex();
        if (headers == null) {
            headers = findByToBy(jDynamicTable.jHeaders());
        }
        if (rowNames == null) {
            rowNames = findByToBy(jDynamicTable.jRowNames());
        }
        if (cellLocatorTemplate == null) {
            cellLocatorTemplate = findByToBy(jDynamicTable.jCell());
        }
        if (columns.lineTemplate == null) {
            columns.lineTemplate = findByToBy(jDynamicTable.jColumn());
        }
        if (rows.lineTemplate == null) {
            rows.lineTemplate = findByToBy(jDynamicTable.jRow());
        }
        if (footerLocator == null) {
            footerLocator = findByToBy(jDynamicTable.jFooter());
        }
        if (headers != null) {
            columns.headersLocator = headers;
        }
        if (rowNames != null) {
            rows.headersLocator = rowNames;
        }
        if (jDynamicTable.header().length > 0) {
            hasColumnHeaders(asList(jDynamicTable.header()));
        }
        if (jDynamicTable.rowsHeader().length > 0) {
            hasRowHeaders(asList(jDynamicTable.rowsHeader()));
        }
        if (jDynamicTable.height() > 0) {
            setColumnsCount(jDynamicTable.height());
        }
        if (jDynamicTable.width() > 0) {
            setRowsCount(jDynamicTable.width());
        }
        if (!jDynamicTable.size().equals("")) {
            String[] split = jDynamicTable.size().split("x");
            if (split.length == 1) {
                split = jDynamicTable.size().split("X");
            }
            if (split.length != 2) {
                throw exception("Can't setup Table from attribute. Bad size: " + jDynamicTable.size());
            }
            setColumnsCount(parseInt(split[0]));
            setRowsCount(parseInt(split[1]));
        }

        switch (jDynamicTable.headerType()) {
            case COLUMNS_HEADERS:
                hasOnlyColumnHeaders();
            case ROWS_HEADERS:
                hasOnlyRowHeaders();
            case ALL_HEADERS:
                hasAllHeaders();
            case NO_HEADERS:
                hasNoHeaders();
        }
        useCache(false);
        return this;
    }
}
