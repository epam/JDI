package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.commons.LinqUtils;
import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import com.epam.jdi.uitests.core.interfaces.complex.interfaces.ICell;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.winnium.elements.WinCascadeInit;
import com.epam.jdi.uitests.win.winnium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.SelectElement;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.ClickableManager;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.WebElementTextManager;
import org.openqa.selenium.By;

public class Cell extends Element implements ICell {
    private int columnNum, rowNum;
    private String columnName, rowName;
    private Table table;
    private WebElementTextManager textManager = new WebElementTextManager(this);
    private ClickableManager clickableManager = new ClickableManager(this);

    Cell(int columnIndex, int rowIndex, int columnNum, int rowNum, String colName, String rowName,
         String xpathTemplate, Table table) {
        this.columnNum = columnNum;
        this.rowNum = rowNum;
        this.columnName = colName;
        this.rowName = rowName;
        this.table = table;

        this.setParent(table);

        String xpathExpression = String.format(xpathTemplate, rowIndex, columnIndex);
        this.setAvatar(new GetElementModule(By.xpath(xpathExpression), this));
    }

    @Override
    public ISelect get() {
        SelectElement selectElement = new SelectElement(getWebElement());
        selectElement.setParent(table);
        return selectElement;
    }

    @Override
    public <T extends IBaseElement> T get(Class<T> clazz) {
        T instance;
        try {
            instance = (clazz.isInterface())
                    ? (T) MapInterfaceToElement.getClassFromInterface(clazz).newInstance()
                    : clazz.newInstance();
        } catch (Exception ex) {
            throw JDISettings.exception("Can't get Cell from interface/class: " +
                    LinqUtils.last((clazz + "").split("\\.")));
        }
        return get(instance);
    }

    @Override
    public <T extends IBaseElement> T get(T element) {
        element.setParent(this);

        new WinCascadeInit().initElements(element, JDISettings.driverFactory.currentDriverName());

        return element;
    }

    @Override
    public int columnNum() {
        return columnNum;
    }

    @Override
    public int rowNum() {
        return rowNum;
    }

    @Override
    public String columnName() {
        return (columnName != null && !columnName.equals(""))
                ? columnName
                : table.columns().headers().get(columnNum - 1);
    }

    @Override
    public String rowName() {
        return (rowName != null && !rowName.equals(""))
                ? rowName
                : table.rows().headers().get(rowNum - 1);
    }

    @Override
    public void select() {
        click();
    }

    @Override
    public boolean isSelected() {
        return get().isSelected();
    }

    @Override
    public void click() {
        clickableManager.click();
    }

    @Override
    public String getText() {
        return textManager.getText();
    }

    @Override
    public String waitText(String text) {
        return textManager.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return textManager.waitMatchText(regEx);
    }

    @Override
    public String getValue() {
        return getText();
    }
}
