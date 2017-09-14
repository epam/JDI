package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.commons.linqinterfaces.JFuncTTREx;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.IRow;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.Row;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class Rows extends TableLine implements IRow {
    public Rows(Table table) {
        super(table);
    }

    @Override
    protected List<WebElement> getFirstLine() {
        return ((Columns)table.columns()).getLineAction(1);
    }

    @Override
    public MapArray<String, ICell> getRow(String rowName) {
        try {
            int colsCount = table.columns().count();
            List<String> headers = table.columns().headers();
            return new MapArray<>(colsCount, headers::get,
                    value -> table.cell(new Column(headers.get(value)), new Row(rowName)));
        } catch (Exception | Error ex) {
            throw throwRowsException(rowName, ex.getMessage());
        }
    }

    @Override
    public List<String> getRowValue(String rowName) {
        return getLineAction(rowName).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public MapArray<String, String> getRowAsText(String rowName) {
        return getRow(rowName).toMapArray(IText::getText);
    }

    private MapArray<String, MapArray<String, ICell>> withValueByRule(Column column,
                                                                      JFuncTTREx<String, String, Boolean> func) {
        Collection<String> rowNames = column.hasName()
                ? table.columns().getColumnAsText(column.getName()).where(func).keys()
                : table.columns().getColumnAsText(column.getNum()).where(func).keys();
        return new MapArray<>(rowNames, key -> key, this::getRow);
    }

    @Override
    public final MapArray<String, MapArray<String, ICell>> withValue(String value, Column column) {
        return withValueByRule(column, (key, val) -> val.equals(value));
    }

    @Override
    public final MapArray<String, MapArray<String, ICell>> containsValue(String value, Column column) {
        return withValueByRule(column, (key, val) -> val.contains(value));
    }

    @Override
    public final MapArray<String, MapArray<String, ICell>> matchesRegEx(String regEx, Column column) {
        return withValueByRule(column, (key, val) -> val.matches(regEx));
    }

    @Override
    public MapArray<String, ICell> getRow(int rowNum) {
        if (count() < 0 || count() < rowNum || rowNum <= 0)
            throw exception("Can't Get Row '%s'. [num] > ColumnsCount(%s).", rowNum, count());

        int colsCount = table.columns().count();

        return new MapArray<>(colsCount,
                key -> table.columns().headers().get(key),
                value -> table.cell(new Column(value + 1), new Row(rowNum)));
    }

    @Override
    public List<String> getRowValue(int rowNum) {
        return getLineAction(rowNum).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public MapArray<String, String> getRowAsText(int rowNum) {
        return getRow(rowNum).toMapArray(IText::getText);
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> get() {
        return new MapArray<>(headers(), key -> key, this::getRow);
    }

    private RuntimeException throwRowsException(String rowName, String ex) {
        return exception("Can't Get Row '%s'. Reason: %s", rowName, ex);
    }
}
