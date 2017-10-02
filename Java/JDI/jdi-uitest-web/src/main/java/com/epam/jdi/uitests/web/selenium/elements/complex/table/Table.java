package com.epam.jdi.uitests.web.selenium.elements.complex.table;
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
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.base.ISetup;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.*;
import com.epam.jdi.uitests.web.selenium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.epam.commons.EnumUtils.getAllEnumNames;
import static com.epam.commons.EnumUtils.getEnumValue;
import static com.epam.commons.LinqUtils.*;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.Timer.waitCondition;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.WebAnnotationsUtil.findByToBy;
import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.FillFromAnnotationRules.fieldHasAnnotation;
import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by Roman_Iovlev on 6/2/2015.
 */
public class Table extends Text implements ITable, Cloneable, ISetup {
    public boolean cache = true;
    protected List<String> footer;
    protected By cellLocatorTemplate;
    protected List<ICell> allCells = new ArrayList<>();
    protected Columns columns = new Columns();
    protected Rows rows = new Rows();
    protected By footerLocator = By.xpath(".//tfoot/tr/th");

    // ------------------------------------------ //

    public Table() {
        this(By.tagName("table"));
        //GetFooterFunc = t => t.FindElements(By.xpath("//tfoot/tr/td")).Select(el => el.Text).ToArray();
    }

    public Table(By tableLocator) {
        super(tableLocator);
        columns.table = this;
        rows.table = this;
    }

