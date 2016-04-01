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


import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.mobile.appium.driver.WebDriverByUtils;
import com.epam.jdi.uitests.mobile.appium.elements.BaseElement;
import com.epam.jdi.uitests.mobile.appium.elements.MapInterfaceToElement;
import com.epam.jdi.uitests.mobile.appium.elements.apiInteract.ContextType;
import com.epam.jdi.uitests.mobile.appium.elements.base.SelectElement;
import com.epam.jdi.uitests.mobile.appium.elements.complex.table.interfaces.ICell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.epam.commons.LinqUtils.last;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

/**
 * Created by 12345 on 25.10.2014.
 */

/**
 * Cell have some info about its position:<br>
 * ColumnName, RowName, ColumnIndex, RowIndex<br>
 * You can do some actions with Cell:<br>
 * Click, Select, getText, waitText, waitMatchText<br>
 * Also you can use get() method to get Element of specified for table Type and do any possible action with it<br>
 */
class Cell extends SelectElement implements ISelect, ICell {
    private int rowIndex;
    private int columnIndex;
    private Table table;
    private int columnNum;
    private WebElement webElement;
    private int rowNum;
    private String columnName;
    private String rowName;
    private By cellLocatorTemplate = By.xpath(".//tr[{1}]/td[{0}]");
    private Class<?>[] columnsTemplate;

    Cell(WebElement webElement, int columnNum, int rowNum, String colName, String rowName,
                By cellLocatorTemplate, Table table) {
        this.webElement = webElement;
        this.columnNum = columnNum;
        this.rowNum = rowNum;
        this.columnName = colName;
        this.rowName = rowName;
        if (cellLocatorTemplate != null)
            this.cellLocatorTemplate = cellLocatorTemplate;
        this.table = table;
    }

    Cell(int columnIndex, int rowIndex, int columnNum, int rowNum, String colName, String rowName,
                By cellLocatorTemplate, Table table) {
        this.columnIndex = (table.rows().hasHeader && table.rows().lineTemplate == null) ? columnIndex + 1 : columnIndex;
        this.rowIndex = rowIndex;
        this.columnNum = columnNum;
        this.rowNum = rowNum;
        this.columnName = colName;
        this.rowName = rowName;
        if (cellLocatorTemplate != null)
            this.cellLocatorTemplate = cellLocatorTemplate;
        this.table = table;
    }

    public void setWebElement(WebElement webElement) {
        this.webElement = webElement;
    }

    public int columnNum() {
        return columnNum;
    }

    public int rowNum() {
        return rowNum;
    }

    public String columnName() {
        return (columnName != null && !columnName.equals(""))
                ? columnName
                : table.columns().headers()[columnNum - 1];
    }

    public String rowName() {
        return (rowName != null && !rowName.equals(""))
                ? rowName
                : table.rows().headers()[rowNum - 1];
    }

    @Override
    protected String getTextAction() {
        return get().getText();
    }

    @Override
    protected void clickAction() {
        get().click();
    }

    @Override
    protected boolean isSelectedAction() {
        return get().isSelected();
    }

    public SelectElement get() {
        return (webElement != null)
                ? new SelectElement(webElement)
                : new SelectElement(WebDriverByUtils.fillByMsgTemplate(cellLocatorTemplate, columnIndex, rowIndex));
    }

    public <T extends BaseElement> T get(Class<T> clazz) {
        T instance;
        try {
            instance = (clazz.isInterface())
                    ? (T) MapInterfaceToElement.getClassFromInterface(clazz).newInstance()
                    : clazz.newInstance();
        } catch (Exception ex) {
            throw exception("Can't get Cell from interface/class: " + last((clazz + "").split("\\.")));
        }
        return get(instance);
    }

    public <T extends BaseElement> T get(T cell) {
        By locator = cell.getLocator();
        if (locator == null || locator.toString().equals(""))
            locator = cellLocatorTemplate;
        if (!locator.toString().contains("{0}") || !locator.toString().contains("{1}"))
            throw exception("Can't create cell with locator template " + cell.getLocator()
                    + ". Template for Cell should contains '{0}' - for column and '{1}' - for row indexes.");
        cell.getAvatar().byLocator = WebDriverByUtils.fillByMsgTemplate(locator, rowIndex, columnIndex);
        cell.getAvatar().context.add(ContextType.Locator, table.getLocator());
        return cell;
    }

    public Cell updateData(String colName, String rowName) {
        if ((columnName == null || columnName.equals("")) && !(colName == null || colName.equals("")))
            columnName = colName;
        if ((this.rowName == null || this.rowName.equals("")) && !(rowName == null || rowName.equals("")))
            this.rowName = rowName;
        return this;
    }
}