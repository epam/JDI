package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.commons.Timer;
import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ITableLine;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.SelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.addAll;

abstract class TableLine extends Element implements ITableLine {
    protected List<String> headers;
    protected String headersXpathStr;
    protected Table table;

    private boolean hasHeader;
    private int startIndex = 1;
    private String lineTemplate;
    private int count = 0;

    public void removeHeaders(String... names) {
        for (String name : names)
            headers.remove(name);
    }
    public void addHeaders(String... names) {
        addAll(headers, names);
    }

    public void setHeadersXpathStr(String headersXpathStr) {
        this.headersXpathStr = headersXpathStr;
    }

    public TableLine(Table table) {
        this.table = table;
    }

    protected List<WebElement> getLineAction(int colNum) {
        return table.getWebElement().findElements(By.xpath(String.format(lineTemplate, colNum + startIndex - 1)));
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public void setLineTemplate(String lineTemplate) {
        this.lineTemplate = lineTemplate;
    }

    @Override
    public int getStartIndex() {
        return startIndex;
    }

    @Override
    public void setCount(int count) {
        if (table.isUseCache())
            this.count = count;
    }

    protected List<WebElement> getHeadersAction() {
        if (headersXpathStr == null || "".equals(headersXpathStr))
            return new LinkedList<>();

        return table.getWebElement().findElements(By.xpath(headersXpathStr));
    }

    @Override
    public int count(boolean acceptEmpty) {
        if (count > 0)
            return count;
        if (headers != null && headers.size() > 0)
            return headers.size();

        int countRes = count();
        setCount(countRes);

        return countRes;
    }

    @Override
    public int count() {
        if (count > 0)
            return count;
        if (headers != null && headers.size() > 0)
            return headers.size();

        List<WebElement> elements = hasHeader ? getHeadersAction() : null;
        if (elements == null || elements.size() == 0)
            elements = getFirstLine();

        int countRes =  elements != null && elements.size() > 0
                ? elements.size()
                : 0;
        setCount(countRes);

        return countRes;
    }

    protected abstract List<WebElement> getFirstLine();

    @Override
    public void clean() {
        headers = null;
        count = 0;
    }

    @Override
    public final MapArray<String, ISelect> header() {
        return new MapArray<>(getHeadersAction(), w -> w.getAttribute("Name"), SelectElement::new);
    }

    @Override
    public final ISelect header(String name) {
        return header().get(name);
    }

    private List<String> getHeadersTextAction() {
        List<WebElement> headersAction = getHeadersAction();

        return headersAction.stream().map(w -> w.getAttribute("Name")).collect(Collectors.toList());
    }

    private List<String> getNumList() {
        return IntStream.rangeClosed(1, count()).mapToObj(Integer::toString).collect(Collectors.toList());
    }

    @Override
    public List<String> headers() {
        if (headers != null)
            return new ArrayList<>(headers);

        List<String> localHeaders = hasHeader
                ? new Timer().getResult(this::getHeadersTextAction)
                : getNumList();

        if (localHeaders == null || localHeaders.size() == 0)
            return new ArrayList<>();

        setHeaders(localHeaders);
        setCount(localHeaders.size());

        return localHeaders;
    }

    private void setHeaders(List<String> headers) {
        if (table.isUseCache())
            this.headers = new ArrayList<>(headers);
    }

    protected List<WebElement> getLineAction(String lineName) {
        int index = headers().indexOf(lineName) + 1;
        if (index == 0)
            throw JDISettings.exception("Can't get line by name %s", lineName);

        return getLineAction(index);
    }

    @Override
    public final MapArray<String, MapArray<String, String>> getAsText() {
        return get().toMapArray(line -> line.toMapArray(IText::getText));
    }
}
