package com.epam.jdi.uitests.mobile.appium.elements.complex.table;
/*
 * Copyright 2004-2016 EPAM Systems
 *
 * This file is part of JDI project.
 *
 * JDI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JDI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */


import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.mobile.appium.elements.complex.table.interfaces.ICell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;

import static com.epam.commons.LinqUtils.select;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by 12345 on 26.10.2014.
 */
public class Columns extends TableLine {
    public Columns() {
        hasHeader = true;
        elementIndex = ElementIndexType.Nums;
        headersLocator = By.xpath(".//th");
        defaultTemplate = By.xpath(".//tr/td[%s]");
    }

    protected List<WebElement> getHeadersAction() {
        return table.getWebElement().findElements(headersLocator);
    }

    protected List<WebElement> getFirstLine() {
        return table.rows().getLineAction(1);
    }

    protected int getCount() {
        List<WebElement> elements = getFirstLine();
        return elements != null ? elements.size() : 0;
    }

    public final MapArray<String, ICell> getColumn(String colName) {
        try {
            String[] headers = table.rows().headers();
            List<WebElement> webColumn = getLineAction(colName);
            return new MapArray<>(table.rows().count(),
                    key -> table.rows().headers[key],
                    value -> table.cell(webColumn.get(value), new Column(colName), new Row(headers[value])));
        } catch (Exception | Error ex) {
            throw throwColumnException(colName, ex.getMessage());
        }
    }

    public final List<String> getColumnValue(String colName) {
        try {
            return select(table.columns().getLineAction(colName), WebElement::getText);
        } catch (Exception | Error ex) {
            throw throwColumnException(colName, ex.getMessage());
        }
    }

    public final MapArray<String, String> getColumnAsText(String colName) {
        return getColumn(colName).toMapArray(IText::getText);
    }

    public MapArray<String, ICell> cellsToColumn(Collection<ICell> cells) {
        return new MapArray<>(cells,
                cell -> headers()[cell.rowNum() - 1],
                cell -> cell);
    }

    public final MapArray<String, ICell> getColumn(int colNum) {
        if (count() < 0 || table.columns().count() < colNum || colNum <= 0)
            throw exception("Can't Get Column '%s'. [num] > RowsCount(%s).", colNum, count());
        try {
            List<WebElement> webColumn = getLineAction(colNum);
            return new MapArray<>(table.rows().count(),
                    key -> table.rows().headers()[key],
                    value -> table.cell(webColumn.get(value), new Column(colNum), new Row(value + 1)));
        } catch (Exception | Error ex) {
            throw throwColumnException(colNum + "", ex.getMessage());
        }
    }

    public final List<String> getColumnValue(int colNum) {
        if (count() < 0 || count() < colNum || colNum <= 0)
            throw exception("Can't Get Column '%s'. [num] > RowsCount(%s).", colNum, count());
        try {
            return select(table.columns().getLineAction(colNum), WebElement::getText);
        } catch (Exception | Error ex) {
            throw throwColumnException(colNum + "", ex.getMessage());
        }
    }

    public final MapArray<String, String> getColumnAsText(int colNum) {
        return getColumn(colNum).toMapArray(IText::getText);
    }

    public MapArray<String, MapArray<String, ICell>> get() {
        return new MapArray<>(headers(), key -> key, this::getColumn);
    }

    private RuntimeException throwColumnException(String rowName, String ex) {
        return exception("Can't Get Column '%s'. Exception: %s", rowName, ex);
    }
}