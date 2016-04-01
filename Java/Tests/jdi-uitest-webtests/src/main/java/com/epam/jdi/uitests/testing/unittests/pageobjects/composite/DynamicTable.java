package com.epam.jdi.uitests.testing.unittests.pageobjects.composite;

import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.CheckBox;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.Table;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ICell;
import com.epam.jdi.uitests.web.selenium.elements.complex.table.interfaces.ITable;
import org.openqa.selenium.By;

/**
 * Created by Natalia_Grebenshchik on 10/21/2015.
 */
public class DynamicTable extends Table implements ITable {

    public DynamicTable() {
        super(By.xpath(".//table//td[1]"),
                By.xpath(".//thead//th"),
                By.xpath(".//table//tr[%s]/td"),
                //By.xpath(".//tr[*[.='%s']]/td[position()>1]"),
                By.xpath(".//table//tr/td[position() > 1][%s]"), -1, 2);
        this.cellLocatorTemplate = By.xpath(".//table//tr[{1}]/td[{0}]");
    }

    public Link getCellLink(int columnIndex, int rowIndex) {
        ICell cell = this.cell(columnIndex, rowIndex);

        return cell.get(new Link(By.xpath(".//tr[{0}]/td[{1}]//a")));
    }

    public void clickCellCheckBox(int columnIndex, int rowIndex) {
        ICell cell = this.cell(columnIndex, rowIndex);

        cell.get(new CellCheckBox(By.xpath(".//tr[{0}]/td[{1}]//label"))).click();
    }

    public boolean isCellCheckBoxChecked(int columnIndex, int rowIndex) {
        ICell cell = this.cell(columnIndex, rowIndex);

        return cell.get(new CellCheckBox(By.xpath(".//tr[{0}]/td[{1}]//label"))).isChecked();
    }

}

class CellCheckBox extends CheckBox {

    CellCheckBox(By xpath) {
        super(xpath);
    }

    @Override
    protected boolean isCheckedAction() {
        return Boolean.valueOf(new Element(this.
                getWebElement().
                findElement(By.xpath("../input"))).
                getInvisibleElement().getAttribute("checked"));
    }
}
