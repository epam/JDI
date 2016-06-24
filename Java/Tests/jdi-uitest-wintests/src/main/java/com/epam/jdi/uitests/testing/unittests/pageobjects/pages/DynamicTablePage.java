package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.testing.unittests.enums.ColumnHeaders;
import com.epam.jdi.uitests.testing.unittests.pageobjects.EpamJDIApplication;
import com.epam.jdi.uitests.testing.unittests.pageobjects.composite.DynamicTable;
import com.epam.jdi.uitests.win.winium.elements.common.Button;
import com.epam.jdi.uitests.win.winium.elements.complex.DropList;
import com.epam.jdi.uitests.win.winium.elements.composite.WebPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Natalia_Grebenshchik on 10/14/2015.
 */
public class DynamicTablePage extends WebPage {
    @FindBy(css = ".table-delete")
    public DynamicTable dynamicTable;

    @FindBy(css = ".dropdown-toggle[type=button]")
    public Button dropDownButton;

    public ColumnDropList<ColumnHeaders> tableColumnDD = new ColumnDropList<ColumnHeaders>(
            By.xpath("*//ul[contains(@class,'dropdown-menu inner selectpicker')]"),
            By.xpath("*//ul[contains(@class,'dropdown-menu inner selectpicker')]/li")) {
        @Override
        public void selectByName(String name) {
            EpamJDIApplication.dynamicTablePage.dropDownButton.click();
            EpamJDIApplication.dynamicTablePage.tableColumnDD.select(name);
            EpamJDIApplication.dynamicTablePage.dropDownButton.click();
        }
    };

    @FindBy(xpath = "*//button[text()='Reestablish']")
    public Button reestablishBtn;
    @FindBy(xpath = "*//button[text()='Apply']")
    public Button applyBtn;

    public abstract class ColumnDropList<ColHeaders extends Enum> extends DropList<ColHeaders> {
        public ColumnDropList(By xpath, By xpath1) {
            super(xpath, xpath1);
        }

        public abstract void selectByName(String name);
    }

    public Boolean addColumn(ColumnHeaders columnHeader) {
        this.tableColumnDD.selectByName(columnHeader.value);
        this.applyBtn.click();

        return true;
    }

    public Boolean deleteColumn(ColumnHeaders columnHeader) {
        this.tableColumnDD.selectByName(columnHeader.value);
        this.applyBtn.click();

        return true;
    }
}
