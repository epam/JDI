package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.commons.linqinterfaces.JFuncTTREx;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.Column;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.IColumn;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.Row;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class Columns extends TableLine implements IColumn {
    public Columns(Table table) {
        super(table);
    }

    @Override
    public MapArray<String, ICell> getColumn(String colName) {
        int rowsCount = table.rows().count();

        return new MapArray<>(rowsCount, key -> table.rows().headers().get(key),
                value -> table.cell(new Column(colName), new Row(value + 1)));
    }

    @Override
    public List<String> getColumnValue(String colName) {
        return getLineAction(colName).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public MapArray<String, String> getColumnAsText(String colName) {
        return getColumn(colName).toMapArray(IText::getText);
    }

    private MapArray<String, MapArray<String, ICell>> withValueByRule(Row row,
                                                                      JFuncTTREx<String, String, Boolean> func) {
        Collection<String> rowNames = row.hasName()
                ? table.rows().getRowAsText(row.getName()).where(func).keys()
                : table.rows().getRowAsText(row.getNum()).where(func).keys();
        return new MapArray<>(rowNames, key -> key, this::getColumn);
    }

    @Override
    public final MapArray<String, MapArray<String, ICell>> withValue(String value, Row row) {
        return withValueByRule(row, (key, val) -> val.equals(value));
    }

    @Override
    public final MapArray<String, MapArray<String, ICell>> containsValue(String value, Row row) {
        return withValueByRule(row, (key, val) -> val.contains(value));
    }

    @Override
    public final MapArray<String, MapArray<String, ICell>> matchesRegEx(String regEx, Row row) {
        return withValueByRule(row, (key, val) -> val.matches(regEx));
    }

    @Override
    public MapArray<String, ICell> getColumn(int colNum) {
        if (count() < 0 || count() < colNum || colNum <= 0)
            throw exception("Can't Get Column '%s'. [num] > RowsCount(%s).", colNum, count());

        int rowsCount = table.rows().count();

        return new MapArray<>(rowsCount, key -> table.rows().headers().get(key),
                value -> table.cell(new Column(colNum), new Row(value + 1)));
    }

    @Override
    public List<String> getColumnValue(int colNum) {
        if (count() < 0 || count() < colNum || colNum <= 0)
            throw exception("Can't Get Column '%s'. [num] > RowsCount(%s).", colNum, count());

        return getLineAction(colNum).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    @Override
    public MapArray<String, String> getColumnAsText(int colNum) {
        return getColumn(colNum).toMapArray(IText::getText);
    }

    @Override
    protected List<WebElement> getFirstLine() {
        return ((Rows)table.rows()).getLineAction(1);
    }

    @Override
    public MapArray<String, MapArray<String, ICell>> get() {
        return new MapArray<>(headers(), key -> key, this::getColumn);
    }
}
