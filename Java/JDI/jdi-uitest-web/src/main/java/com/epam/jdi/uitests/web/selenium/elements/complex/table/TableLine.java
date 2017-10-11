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
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ElementIndexType;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITableLine;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.epam.commons.LinqUtils.*;
import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.fillByTemplate;
import static com.epam.jdi.uitests.web.selenium.driver.WebDriverByUtils.getByLocator;
import static java.util.Collections.addAll;

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

    public  <T extends TableLine> T clone(T newTableLine, Table newTable) {
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
        return getElementByTemplate(colNum + startIndex - 1);
    }

    protected List<WebElement> getLineAction(String lineName) {
        int index = getIndex(select(headers(), String::toLowerCase), lineName.toLowerCase()) + 1;
        if (lineTemplate != null && getByLocator(lineTemplate).contains("%s"))
            return getElementByTemplate(index);
        return lineTemplate == null
                ? getLineAction(index)
                : getElementByTemplate(index);
    }
    private List<WebElement> getElementByTemplate(Object value) {
        By locator = fillByTemplate(lineTemplate != null
                ? lineTemplate
                : defaultTemplate, value);
        return where(table.getWebElement().findElements(locator), WebElement::isDisplayed);
    }

    public void removeHeaders(String... names) {
        for (String name : names)
            headers.remove(name);
    }
    public void addHeaders(String... names) {
        addAll(headers, names);
    }
    protected int getCount(boolean acceptEmpty) {
        setWaitTimeout(0);
        List<WebElement> elements = getHeadersAction();
        if (elements.size() == 0)
            elements = getCrossFirstLine();
        restoreWaitTimeout();
        if (!acceptEmpty)
            elements = timer().getResultByCondition(this::getCrossFirstLine, el -> el != null && el.size() > 0);
        return elements != null && elements.size() > 0
            ? elements.size()
            : 0;
    }

    protected abstract List<WebElement> getCrossFirstLine();

    public void setCount(int value) {
        if (table.cache) count = value;
    }

    public int count() {
        return count(false);
    }
    public int count(boolean acceptEmpty) {
        if (count > 0)
            return count;
        if (headers != null && headers.size() > 0)
            return headers.size();
        return getCount(acceptEmpty);
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

    public final MapArray<String, ISelect> header() {
        return new MapArray<>(headers(),
                select(getHeadersAction(), SelectElement::new));
    }

    public final ISelect header(String name) {
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