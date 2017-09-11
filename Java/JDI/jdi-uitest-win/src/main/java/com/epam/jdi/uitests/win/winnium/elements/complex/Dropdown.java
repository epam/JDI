package com.epam.jdi.uitests.win.winnium.elements.complex;

import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.DropdownManager;
import org.openqa.selenium.By;

import java.util.List;
import java.util.function.Supplier;

public class Dropdown<TEnum extends Enum & Supplier<String>> extends Element implements IDropDown<TEnum> {
    private DropdownManager<TEnum> dropdownManager;

    @Override
    public void click() {
        dropdownManager.click();
    }

    @Override
    public void expand() {
        dropdownManager.expand();
    }

    @Override
    public void close() {
        dropdownManager.close();
    }
    @Override
    public void select(String name) {
        dropdownManager.select(name);
    }

    @Override
    public void select(TEnum name) {
        dropdownManager.select(name);
    }

    @Override
    public void select(int index) {
        dropdownManager.select(index);
    }

    @Override
    public String getSelected() {
        return dropdownManager.getSelected();
    }

    @Override
    public int getSelectedIndex() {
        return dropdownManager.getSelectedIndex();
    }

    @Override
    public boolean isSelected(String name) {
        return dropdownManager.isSelected(name);
    }

    @Override
    public boolean isSelected(TEnum name) {
        return dropdownManager.isSelected(name);
    }

    @Override
    public void waitSelected(String name) {
        dropdownManager.waitSelected(name);
    }

    @Override
    public void waitSelected(TEnum name) {
        dropdownManager.waitSelected(name);
    }

    @Override
    public List<String> getOptions() {
        return dropdownManager.getOptions();
    }

    @Override
    public void setValue(String value) {
        dropdownManager.setValue(value);
    }

    @Override
    public String getText() {
        return dropdownManager.getText();
    }

    @Override
    public String waitText(String text) {
        return dropdownManager.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return dropdownManager.waitMatchText(regEx);
    }

    @Override
    public String getValue() {
        return dropdownManager.getValue();
    }

    public void setUp(By expandBy, By elementsByName) {
        if (expandBy != null)
            avatar.setByLocator(expandBy);

        dropdownManager = new DropdownManager<>(this, elementsByName);
    }
}
