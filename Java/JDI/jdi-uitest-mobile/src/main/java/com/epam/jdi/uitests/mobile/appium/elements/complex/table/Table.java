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
import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.mobile.appium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.mobile.appium.elements.base.SelectElement;
import com.epam.jdi.uitests.mobile.appium.elements.common.Text;
import com.epam.jdi.uitests.mobile.appium.elements.complex.table.interfaces.ICell;
import com.epam.jdi.uitests.mobile.appium.elements.complex.table.interfaces.ITable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.epam.commons.EnumUtils.getAllEnumNamesAsArray;
import static com.epam.commons.LinqUtils.*;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.Timer.waitCondition;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Roman_Iovlev on 6/2/2015.
 */
public class Table extends Text implements ITable, Cloneable {
    public boolean cache = true;
    protected String[] footer;
    protected By cellLocatorTemplate;
    private List<ICell> allCells = new ArrayList<>();
    private Columns columns = new Columns();
    private Rows rows = new Rows();
    private By footerLocator = By.xpath(".//tfoot//th");

    // ------------------------------------------ //

    public Table() {
        columns.table = this;
        rows.table = this;
        //GetFooterFunc = t => t.FindElements(By.xpath("//tfoot/tr/td")).Select(el => el.Text).ToArray();
    }

    public Table(By tableLocator) {
        super(tableLocator);
        columns.table = this;
        rows.table = this;
    }

    public Table(By columnHeader, By row, By column) {
        this();
        columns.lineTemplate = column;
        columns.headersLocator = columnHeader;
        rows.lineTemplate = row;
    }

    public Table(By rowHeader, By columnHeader, By row, By column, int rowStartIndex, int columnStartIndex) {
        this();
        if (column != null)
            columns.lineTemplate = column;
        if (columnHeader != null)
            columns.headersLocator = columnHeader;
        if (row != null)
            rows.lineTemplate = row;
        if (rowHeader != null)
            rows.headersLocator = rowHeader;

        if (columnStartIndex > -1)
            columns.startIndex = columnStartIndex;
        if (rowStartIndex > -1)
            rows.startIndex = rowStartIndex;
    }

    public Table(By tableLocator, By cellLocatorTemplate) {
        this(tableLocator);
        this.cellLocatorTemplate = cellLocatorTemplate;
    }

    public Table(By columnHeader, By rowHeader, By row, By column, By footer, TableSettings settings,
                 int columnStartIndex, int rowStartIndex) {
        this();
        columns.lineTemplate = column;
        if (columnHeader != null)
            columns.headersLocator = columnHeader;
        rows.lineTemplate = row;
        if (rowHeader != null)
            rows.headersLocator = rowHeader;
        footerLocator = footer;

        columns.startIndex = columnStartIndex;
        rows.startIndex = rowStartIndex;

        setTableSettings(settings);
    }

    public Table(TableSettings settings) {
        this();
        setTableSettings(settings);
    }

    public Table copy() {
        return clone();
    }

    public Table clone() {
        asserter.silent(() -> super.clone());
        Table newTable = new Table();
        newTable.rows = rows().clone(new Rows(), newTable);
        newTable.columns = columns().clone(new Columns(), newTable);
        newTable.avatar = new GetElementModule(getLocator(), getAvatar().context, newTable);
        return newTable;
    }

    public List<ICell> getCells() {
        List<ICell> result = new ArrayList<>();
        MapArray<String, MapArray<String, ICell>> rows = rows().get();
        for (String columnName : columns().headers())
            for (String rowName : rows().headers())
                result.add(rows.get(rowName).get(columnName));
        if (cache)
            allCells = result;
        return result;
    }

    public ITable useCache() {
        cache = true;
        return this;
    }

    public void clean() {
        allCells = new ArrayList<>();
        columns().clean();
        rows().clean();
    }

    public void clear() {
        clean();
    }

    public Columns columns() {
        return columns;
    }

    public MapArray<String, ICell> column(int colNum) {
        return columns().getColumn(colNum);
    }

    public MapArray<String, ICell> column(String colName) {
        return columns().getColumn(colName);
    }

    public List<String> columnValue(int colNum) {
        return columns().getColumnValue(colNum);
    }

    public List<String> columnValue(String colName) {
        return columns().getColumnValue(colName);
    }

    private MapArray<String, ICell> column(Column column) {
        return column.get(this::column, this::column);
    }

    public void setColumns(Columns value) {
        columns.update(value);
    }

    public Rows rows() {
        return rows;
    }

    public MapArray<String, ICell> row(int rowNum) {
        return rows().getRow(rowNum);
    }

    public MapArray<String, ICell> row(String rowName) {
        return rows().getRow(rowName);
    }

    public List<String> rowValue(int rowNum) {
        return rows().getRowValue(rowNum);
    }

    public List<String> rowValue(String rowName) {
        return rows().getRowValue(rowName);
    }

    public void setTableSettings(TableSettings settings) {
        rows().hasHeader = settings.rowHasHeaders;
        rows().headers = settings.rowHeaders;
        rows().count = settings.rowsCount;
        columns().hasHeader = settings.columnHasHeaders;
        columns().headers = settings.columnHeaders;
        columns().count = settings.columnsCount;
    }