    public Table(By columnHeader, By row, By column) {
        this();
        if (column != null)
            columns.lineTemplate = column;
        if (columnHeader != null)
            columns.headersLocator = columnHeader;
        if (row != null)
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

    public Table(By columnHeader, By rowHeader, By row, By column, By footer,
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

    }

    public Table copy() {
        return clone();
    }

    public Table clone() {
        Table newTable = new Table();
        newTable.rows = ((Rows)rows()).clone(new Rows(), newTable);
        newTable.columns = ((Columns)columns()).clone(new Columns(), newTable);
        newTable.avatar = new GetElementModule(getLocator(), newTable);
        return newTable;
    }

    public List<ICell> getCells() {
        List<ICell> result = new ArrayList<>();
        for (String columnName : columns().headers())
            //it was so
            // columns().headers().forEach(rowName-> result.add(cell(columnName, rowName)))
            rows().headers().forEach(rowName
                -> result.add(cell(columnName, rowName)));
        if (cache)
            allCells = result;
        return result;
    }

    public void setup(Field field) {
        if (!fieldHasAnnotation(field, JTable.class, ITable.class))
            return;
        JTable jTable = field.getAnnotation(JTable.class);
        By root = findByToBy(jTable.root());
        if (root == null)
            root = findByToBy(jTable.jRoot());
        setAvatar(root);
        By headers = findByToBy(jTable.headers());
        By rowNames = findByToBy(jTable.rowNames());
        cellLocatorTemplate = findByToBy(jTable.cell());
        columns.lineTemplate = findByToBy(jTable.column());
        rows.lineTemplate = findByToBy(jTable.row());
        footerLocator = findByToBy(jTable.footer());
        columns.startIndex = jTable.colStartIndex();
        rows.startIndex = jTable.rowStartIndex();
        if (headers == null)
            headers = findByToBy(jTable.jHeaders());
        if (rowNames == null)
            rowNames = findByToBy(jTable.jRowNames());
        if (cellLocatorTemplate == null)
            cellLocatorTemplate = findByToBy(jTable.jCell());
        if (columns.lineTemplate == null)
            columns.lineTemplate = findByToBy(jTable.jColumn());
        if (rows.lineTemplate == null)
            rows.lineTemplate = findByToBy(jTable.jRow());
        if (footerLocator == null)
            footerLocator = findByToBy(jTable.jFooter());

        if (headers != null)
            columns.headersLocator = headers;
        if (rowNames != null)
            rows.headersLocator = rowNames;

        if (jTable.header().length > 0)
            hasColumnHeaders(asList(jTable.header()));
        if (jTable.rowsHeader().length > 0)
            hasRowHeaders(asList(jTable.rowsHeader()));

        if (jTable.height() > 0)
            setColumnsCount(jTable.height());
        if (jTable.width() > 0)
            setRowsCount(jTable.width());
        if (!jTable.size().equals("")) {
            String[] split = jTable.size().split("x");
            if (split.length == 1)
                split = jTable.size().split("X");
            if (split.length != 2)
                throw exception("Can't setup Table from attribute. Bad size: " + jTable.size());
            setColumnsCount(parseInt(split[0]));
            setRowsCount(parseInt(split[1]));
        }

        switch (jTable.headerType()) {
            case COLUMNS_HEADERS:
                hasOnlyColumnHeaders();
                break;
            case ROWS_HEADERS:
                hasOnlyRowHeaders();
                break;
            case ALL_HEADERS:
                hasAllHeaders();
                break;
            case NO_HEADERS:
                hasNoHeaders();
                break;
        }
        useCache(jTable.useCache());
    }

    public ITable useCache(boolean value) {
        cache = value;
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

    public IColumn columns() {
        return columns;
    }

    public MapArray<String, ICell> column(int colNum) {
        return columns().getColumn(colNum);
    }

    public MapArray<String, ICell> column(String colName) {
        return columns().getColumn(colName);
    }

    private MapArray<String, ICell> column(Column column) {
        return column.get(this::column, this::column);
    }

    public List<String> columnValue(int colNum) {
        return columns().getColumnValue(colNum);
    }

    public List<String> columnValue(String colName) {
        return columns().getColumnValue(colName);
    }

    public void setColumns(Columns value) {
        columns.update(value);
    }

    public IRow rows() {
        return rows;
    }

    public MapArray<String, ICell> row(int rowNum) {
        return rows().getRow(rowNum);
    }

    public MapArray<String, ICell> row(String rowName) {
        return rows().getRow(rowName);
    }

    private MapArray<String, ICell> row(Row row) {
        return row.get(this::row, this::row);
    }

    public List<String> rowValue(int rowNum) {
        return rows().getRowValue(rowNum);
    }

    public List<String> rowValue(String rowName) {
        return rows().getRowValue(rowName);
    }

    public void setRows(Rows value) {
        rows.update(value);
    }

    public ITable hasAllHeaders() {
        ((Columns) columns()).hasHeader = true;
        ((Rows)rows()).hasHeader = true;
        return this;
    }

    public ITable hasNoHeaders() {
        ((Columns) columns()).hasHeader = false;
        ((Rows)rows()).hasHeader = false;
        return this;
    }

    public ITable hasOnlyColumnHeaders() {
        ((Columns) columns()).hasHeader = true;
        ((Rows)rows()).hasHeader = false;
        return this;
    }

    public ITable hasOnlyRowHeaders() {
        ((Columns) columns()).hasHeader = false;
        ((Rows)rows()).hasHeader = true;
        return this;
    }


    public ITable hasColumnHeaders(List<String> value) {
        ((Columns) columns()).hasHeader = true;
        ((Columns) columns()).headers = new ArrayList<>(value);
        return this;
    }

    public <THeaders extends Enum> ITable hasColumnHeaders(Class<THeaders> headers) {
        return hasColumnHeaders(getAllEnumNames(headers));
    }

    public ITable hasRowHeaders(List<String> value) {
        ((Rows)rows()).hasHeader = true;
        ((Rows)rows()).headers = new ArrayList<>(value);
        return this;
    }

    public <THeaders extends Enum> ITable hasRowHeaders(Class<THeaders> headers) {
        return hasRowHeaders(getAllEnumNames(headers));
    }

    public ITable setColumnsCount(int value) {
        columns().setCount(value);
        return this;
    }

    public ITable setRowsCount(int value) {
        rows().setCount(value);
        return this;
    }

    protected List<String> getFooterAction() {
        return select(getWebElement()
            .findElements(footerLocator), WebElement::getText);
    }

    private List<String> getFooter() {
        return new ArrayList<>(footer);
    }

    public void setFooter(List<String> value) {
        footer = new ArrayList<>(value);
    }

    public final MapArray<String, ISelect> header() {
        return columns().header();
    }

    public final ISelect header(String name) {
        return columns().header(name);
    }
    public final <TEnum extends Enum> ISelect header(TEnum enumName) {
        return columns().header(getEnumValue(enumName));
    }

    public List<String> headers() {
        return columns().headers();
    }

    public List<String> footer() {
        if (footer != null)
            return getFooter();
        footer = invoker.doJActionResult("Get Footer", this::getFooterAction);
        if (footer == null || footer.size() == 0)
            return new ArrayList<>();
        columns().setCount(footer.size());
        return getFooter();
    }

    public ICell cell(Column column, Row row) {
        int colIndex = column.get(this::getColumnIndex, num -> num + columns().getStartIndex() - 1);
        int rowIndex = row.get(this::getRowIndex, num -> num + rows().getStartIndex() - 1);
        return addCell(colIndex, rowIndex,
                column.get(name -> columns().headers().indexOf(name) + 1, num -> num),
                row.get(name -> rows().headers().indexOf(name) + 1, num -> num),
                column.get(name -> name, num -> ""),
                row.get(name -> name, num -> ""));
    }

    public ICell cell(WebElement webElement, Column column, Row row) {
        return addCell(webElement,
                column.get(name -> columns().headers().indexOf(name) + 1, num -> num),
                row.get(name -> rows().headers().indexOf(name) + 1, num -> num),
                column.get(name -> name, num -> ""),
                row.get(name -> name, num -> ""));
    }

    private List<ICell> contains(Collection<ICell> list, String value) {
        return new ArrayList<>(where(list, cell -> cell.getValue().contains(value)));
    }
    private List<ICell> matches(Collection<ICell> list, String regex) {
        return new ArrayList<>(where(list, cell -> cell.getValue().matches(regex)));
    }

    public List<ICell> cells(String value) {
        return new ArrayList<>(where(getCells(), cell -> cell.getValue().equals(value)));
    }

    public List<ICell> cellsContains(String value) {
        return contains(getCells(), value);
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
    public MapArray<String, MapArray<String, ICell>> rows(String value, Column column) {
        return rows().withValue(value, column);
    }
    public MapArray<String, MapArray<String, ICell>> rowsContains(String value, Column column) {
        return rows().containsValue(value, column);
    }
    public MapArray<String, MapArray<String, ICell>> rowsMatches(String regEx, Column column) {
        return rows().matchesRegEx(regEx, column);
    }

    public MapArray<String, MapArray<String, ICell>> rows(String... colNameValues) {
        if (colNameValues.length == 0)
            return rows().get();
        List<TableFilter> filters = new ArrayList<>();
        for (String colNameValue : colNameValues)
            filters.add(new TableFilter(colNameValue));
        boolean matches = false;
        MapArray<String, MapArray<String, ICell>> result = new MapArray<>();
        for (Pair<String, MapArray<String, ICell>> row : rows().get()) {
            for (TableFilter filter : filters) {
                ICell cell = row.value.get(filter.name);
                if (cell == null)
                    throw exception(format("Search rows for '%s' failed. Can't get cell for column named %s",
                            print(colNameValues), filter.name));
                switch (filter.type) {
                    case EQUAL:
                        matches = cell.getValue().equals(filter.value);
                        break;
                    case CONTAINS:
                        matches = cell.getValue().contains(filter.value);
                        break;
                    case MATCH:
                        matches = cell.getValue().matches(filter.value);
                        break;
                }
                if (!matches) break;
            }
            if (matches) result.add(row);
        }
        return result;
    }

    public MapArray<String, MapArray<String, ICell>> columns(String value, Row row) {
        return columns().withValue(value, row);
    }
    public MapArray<String, MapArray<String, ICell>> columnsContains(String value, Row row) {
        return columns().containsValue(value, row);
    }
    public MapArray<String, MapArray<String, ICell>> columnsMatches(String regEx, Row row) {
        return columns().matchesRegEx(regEx, row);
    }
    public MapArray<String, MapArray<String, ICell>> columns(String... rowNameValues) {
        if (rowNameValues.length == 0)
            return columns().get();
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
        int rowsCount = rows().count(true);
        getDriver().manage().timeouts().implicitlyWait(timeouts.getCurrentTimeoutSec(), SECONDS);
        return rowsCount == 0;
    }

    public boolean waitHaveRows() {
        return waitRows(1);
    }

    public boolean waitRows(int count) {
        return waitCondition(() -> rows().count() >= count);
    }

    public ICell cell(String value, Row row) {
        int rowNum = row.hasName()
                ? rows().headers().indexOf(row.getName()) + 1
                : row.getNum();
        return rows().getRow(rowNum).first((name, cell) -> cell.getValue().equals(value));
    }
    public ICell cellContains(String value, Row row) {
        int rowNum = row.hasName()
                ? rows().headers().indexOf(row.getName()) + 1
                : row.getNum();
        return rows().getRow(rowNum).first((name, cell) -> cell.getValue().contains(value));
    }
    public ICell cellMatch(String regex, Row row) {
        MapArray<String, ICell> rowLine = row(row);
        List<ICell> cells =  matches(rowLine.values(), regex);
        if (cells.size() == 0) {
            logger.info(format("Can't find any cells in row %s that matches regEx: %s", row, regex));
            return null;
        }
        return cells.get(0);
    }

    public ICell cell(String value, Column column) {
        int colIndex = column.get(
                name -> columns().headers().indexOf(name) + 1,
                num -> num);
        return columns().getColumn(colIndex).first((name, cell) -> cell.getValue().equals(value));
    }
    public ICell cellContains(String value, Column column) {
        int colIndex = column.get(
                name -> columns().headers().indexOf(name) + 1,
                num -> num);
        return columns().getColumn(colIndex).first((name, cell) -> cell.getValue().contains(value));
    }
    public ICell cellMatch(String regex, Column column) {
        MapArray<String, ICell> columnLine = column(column);
        List<ICell> cells = matches(columnLine.values(), regex);
        if (cells.size() == 0) {
            logger.info(format("Can't find any cells in column %s that matches regEx: %s", column, regex));
            return null;
        }
        return cells.get(0);
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
        if (columnCell == null) {
            logger.info(format("Can't find any cells in row %s with value %s", row, value));
            return null;
        }
        return columns().getColumn(columnCell.columnNum());
    }
    public MapArray<String, ICell> columnContains(String value, Row row) {
        ICell columnCell = cellContains(value, row);
        if (columnCell == null) {
            logger.info(format("Can't find any cells in row %s that contains value %s", row, value));
            return null;
        }
        return columns().getColumn(columnCell.columnNum());
    }
    public MapArray<String, ICell> columnMatch(String regEx, Row row) {
        ICell columnCell = cellMatch(regEx, row);
        if (columnCell == null) {
            logger.info(format("Can't find any cells in row %s that matches regex %s", row, regEx));
            return null;
        }
        return columns().getColumn(columnCell.columnNum());
    }

    public MapArray<String, ICell> row(String value, Column column) {
        ICell rowCell = cell(value, column);
        if (rowCell == null) {
            logger.info(format("Can't find any cells in column %s with value %s", column, value));
            return null;
        }
        return rows().getRow(rowCell.rowNum());
    }
    public MapArray<String, ICell> rowContains(String value, Column column) {
        ICell rowCell = cellContains(value, column);
        if (rowCell == null) {
            logger.info(format("Can't find any cells in column %s that contains value %s", column, value));
            return null;
        }
        return rows().getRow(rowCell.rowNum());
    }
    public MapArray<String, ICell> rowMatch(String regEx, Column column) {
        ICell rowCell = cellMatch(regEx, column);
        if (rowCell == null) {
            logger.info(format("Can't find any cells in column %s that matches regEx: %s", column, regEx));
            return null;
        }
        return rows().getRow(rowCell.rowNum());
    }

    private int getColumnIndex(String name) {
        int nameIndex;
        List<String> headers = columns().headers();
        if (headers != null && headers.contains(name))
            nameIndex = headers.indexOf(name);
        else
            throw exception("Can't Get Column: '" + name + "'. " + ((headers == null)
                    ? "ColumnHeaders is Null"
                    : ("Available ColumnHeaders: " + print(headers, ", ", "'{0}'") + ")")));
        return nameIndex + columns().getStartIndex();
    }

    private int getRowIndex(String name) {
        int nameIndex;
        List<String> headers = rows().headers();
        if (headers != null && headers.contains(name))
            nameIndex = headers.indexOf(name);
        else
            throw exception("Can't Get Row: '%s'. Available RowHeaders: (%s)", name, print(headers, ", ", "'{0}'"));
        return nameIndex + rows().getStartIndex();
    }

    @Override
    protected String getTextAction() {
        return "||X||" + print(columns().headers(), "|") + "||\n"
                + print(select(rows().headers(),rowName -> "||" + rowName + "||" + print(rowValue(rowName), "|") + "||"), "\n");
    }

    private Cell addCell(int colIndex, int rowIndex, int colNum, int rowNum, String colName, String rowName) {
        Cell cell = (allCells.size() != 0)
            ? (Cell) first(allCells, c -> c.columnNum() == colNum && c.rowNum() == rowNum)
            : null;
        if (cell != null)
            return cell.updateData(colName, rowName);
        cell = new Cell(colIndex, rowIndex, colNum, rowNum, colName, rowName, cellLocatorTemplate, this);
        cell.setParent(this);
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