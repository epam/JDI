package com.epam.jdi.uitests.win.winnium.elements.complex;

import com.epam.jdi.uitests.core.interfaces.complex.IComboBox;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.DropdownManager;
import com.epam.jdi.uitests.win.winnium.elements.base.managers.WebElementTextManager;
import com.epam.jdi.uitests.win.winnium.elements.common.Label;
import org.openqa.selenium.By;

import java.util.List;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class ComboBoxElement<TEnum extends Enum & Supplier<String>>  extends Element implements IComboBox<TEnum> {
    private WebElementTextManager textFromWebElement = new WebElementTextManager(this);
    private DropdownManager<TEnum> dropdownManager;

    public ComboBoxElement() {
        super();
    }
    public ComboBoxElement(By label) {
        super(); labelLocator = label;
    }
    public String label() {
        if (labelLocator == null)
            throw exception("Label locator no specified");
        Label label = new Label();
        label.getAvatar().setByLocator(labelLocator);
        label.setParent(getParent());
        return label.getText();
    }
    public By labelLocator;

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
        return getWebElement().getText();
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
    public String waitText(String text) {
        return dropdownManager.waitText(text);
    }

    @Override
    public String waitMatchText(String regEx) {
        return dropdownManager.waitMatchText(regEx);
    }

    @Override
    public void setValue(String value) {
        textFromWebElement.setValue(value);
    }

    @Override
    public String getValue() {
        return textFromWebElement.getValue();
    }

    @Override
    public String getText() {
        return textFromWebElement.getText();
    }

    @Override
    public void input(CharSequence text) {
        textFromWebElement.input(text);
    }

    @Override
    public void newInput(CharSequence text) {
        textFromWebElement.newInput(text);
    }

    @Override
    public void clear() {
        textFromWebElement.clear();
    }

    @Override
    public void focus() {
        textFromWebElement.focus();
    }

    public void setUp(By expandBy, By elementsByName) {
        if (expandBy != null)
            avatar.setByLocator(expandBy);

        dropdownManager = new DropdownManager<>(this, elementsByName);
    }
}
