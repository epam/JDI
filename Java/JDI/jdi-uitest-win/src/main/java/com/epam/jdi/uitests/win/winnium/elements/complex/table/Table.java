package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.commons.map.MapArray;
import com.epam.commons.pairs.Pair;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.*;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.elements.ElementsUtils;
import com.epam.jdi.uitests.win.winnium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.pageobjects.annotations.objects.TableHeaderTypes;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.commons.EnumUtils.getAllEnumNames;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.Timer.getByCondition;
import static com.epam.commons.Timer.waitCondition;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static java.lang.String.format;

public class Table extends Element implements ITable {
    private boolean useCache = true;
    private String cellXpath;

    protected Columns columns;
    protected Rows rows;

    public Table() {
        columns = new Columns(this);
        rows = new Rows(this);
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUp(By root, String columnHeadersInTableXpath, String rowsInTableXpath, String headerInRowXpath,
                      String columnsInRowXpath, TableHeaderTypes tableHeaderTypes, int rowStartIndex,
                      int colStartIndex, boolean useCache) {
        cellXpath = "." + rowsInTableXpath + "[%d]" + columnsInRowXpath + "[%d]";

        if (root != null)
            setAvatar(new GetElementModule(root, this));

        this.useCache = useCache;

        rows.setStartIndex(rowStartIndex);
        rows.setLineTemplate("." + rowsInTableXpath + "[%d]" + columnsInRowXpath);
        if (!"".equals(rowsInTableXpath) || !"".equals(headerInRowXpath))
            rows.setHeadersXpathStr("." + rowsInTableXpath + headerInRowXpath);
        columns.setStartIndex(colStartIndex);
        columns.setLineTemplate("." + rowsInTableXpath + columnsInRowXpath + "[%d]");
        if (!"".equals(columnHeadersInTableXpath))
            columns.setHeadersXpathStr("." + columnHeadersInTableXpath);

        switch (tableHeaderTypes) {
            case ALL_HEADERS:
                hasAllHeaders();
                break;
            case NO_HEADERS:
                hasNoHeaders();
                break;
            case COLUMNS_HEADERS:
                hasOnlyColumnHeaders();
                break;
            case ROWS_HEADERS:
                hasOnlyRowHeaders();
                break;
        }
    }

    private int getColumnIndex(String name) {
        List<String> headers = columns().headers();
        if (headers != null) {
            int nameIndex = headers.indexOf(name);
            if (nameIndex != -1)
                return nameIndex + columns().getStartIndex();
        }

        throw exception("Can't Get Column: '" + name + "'. " + ((headers == null)
                ? "ColumnHeaders is Null"
                : ("Available ColumnHeaders: " + print(headers, ", ", "'{0}'") + ")")));
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
    public ICell cell(Column column, Row row) {
        int colIndex = column.get(this::getColumnIndex, num -> num + columns().getStartIndex() - 1);
        int rowIndex = row.get(this::getRowIndex, num -> num + rows().getStartIndex() - 1);

        return addCell(colIndex, rowIndex,
                column.get(name -> columns().headers().indexOf(name) + 1, num -> num),
                row.get(name -> rows().headers().indexOf(name) + 1, num -> num),
                column.get(name -> name, num -> ""),
                row.get(name -> name, num -> ""));
    }

    private ICell addCell(int colIndex, int rowIndex, int colNum, int rowNum, String colName, String rowName) {
        return new Cell(colIndex, rowIndex, colNum, rowNum, colName, rowName, cellXpath, this);
    }

    @Override
    public List<ICell> cells(String value) {
        return getCells().stream().filter(cell -> cell.getValue().equals(value)).collect(Collectors.toList());
    }

    @Override
    public ICell cellContains(String value, Row row) {
        int rowNum = row.hasName() ? rows().headers().indexOf(row.getName()) + 1 : row.getNum();

        return rows().getRow(rowNum).first((name, cell) -> cell.getValue().contains(value));
    }

    @Override
    public ICell cellMatch(String regex, Row row) {
        MapArray<String, ICell> rowLine = row(row);
        Optional<ICell> cellOpt = rowLine.values().stream().filter(cell -> cell.getValue().matches(regex)).findAny();

        return cellOpt.isPresent() ? cellOpt.get() : null;
    }

    private MapArray<String, ICell> row(Row row) {
        return row.get(this::row, this::row);
    }

    @Override
    public ICell cellContains(String value, Column column) {
        int colIndex = column.get(
                name -> columns().headers().indexOf(name) + 1,
                num -> num);
        return columns().getColumn(colIndex).first((name, cell) -> cell.getValue().contains(value));
    }

    @Override
    public ICell cellMatch(String regex, Column column) {
        MapArray<String, ICell> columnLine = column(column);
        Optional<ICell> cellOpt = columnLine.values().stream().filter(cell -> cell.getValue().matches(regex)).findAny();

        return cellOpt.isPresent() ? cellOpt.get() : null;
    }

    private MapArray<String, ICell> column(Column column) {
        return column.get(this::column, this::column);
    }

    @Override
    public List<ICell> cellsContains(String value) {
        return getCells().stream().filter(cell -> cell.getValue().equals(value)).collect(Collectors.toList());
    }

    @Override
    public List<ICell> cellsMatch(String regex) {
        return getCells().stream().filter(cell -> cell.getValue().matches(regex)).collect(Collectors.toList());
    }

    @Override
    public ICell cell(String value) {
        Optional<ICell> cellOpt = getCells().stream().filter(cell -> cell.getValue().equals(value)).findAny();
        return cellOpt.isPresent() ? cellOpt.get() : null;
    }

    @Override
    public ICell cellMatch(String regex) {
        Optional<ICell> cellOpt = getCells().stream().filter(cell -> cell.getValue().matches(regex)).findAny();
        return cellOpt.isPresent() ? cellOpt.get() : null;
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> rows(String... colNameValues) {
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
                    default:
                        matches = cell.getValue().matches(filter.value);
                        break;
                }
                if (!matches) break;
            }
            if (matches) result.add(row);
        }
        return result;
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> rows(String value, Column column) {
        return rows().withValue(value, column);
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> rowsContains(String value, Column column) {
        return rows().containsValue(value, column);
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> rowsMatches(String regEx, Column column) {
        return rows().matchesRegEx(regEx, column);
    }

    @Override
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

    @Override
    public MapArray<String, MapArray<String, ICell>> columns(String value, Row row) {
        return columns().withValue(value, row);
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> columnsContains(String value, Row row) {
        return columns().containsValue(value, row);
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> columnsMatches(String regEx, Row row) {
        return columns().withValue(regEx, row);
    }

    @Override
    public boolean waitValue(String value, Row row) {
        return ElementsUtils.timer().wait(() -> column(value, row) != null);
    }

    @Override
    public boolean waitValue(String value, Column column) {
        return ElementsUtils.timer().wait(() -> row(value, column) != null);
    }

    @Override
    public boolean isEmpty() {
        return rows().count() == 0;
    }

    @Override
    public boolean waitHaveRows() {
        return waitRows(1);
    }

    @Override
    public boolean waitRows(int count) {
        return waitCondition(() -> rows().count() >= count);
    }

    @Override
    public ICell cell(String value, Row row) {
        int rowIndex = row.get(name -> rows().headers().indexOf(name) + 1, num -> num);
        return rows().getRow(rowIndex).first((name, cell) -> cell.getValue().equals(value));
    }

    @Override
    public ICell cell(String value, Column column) {
        int colIndex = column.get(name -> columns().headers().indexOf(name) + 1, num -> num);
        return columns().getColumn(colIndex).first((name, cell) -> cell.getValue().equals(value));
    }

    @Override
    public List<ICell> cellsMatch(String regex, Row row) {
        MapArray<String, ICell> rowLine = row(row);
        return rowLine.values().stream().filter(cell -> cell.getValue().matches(regex)).collect(Collectors.toList());
    }

    @Override
    public List<ICell> cellsMatch(String regex, Column column) {
        MapArray<String, ICell> columnLine = column(column);
        return columnLine.values().stream().filter(cell -> cell.getValue().matches(regex)).collect(Collectors.toList());
    }

    @Override
    public MapArray<String, ICell> row(String value, Column column) {
        ICell rowCell = cell(value, column);
        return rowCell != null ? rows().getRow(rowCell.rowNum()) : null;
    }

    @Override
    public MapArray<String, ICell> column(String value, Row row) {
        ICell columnCell = cellContains(value, row);
        return columnCell != null ? columns().getColumn(columnCell.columnNum()) : null;
    }

    @Override
    public MapArray<String, ICell> columnContains(String value, Row row) {
        ICell columnCell = cellContains(value, row);
        return columnCell != null ? columns().getColumn(columnCell.columnNum()) : null;
    }

    @Override
    public MapArray<String, ICell> columnMatch(String regEx, Row row) {
        ICell columnCell = cellMatch(regEx, row);
        return columnCell != null ? columns().getColumn(columnCell.columnNum()) : null;
    }

    @Override
    public MapArray<String, ICell> rowContains(String value, Column column) {
        ICell rowCell = cellContains(value, column);
        return rowCell != null ? rows().getRow(rowCell.rowNum()) : null;
    }

    @Override
    public MapArray<String, ICell> rowMatch(String regEx, Column column) {
        ICell rowCell = cellMatch(regEx, column);
        return rowCell != null ? rows().getRow(rowCell.rowNum()) : null;
    }

    @Override
    public IRow rows() {
        return rows;
    }

    @Override
    public MapArray<String, ICell> row(int rowNum) {
        return rows().getRow(rowNum);
    }

    @Override
    public MapArray<String, ICell> row(String rowName) {
        return rows().getRow(rowName);
    }

    @Override
    public List<String> rowValue(int rowNum) {
        return rows().getRowValue(rowNum);
    }

    @Override
    public List<String> rowValue(String rowName) {
        return rows().getRowValue(rowName);
    }

    @Override
    public IColumn columns() {
        return columns;
    }

    @Override
    public MapArray<String, ICell> column(int colNum) {
        return columns().getColumn(colNum);
    }

    @Override
    public MapArray<String, ICell> column(String colName) {
        return columns().getColumn(colName);
    }

    @Override
    public List<String> columnValue(int colNum) {
        return columns().getColumnValue(colNum);
    }

    @Override
    public List<String> columnValue(String colName) {
        return columns().getColumnValue(colName);
    }

    @Override
    public MapArray<String, ISelect> header() {
        return columns().header();
    }

    @Override
    public ISelect header(String name) {
        return columns().header(name);
    }

    @Override
    public List<String> headers() {
        return columns.headers();
    }

    @Override
    public List<String> footer() {
        throw JDISettings.exception("Not supported");
    }

    @Override
    public List<ICell> getCells() {
        List<ICell> result = new ArrayList<>();
        for (String columnName : columns().headers())
            rows().headers().forEach(rowName
                    -> result.add(cell(columnName, rowName)));

        return result;
    }

    @Override
    public void clean() {
        columns().clean();
        rows().clean();
    }

    @Override
    public void clear() {
        clean();
    }

    @Override
    public ITable useCache(boolean value) {
        useCache = value;
        return this;
    }

    @Override
    public ITable clone() {
        throw JDISettings.exception("Not supported");
    }

    @Override
    public ITable copy() {
        return clone();
    }

    public ITable hasAllHeaders() {
        ((Columns) columns()).setHasHeader(true);
        ((Rows)rows()).setHasHeader(true);
        return this;
    }

    public ITable hasNoHeaders() {
        ((Columns) columns()).setHasHeader(false);
        ((Rows)rows()).setHasHeader(false);
        return this;
    }

    public ITable hasOnlyColumnHeaders() {
        ((Columns) columns()).setHasHeader(true);
        ((Rows)rows()).setHasHeader(false);
        return this;
    }

    public ITable hasOnlyRowHeaders() {
        ((Columns) columns()).setHasHeader(false);
        ((Rows)rows()).setHasHeader(true);
        return this;
    }

    @Override
    public ITable hasColumnHeaders(List<String> value) {
        ((Columns) columns()).setHasHeader(true);
        ((Columns) columns()).headers = new ArrayList<>(value);
        return this;
    }

    @Override
    public <THeaders extends Enum> ITable hasColumnHeaders(Class<THeaders> headers) {
        return hasColumnHeaders(getAllEnumNames(headers));
    }

    @Override
    public ITable hasRowHeaders(List<String> value) {
        ((Rows)rows()).setHasHeader(true);
        ((Rows)rows()).headers = new ArrayList<>(value);
        return this;
    }

    @Override
    public <THeaders extends Enum> ITable hasRowHeaders(Class<THeaders> headers) {
        return hasRowHeaders(getAllEnumNames(headers));
    }

    @Override
    public ITable setColumnsCount(int value) {
        columns().setCount(value);
        return this;
    }

    @Override
    public ITable setRowsCount(int value) {
        rows().setCount(value);
        return this;
    }

    @Override
    public String getText() {
        return "||X||" + print(columns().headers(), "|") + "||\n" + print(
                rows().headers().stream().map(rowName -> "||" + rowName + "||" + print(rowValue(rowName), "|") + "||").
                        collect(Collectors.toList()), "\n");
    }

    @Override
    public String waitText(String text) {
        return invoker.doJActionResult(format("Wait text contains '%s'", text),
                () -> getByCondition(this::getText, t -> t.contains(text)), this.toString());
    }

    @Override
    public String waitMatchText(String regEx) {
        return invoker.doJActionResult(format("Wait text match regex '%s'", regEx),
                () -> getByCondition(this::getText, t -> t.matches(regEx)), this.toString());
    }

    @Override
    public String getValue() {
        return getText();
    }
}
