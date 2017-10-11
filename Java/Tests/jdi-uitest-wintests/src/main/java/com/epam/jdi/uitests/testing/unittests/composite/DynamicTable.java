package com.epam.jdi.uitests.testing.unittests.composite;

import com.epam.jdi.uitests.core.interfaces.complex.tables.interfaces.ICell;
import com.epam.jdi.uitests.win.winnium.elements.apiInteract.GetElementModule;
import com.epam.jdi.uitests.win.winnium.elements.common.CheckBox;
import com.epam.jdi.uitests.win.winnium.elements.common.Label;
import com.epam.jdi.uitests.win.winnium.elements.complex.table.Table;
import org.openqa.selenium.By;

public class DynamicTable extends Table {
    public Label getLabel(int columnIndex, int rowIndex) {
        ICell cell = cell(columnIndex, rowIndex);

        Label label = new Label();
        label.setAvatar(new GetElementModule(By.xpath("./*[contains(@Name, 'See more')]"), label));
        return cell.get(label);
    }

    private CheckBox getCheckBox(int columnIndex, int rowIndex) {
        ICell cell = this.cell(columnIndex, rowIndex);

        CheckBox checkBox = new CheckBox();
        checkBox.setAvatar(new GetElementModule(By.xpath("./*[contains(@ControlType, 'ControlType.CheckBox')]"),
                checkBox));
        cell.get(checkBox);
        return checkBox;
    }

    public void clickCellCheckBox(int columnIndex, int rowIndex) {
        getCheckBox(columnIndex, rowIndex).click();
    }

    public boolean isCellCheckBoxChecked(int columnIndex, int rowIndex) {
        return getCheckBox(columnIndex, rowIndex).isChecked();
    }
}
