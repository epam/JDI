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
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.base.SelectElement;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITableLine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.LinqUtils.getIndex;
import static com.epam.commons.LinqUtils.select;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.fillByTemplate;

/**
 * Created by 12345 on 25.10.2014.
 */
abstract class TableLine extends Element implements ITableLine, Cloneable {
    public boolean hasHeader;
    public ElementIndexType elementIndex;
    public Table table;
    protected int count = 0;
    protected List<String> headers;
    protected int startIndex = 1;
    protected By headersLocator;
    protected By defaultTemplate;
    protected By lineTemplate = null;

    protected <T extends TableLine> T clone(T newTableLine, Table newTable) {
        asserter.silent(() -> super.clone());
        newTableLine.hasHeader = hasHeader;
        newTableLine.elementIndex = elementIndex;
        newTableLine.table = newTable;
        newTableLine.count = count;
        newTableLine.headers = headers;
        newTableLine.startIndex = startIndex;
        newTableLine.headersLocator = headersLocator;
        newTableLine.defaultTemplate = defaultTemplate;
        newTableLine.lineTemplate = lineTemplate;
        return newTableLine;
    }

    public int getStartIndex() {
        return startIndex;
    }

    protected List<WebElement> getLineAction(int colNum) {
        return table.getWebElement().findElements(fillByTemplate((lineTemplate != null) ? lineTemplate : defaultTemplate, colNum));
    }

    protected List<WebElement> getLineAction(String lineName) {
        if (lineTemplate != null && WebDriverByUtils.getByLocator(lineTemplate).contains("%s"))
            return table.getWebElement().findElements(fillByTemplate(lineTemplate, lineName));
        int index = getIndex(headers(), lineName) + 1;
        return (lineTemplate == null)
                ? getLineAction(index)
                : table.getWebElement().findElements(fillByTemplate(lineTemplate, index));
    }

    protected int getCount(boolean acceptEmpty)
    {
        List<WebElement> elements = acceptEmpty
                ? getFirstLine()
                : timer().getResultByCondition(this::getFirstLine, el -> el != null && el.size() > 0);
        return elements != null && elements.size() > 0
            ? elements.size()
            : 0;
    }

    protected abstract List<WebElement> getFirstLine();

    public void setCount(int value) {
        if (table.cache) count = value;
    }

    public int count() {
        return count(false);
    }
    protected int count(boolean acceptEmpty) {
        if (count > 0)
            return count;
        else {
            if (headers != null && headers.size() > 0)
                return headers.size();
            return getCount(acceptEmpty);
        }
    }

    public void clean() {
        headers = null;
        count = 0;
    }

    public void setHeaders(List<String> value) {
        if (table.cache)
            headers = new ArrayList<>(value);
    }

    protected List<String> getHeadersTextAction() {
        return select(getHeadersAction(), WebElement::getText);
    }

    protected abstract List<WebElement> getHeadersAction();

    public final MapArray<String, SelectElement> header() {
        return new MapArray<>(getHeadersAction(), WebElement::getText, SelectElement::new);
    }

    public final SelectElement header(String name) {
        return header().get(name);
    }

    public List<String> headers() {
        if (headers != null)
            return new ArrayList<>(headers);
        List<String> localHeaders = hasHeader
                ? timer().getResult(this::getHeadersTextAction)
                : getNumList(count());
        if (localHeaders == null || localHeaders.size() == 0)
            return new ArrayList<>();
        if (count > 0 && localHeaders.size() > count)
            localHeaders = localHeaders.subList(0, count);
        setHeaders(localHeaders);
        setCount(localHeaders.size());
        return localHeaders;
    }

    protected List<String> getNumList(int count) {
        return getNumList(count, 1);
    }

    protected List<String> getNumList(int count, int from) {
        List<String> result = new ArrayList<>();
        for (int i = from; i < count + from; i++)
            result.add(Integer.toString(i));
        return result;
    }

    public final void update(TableLine tableLine) {
        if (tableLine.count > 0)
            setCount(tableLine.count());
        if (tableLine.startIndex != 1)
            startIndex = tableLine.startIndex;
        if (tableLine.headers != null && tableLine.headers.size() > 0)
            setHeaders(tableLine.headers());
        if ((isClass(tableLine.getClass(), Columns.class) && !tableLine.hasHeader)
                || (isClass(tableLine.getClass(), Rows.class) && tableLine.hasHeader))
            hasHeader = tableLine.hasHeader;
        if (tableLine.elementIndex != ElementIndexType.Nums)
            elementIndex = tableLine.elementIndex;
    }

    public final MapArray<String, MapArray<String, String>> getAsText() {
        return get().toMapArray(line -> line.toMapArray(IText::getText));
    }
}