    private MapArray<String, ICell> row(Row row) {
        return row.get(this::row, this::row);
    }

    public void setRows(Rows value) {
        rows.update(value);
    }

    public ITable hasAllHeaders() {
        columns().hasHeader = true;
        rows().hasHeader = true;
        return this;
    }

    public ITable hasNoHeaders() {
        columns().hasHeader = false;
        rows().hasHeader = false;
        return this;
    }

    public ITable hasOnlyColumnHeaders() {
        columns().hasHeader = true;
        rows().hasHeader = false;
        return this;
    }

    public ITable hasOnlyRowHeaders() {
        columns().hasHeader = false;
        rows().hasHeader = true;
        return this;
    }


    public ITable hasColumnHeaders(String[] value) {
        columns().hasHeader = true;
        columns().setHeaders(value);
        return this;
    }

    public <THeaders extends Enum> ITable hasColumnHeaders(Class<THeaders> headers) {
        return hasColumnHeaders(getAllEnumNamesAsArray(headers));
    }

    public ITable hasRowHeaders(String[] value) {
        rows().hasHeader = true;
        rows().setHeaders(value);
        return this;
    }

    public <THeaders extends Enum> ITable hasRowHeaders(Class<THeaders> headers) {
        return hasRowHeaders(getAllEnumNamesAsArray(headers));
    }

    public ITable setColumnsCount(int value) {
        columns().setCount(value);
        return this;
    }

    public ITable setRowsCount(int value) {
        rows().setCount(value);
        return this;
    }

    protected String[] getFooterAction() {
        return select(getWebElement().findElements(By.xpath(".//tfoot/tr/th")), WebElement::getText)
                .toArray(new String[1]);
    }

    private String[] getFooter() {
        return footer.clone();
    }

    public void setFooter(String[] value) {
        footer = value.clone();
    }

    public final MapArray<String, SelectElement> header() {
        return columns().header();
    }

    public final SelectElement header(String name) {
        return columns().header(name);
    }

    public String[] headers() {
        return columns().headers();
    }

    public String[] footer() {
        if (footer != null)
            return getFooter();
        footer = invoker.doJActionResult("Get Footer", this::getFooterAction);
        if (footer == null || footer.length == 0)
            return new String[]{};
        columns().setCount(footer.length);
        return getFooter();
    }

    public ICell cell(Column column, Row row) {
        int colIndex = column.get(this::getColumnIndex, num -> num + columns().getStartIndex() - 1);
        int rowIndex = row.get(this::getRowIndex, num -> num + rows().getStartIndex() - 1);
        return addCell(colIndex, rowIndex,
                column.get(name -> asList(columns().headers()).indexOf(name) + 1, num -> num),
                row.get(name -> asList(rows().headers()).indexOf(name) + 1, num -> num),
                column.get(name -> name, num -> ""),
                row.get(name -> name, num -> ""));
    }

    public ICell cell(WebElement webElement, Column column, Row row) {
        return addCell(webElement,
                column.get(name -> asList(columns().headers()).indexOf(name) + 1, num -> num),
                row.get(name -> asList(rows().headers()).indexOf(name) + 1, num -> num),
                column.get(name -> name, num -> ""),
                row.get(name -> name, num -> ""));
    }

    private List<ICell> matches(Collection<ICell> list, String regex) {
        return new ArrayList<>(where(list, cell -> cell.getValue().matches(regex)));
    }

    public List<ICell> cells(String value) {
        return new ArrayList<>(where(getCells(), cell -> cell.getValue().equals(value)));
    }

    public List<ICell> cellsMatch(String regex) {
        return matches(getCells(), regex);
    }

    public ICell cell(String value) {
        ICell result;
        for (Pair<String, MapArray<String, ICell>> row : rows().get()) {
            result = row.value.first((cName, cValue) -> cValue.getText().equals(value));
            if (result != null)
                return result;
        }
        return null;
    }

    public ICell cellMatch(String regex) {
        ICell result;
        for (Pair<String, MapArray<String, ICell>> row : rows().get()) {
            result = row.value.first((cName, cValue) -> cValue.getText().matches(regex));
            if (result != null)
                return result;
        }
        return null;
    }

    public MapArray<String, MapArray<String, ICell>> rows(String... colNameValues) {
        MapArray<String, MapArray<String, ICell>> result = new MapArray<>();
        for (Pair<String, MapArray<String, ICell>> row : rows().get()) {
            boolean matches = true;
            for (String colNameValue : colNameValues) {
                if (!colNameValue.matches("[^=]+=[^=]*"))
                    throw exception("Wrong searchCriteria for Cells: " + colNameValue);
                String[] splitted = colNameValue.split("=");
                String colName = splitted[0];
                String colValue = splitted[1];
                ICell cell = row.value.get(colName);
                if (cell == null || !cell.getValue().equals(colValue)) {
                    matches = false;
                    break;
                }
            }
            if (matches) result.add(row);
        }
        return result;
    }

