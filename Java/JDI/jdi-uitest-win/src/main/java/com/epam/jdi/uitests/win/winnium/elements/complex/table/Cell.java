package com.epam.jdi.uitests.win.winnium.elements.complex.table;

import com.epam.jdi.uitests.core.interfaces.MapInterfaceToElement;
import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.SelectElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static com.epam.commons.LinqUtils.last;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.win.winnium.driver.WebDriverByUtils.fillByMsgTemplate;

public class Cell extends Element implements ICell {
    private int rowIndex;
    private int columnIndex;
    private Table table;
    private int columnNum;
    private int rowNum;
    private String columnName;
    private String rowName;
    private By cellLocatorTemplate = By.xpath(".//tr[{1}]/td[{0}]");

    Cell(WebElement webElement, int columnNum, int rowNum, String colName, String rowName,
         By cellLocatorTemplate, Table table) {
        this.getAvatar().setWebElement(webElement);
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
        this.columnIndex = (((Rows)table.rows()).hasHeader && ((Rows)table.rows()).lineTemplate == null) ? columnIndex + 1 : columnIndex;
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
        this.getAvatar().setWebElement(webElement);
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
                : table.columns().headers().get(columnNum - 1);
    }

    public String rowName() {
        return (rowName != null && !rowName.equals(""))
                ? rowName
                : table.rows().headers().get(rowNum - 1);
    }

    protected String getTextAction() {
        return get().getText();
    }

    protected void clickAction() {
        get().click();
    }

    protected boolean isSelectedAction() {
        return get().isSelected();
    }

    private SelectElement get() {
        SelectElement cell = avatar.hasWebElement()
                ? new SelectElement(getWebElement())
                : new SelectElement(getDriver().findElement(fillByMsgTemplate(cellLocatorTemplate, columnIndex, rowIndex)));
        //TODO
        //cell.init(table, cell.getAvatar());
        return cell;
    }

    public WebElement get(By subLocator) {
        //TODO
        return null;
        //return get().get(subLocator);
    }

    public <T extends IBaseElement> T get(Class<T> clazz) {
        T instance;
        try {
            instance = (clazz.isInterface())
                    ? (T) MapInterfaceToElement.getClassFromInterface(clazz).newInstance()
                    : clazz.newInstance();
            Element el = ((Element)instance);
            el.setAvatar(getAvatar());
            el.setLocator(fillByMsgTemplate(cellLocatorTemplate, columnIndex, rowIndex));
            //TODO
            //el.init(table, instance.getAvatar());
        } catch (Exception ex) {
            throw exception("Can't get Cell from interface/class: " + last((clazz + "").split("\\.")));
        }
        return get(instance);
    }

    public <T extends IBaseElement> T get(T cell) {
        return null;
        //TODO
        /*
        BaseElement cellSelect = (BaseElement) cell;
        if (cellSelect.getAvatar().hasWebElement())
            return cell;
        By locator = cellSelect.getLocator();
        if (locator == null || locator.toString().equals(""))
            locator = cellLocatorTemplate;
        if (!locator.toString().contains("{0}") || !locator.toString().contains("{1}"))
            throw exception("Can't create cell with locator template " + cellSelect.getLocator()
                    + ". Template for Cell should contains '{0}' - for column and '{1}' - for row indexes.");
        cellSelect.avatar.setDriverName(avatar.getDriverName());
        cellSelect.init(table,
                new GetElementModule(
                        fillByMsgTemplate(locator, columnIndex, rowIndex), cellSelect));
        return cell;*/
    }

    public Cell updateData(String colName, String rowName) {
        if ((columnName == null || columnName.equals("")) && !(colName == null || colName.equals("")))
            columnName = colName;
        if ((this.rowName == null || this.rowName.equals("")) && !(rowName == null || rowName.equals("")))
            this.rowName = rowName;
        return this;
    }

    public String getValue() {
        throw new NotImplementedException();
    }

    public void click() {
        throw new NotImplementedException();

    }

    public void select() {
        throw new NotImplementedException();

    }

    public boolean isSelected() {
        throw new NotImplementedException();
    }

    public String getText() {
        throw new NotImplementedException();
    }

    public String waitText(String text) {
        throw new NotImplementedException();
    }

    public String waitMatchText(String regEx) {
        throw new NotImplementedException();
    }
}
