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
public class Rows extends TableLine {
    public Rows() {
        hasHeader = false;
        elementIndex = ElementIndexType.Nums;
        headersLocator = By.xpath(".//tr/td[1]");
        defaultTemplate = By.xpath(".//tr[%s]/td");
    }

    protected List<WebElement> getHeadersAction() {
        return table.getWebElement().findElements(headersLocator);
    }

    protected List<WebElement> getFirstLine() {
        return table.columns().getLineAction(1);
    }

    protected int getCount() {
        List<WebElement> elements = getFirstLine();
        return elements != null ? elements.size() : 0;
    }

    public MapArray<String, MapArray<String, ICell>> get() {
        return new MapArray<>(headers(), key -> key, this::getRow);
    }

    ///

    public List<String> getRowValue(String rowName) {
        try {
            return select(table.rows().getLineAction(rowName), WebElement::getText);
        } catch (Exception | Error ex) {
            throw throwRowsException(rowName, ex.getMessage());
        }
    }

    public final MapArray<String, String> getRowAsText(String rowName) {
        return getRow(rowName).toMapArray(IText::getText);
    }

    public MapArray<String, ICell> cellsToRow(Collection<ICell> cells) {
        return new MapArray<>(cells,
                cell -> headers()[cell.columnNum() - 1],
                cell -> cell);
    }

    public MapArray<String, ICell> getRow(int rowNum) {
        if (count() < 0 || table.rows().count() < rowNum || rowNum <= 0)
            throw exception("Can't Get Row '%s'. [num] > ColumnsCount(%s).", rowNum, count());
        try {
            List<WebElement> webRow = getLineAction(rowNum);
            return new MapArray<>(table.columns().count(),
                    key -> table.columns().headers()[key],
                    value -> table.cell(webRow.get(value), new Column(value + 1), new Row(rowNum)));
        } catch (Exception | Error ex) {
            throw throwRowsException(Integer.toString(rowNum) + "", ex.getMessage());
        }
    }

    public List<String> getRowValue(int rowNum) {
        if (count() < 0 || count() < rowNum || rowNum <= 0)
            throw exception("Can't Get Row '%s'. [num] > ColumnsCount(%s).", rowNum, count());
        try {
            return select(table.rows().getLineAction(rowNum), WebElement::getText);
        } catch (Exception | Error ex) {
            throw throwRowsException(Integer.toString(rowNum), ex.getMessage());
        }
    }

    public final MapArray<String, String> getRowAsText(int rowNum) {
        return getRow(rowNum).toMapArray(IText::getText);
    }

    public final MapArray<String, ICell> getRow(String rowName) {
        try {
            String[] headers = table.columns().headers();
            return new MapArray<>(table.columns().count(),
                    key -> table.columns().headers[key],
                    value -> table.cell(getLineAction(rowName).get(value), new Column(headers[value]), new Row(rowName)));
        } catch (Exception | Error ex) {
            throw throwRowsException(rowName, ex.getMessage());
        }
    }

    private RuntimeException throwRowsException(String colName, String ex) {
        return exception("Can't Get Row '%s'. Exception: %s", colName, ex);
    }
}