    public MapArray<String, MapArray<String, ICell>> columns(String... rowNameValues) {
        MapArray<String, MapArray<String, ICell>> result = new MapArray<>();
        for (Pair<String, MapArray<String, ICell>> column : columns().get()) {
            boolean matches = true;
            for (String rowNameValue : rowNameValues) {
                if (!rowNameValue.matches("[^=]+=[^=]*"))
                    throw exception("Wrong searchCritaria for Cells: " + rowNameValue);
                String[] splitted = rowNameValue.split("=");
                String rowName = splitted[0];
                String rowValue = splitted[1];
                ICell cell = column.value.get(rowName);
                if (cell == null || !cell.getValue().equals(rowValue)) {
                    matches = false;
                    break;
                }
            }
            if (matches) result.add(column);
        }
        return result;
    }

    public boolean waitValue(String value, Row row) {
        return timer().wait(() -> column(value, row) != null);
    }

    public boolean waitValue(String value, Column column) {
        return waitCondition(() -> row(value, column) != null);
    }

    public boolean isEmpty() {
        getDriver().manage().timeouts().implicitlyWait(0, MILLISECONDS);
        int rowsCount = rows().count();
        getDriver().manage().timeouts().implicitlyWait(timeouts.waitElementSec, SECONDS);
        return rowsCount == 0;
    }

    public boolean waitHaveRows() {
        return waitRows(1);
    }

    public boolean waitRows(int count) {
        return waitCondition(() -> rows().count() >= count);
    }

    public ICell cell(String value, Row row) {
        int rowNum = (row.haveName())
                ? asList(rows().headers()).indexOf(row.getName()) + 1
                : row.getNum();
        return rows().getRow(rowNum).first((name, cell) -> cell.getValue().equals(value));
    }

    public ICell cell(String value, Column column) {
        int colIndex = column.get(
                name -> asList(columns().headers()).indexOf(name) + 1,
                num -> num);
        return columns().getColumn(colIndex).first((name, cell) -> cell.getValue().equals(value));
    }

    public List<ICell> cellsMatch(String regex, Column column) {
        MapArray<String, ICell> columnLine = column(column);
        return matches(columnLine.values(), regex);
    }

    public List<ICell> cellsMatch(String regex, Row row) {
        MapArray<String, ICell> rowLine = row(row);
        return matches(rowLine.values(), regex);
    }

    public MapArray<String, ICell> column(String value, Row row) {
        ICell columnCell = cell(value, row);
        return columnCell != null ? columns().getColumn(columnCell.columnNum()) : null;
    }

    public MapArray<String, ICell> row(String value, Column column) {
        ICell rowCell = cell(value, column);
        return rowCell != null ? rows().getRow(rowCell.rowNum()) : null;
    }

    private int getColumnIndex(String name) {
        int nameIndex;
        String[] headers = columns().headers();
        if (headers != null && asList(headers).contains(name))
            nameIndex = asList(headers).indexOf(name);
        else
            throw exception("Can't Get Column: '" + name + "'. " + ((headers == null)
                    ? "ColumnHeaders is Null"
                    : ("Available ColumnHeaders: " + print(headers, ", ", "'{0}'") + ")")));
        return nameIndex + columns().getStartIndex();
    }

    private int getRowIndex(String name) {
        int nameIndex;
        String[] headers = rows().headers();
        if (headers != null && asList(headers).contains(name))
            nameIndex = asList(headers).indexOf(name);
        else
            throw exception("Can't Get Row: '%s'. Available RowHeaders: (%s)", name, print(headers, ", ", "'{0}'"));
        return nameIndex + rows().getStartIndex();
    }

    @Override
    protected String getTextAction() {
        return "||X||" + print(columns().headers(), "|") + "||\n"
                + print(select(rows().headers(),
                        rowName -> "||" + rowName + "||" + print(select(where(getCells(),
                                cell -> cell.rowName().equals(rowName)), ICell::getValue), "|") + "||"), "\n");
    }

    private Cell addCell(int colIndex, int rowIndex, int colNum, int rowNum, String colName, String rowName) {
        Cell cell = (Cell) first(allCells, c -> c.columnNum() == colNum && c.rowNum() == rowNum);
        if (cell != null)
            return cell.updateData(colName, rowName);
        cell = new Cell(colIndex, rowIndex, colNum, rowNum, colName, rowName, cellLocatorTemplate, this);
        cell.setAvatar(cell.get().getAvatar());

        if (cache)
            allCells.add(cell);
        return cell;
    }

    private Cell addCell(WebElement webElement, int colNum, int rowNum, String colName, String rowName) {
        Cell cell = (Cell) first(allCells, c -> c.columnNum() == colNum && c.rowNum() == rowNum);
        if (cell != null) {
            cell.setWebElement(webElement);
            return cell.updateData(colName, rowName);
        }
        cell = new Cell(webElement, colNum, rowNum, colName, rowName, cellLocatorTemplate, this);
        if (cache)
            allCells.add(cell);
        return cell;
    }
}