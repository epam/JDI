package com.epam.jdi.uitests.win.winnium.elements.base.managers;

import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.win.settings.WinSettings;
import com.epam.jdi.uitests.win.winnium.elements.ElementsUtils;
import com.epam.jdi.uitests.win.winnium.elements.base.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import winium.elements.desktop.ComboBox;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DropdownManager <TEnum extends Enum & Supplier<String>> {
    private static final int OFFSET = 10;
    private Element element;
    private By elementsByName;

    public DropdownManager(Element element, By elementsByName) {
        this.element = element;
        this.elementsByName = elementsByName;
    }

    public void click() {
        WebElement webElement = element.getWebElement();
        Actions build = new Actions((WebDriver) WinSettings.driverFactory.getDriver());
        build.moveToElement(webElement, webElement.getSize().getWidth() - OFFSET, OFFSET).click().build().perform();
    }

    public void expand() {
        ComboBox comboBox = new ComboBox(element.getWebElement());
        if (!comboBox.isExpanded())
            click();
    }

    public void close() {
        ComboBox comboBox = new ComboBox(element.getWebElement());
        if (comboBox.isExpanded())
            click();
    }

    public void select(String name) {
        List<WebElement> webElements = getNestedWebElements();
        Optional<WebElement> optWebElement =
                webElements.stream().filter(w -> name.equals(w.getAttribute("Name"))).findFirst();
        optWebElement.orElseThrow(() -> JDISettings.exception("Can't find nested element with name: %s", name)).click();
    }

    public void select(TEnum name) {
        select(name.get());
    }

    public void select(int index) {
        List<WebElement> webElements = getNestedWebElements();
        webElements.get(index - 1).click();
    }

    private List<WebElement> getNestedWebElements() {
        expand();
        return element.getWebElement().findElements(elementsByName);
    }

    public String getSelected() {
        return getSelectedRemWebElement().getAttribute("Name");
    }

    private RemoteWebElement getSelectedRemWebElement() {
        ComboBox comboBox = new ComboBox(element.getWebElement());
        RemoteWebElement selected;
        try {
            selected = comboBox.findSelected();
        } catch (NoSuchElementException e) {
            expand();
            close();
            selected = comboBox.findSelected();
        }
        return selected;
    }

    public int getSelectedIndex() {
        String selectedStr = getSelected();
        List<WebElement> webElements = getNestedWebElements();
        OptionalInt first = IntStream.range(0, webElements.size()).
                filter(i -> selectedStr.equals(webElements.get(i).getAttribute("Name"))).findFirst();
        close();

        return first.getAsInt() + 1;
    }

    public boolean isSelected(String name) {
        return getSelected().equals(name);
    }

    public boolean isSelected(TEnum name) {
        return getSelected().equals(name.get());
    }

    public void waitSelected(String name) {
        ElementsUtils.waitByCondition(this::getSelected, name::equals,  "Element %s can't be selected", name);
    }

    public void waitSelected(TEnum name) {
        ElementsUtils.waitByCondition(this::getSelected, name.get()::equals, "Element %s can't be selected", name.get());
    }

    public List<String> getOptions() {
        List<String> options =
                getNestedWebElements().stream().map(w -> w.getAttribute("Name")).collect(Collectors.toList());
        close();
        return options;
    }

    public void setValue(String value) {
        select(value);
    }

    public String getText() {
        return getSelected();
    }

    public String waitText(String text) {
        return ElementsUtils.waitByCondition(this::getSelected, str -> str.contains(text),
                "Element that contains %s wasn't selected", text);
    }

    public String waitMatchText(String regEx) {
        return ElementsUtils.waitByCondition(this::getSelected, str -> str.matches(regEx),
                "Can't find element that matches %s", regEx);
    }

    public String getValue() {
        return getSelected();
    }
}
