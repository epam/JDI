package com.epam.jdi.uitests.win.winium.elements.complex.table.interfaces;
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
import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.win.winium.elements.base.SelectElement;
import com.epam.jdi.uitests.win.winium.elements.complex.table.*;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Created by roman.i on 20.10.2014.
 */

public interface ITable extends IText {
    /**
     * Get Cell by column/row index (Int) or name(String)
     */
    @JDIAction
    ICell cell(Column column, Row row);

    @JDIAction
    default ICell cell(String columnName, String rowName) {
        return cell(Column.column(columnName), Row.row(rowName));
    }

    @JDIAction
    default ICell cell(int columnIndex, int rowIndex) {
        return cell(Column.column(columnIndex), Row.row(rowIndex));
    }

    /**
     * Get all Cells with values equals to searched value
     */
    @JDIAction
    List<ICell> cells(String value);

    /**
     * Get all Cells with values matches to searched regex
     */
    @JDIAction
    List<ICell> cellsMatch(String regex);

    /**
     * Get first Cell with equals to searched value
     */
    @JDIAction
    ICell cell(String value);

    /**
     * Get first Cell with matches to searched regex
     */
    @JDIAction
    ICell cellMatch(String regex);

    /**
     * Searches Rows in table matches specified criteria colNameValues - list of search criteria in format columnName=columnValue<br>
     * e.g. rows("Name=Roman", "Profession=QA") <br>
     * Each Row is map: columnName:cell
     */
    @JDIAction
    MapArray<String, MapArray<String, ICell>> rows(String... colNameValues);

    /**
     * Searches Columns in table matches specified criteria rowNameValues - list of search criteria in format rowName=rowValue<br>
     * e.g. columns("Total=100", "Count=10") <br>
     * Each Column is map: rowName:cell
     */
    @JDIAction
    MapArray<String, MapArray<String, ICell>> columns(String... rowNameValues);

    /**
     * Waits while value appear in Row <br>
     * e.g. waitValue("100", row("Total")) <br>
     * or   waitValue("100", row(5))
     */
    @JDIAction
    boolean waitValue(String value, Row row);

    /**
     * Waits while value appear in Column <br>
     * e.g. waitValue("Roman", column("Name")) <br>
     * or   waitValue("Roman", column(3))
     */
    @JDIAction
    boolean waitValue(String value, Column column);

    /**
     * Indicates are any rows in table. Check immediately
     */
    @JDIAction
    boolean isEmpty();

    /**
     * Wait while at least one row appear in table
     */
    @JDIAction
    boolean waitHaveRows();

    /**
     * Wait while at least count of rows appear in table
     */
    @JDIAction
    boolean waitRows(int count);

    /**
     * Get first Cell with searched value in row by index (Int) or name(String)<br>
     * e.g. cell("100", row("Total")) <br>
     * or   cell("100", row(5))
     */
    @JDIAction
    ICell cell(String value, Row row);

    /**
     * Get first Cell with searched value in column by index (Int) or name(String)<br>
     * e.g. cell("Roman", column("Name")) <br>
     * or   cell("Roman", column(3))
     */
    @JDIAction
    ICell cell(String value, Column column);

    /**
     * Get all Cells with values matches to searched in Row by index (Int) or name(String) <br>
     * e.g. cellsMatch(".*uccess.*", row("Result")) <br>
     * or   cellsMatch(".*uccess.*", row(5))
     */
    @JDIAction
    List<ICell> cellsMatch(String regex, Row row);

    /**
     * Get all Cells with values matches to searched in Column by index (Int) or name(String) <br>
     * e.g. cellsMatch("Roma.*", column("Name")) <br>
     * or   cellsMatch("Roma.*", column(3))
     */
    @JDIAction
    List<ICell> cellsMatch(String regex, Column column);

    /**
     * Get Row cells for Cell with searched value in Column by index(Int) or name(String) <br>
     * e.g. row("Roman", column("Name")) <br>
     * or   row("Roman", column(3)) <br>
     * Each Row is map: columnName:cell
     */
    @JDIAction
    MapArray<String, ICell> row(String value, Column column);

    /**
     * Get Column cells for Cell with searched value in Row by index(Int) or name(String) <br>
     * e.g. column("100", row("Total") <br>
     * or   column("100", row(5)) <br>
     * Each Column is map: rowName:cell
     */
    @JDIAction
    MapArray<String, ICell> column(String value, Row row);

    Rows rows();

    /**
     * Get Row with index <br>
     * Each Row is map: columnName:cell
     */
    @JDIAction
    MapArray<String, ICell> row(int rowNum);

    /**
     * Get Row with name <br>
     * Each Row is map: columnName:cell
     */
    @JDIAction
    MapArray<String, ICell> row(String rowName);

    /**
     * Get Row value
     */
    @JDIAction
    List<String> rowValue(int colNum);

    /**
     * Get Row value
     */
    @JDIAction
    List<String> rowValue(String colName);

    Columns columns();

    /**
     * Get Column with index <br>
     * Each Column is map: rowName:cell
     */
    @JDIAction
    MapArray<String, ICell> column(int colNum);

    /**
     * Get Column with name <br>
     * Each Column is map: rowName:cell
     */
    @JDIAction
    MapArray<String, ICell> column(String colName);

    /**
     * Get Column value
     */
    @JDIAction
    List<String> columnValue(int colNum);

    /**
     * Get Column value
     */
    @JDIAction
    List<String> columnValue(String colName);

    /**
     * Get Header
     */
    @JDIAction
    MapArray<String, SelectElement> header();

    @JDIAction
    SelectElement header(String name);

    /**
     * Get Header
     */
    @JDIAction
    List<String> headers();

    /**
     * Get Footer
     */
    @JDIAction
    List<String> footer();

    /**
     * Get All Cells
     */
    @JDIAction
    List<ICell> getCells();

    /**
     * Clean all already founded Cells
     */
    void clean();

    /**
     * Similar to clean
     */
    void clear();

    ITable useCache(boolean value);
    default ITable useCache() { return useCache(true); }

    Table clone();

    Table copy();

    ITable setUp(By root, By cell, By row, By column, By footer, int colStartIndex, int rowStartIndex);

    ITable hasAllHeaders();

    ITable hasNoHeaders();

    ITable hasOnlyColumnHeaders();

    ITable hasOnlyRowHeaders();

    ITable hasColumnHeaders(List<String> value);

    <THeaders extends Enum> ITable hasColumnHeaders(Class<THeaders> headers);

    ITable hasRowHeaders(List<String> value);

    <THeaders extends Enum> ITable hasRowHeaders(Class<THeaders> headers);

    ITable setColumnsCount(int value);

    ITable setRowsCount(int value);
}