package com.epam.jdi.uitests.win.winnium.elements.complex;

import com.epam.jdi.uitests.core.interfaces.complex.ICheckList;
import com.epam.jdi.uitests.win.winnium.elements.BaseElement;
import com.epam.jdi.uitests.win.winnium.elements.ElementsUtils;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.epam.commons.PrintUtils.print;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class CheckList <TEnum extends Enum & Supplier<String>> extends BaseElement implements ICheckList<TEnum> {
    private void selectByNamesAction(String[] names, Predicate<WebElement> predicate) {
        List<WebElement> elements = getAvatar().getElements();

        for (String name : names) {
            WebElement webElement = elements.stream().filter(el -> el.getAttribute("Name").equals(name)).findFirst().
                    orElseThrow(() -> exception("Can't find option '%s'.", name));

            if (predicate.test(webElement))
                webElement.click();
        }
    }

    @Override
    public void select(String... names) {
        invoker.doJAction(String.format("Select '%s'", print(names)), () -> selectByNamesAction(names, w -> true),
                toString());
    }

    @SafeVarargs
    @Override
    public final void select(TEnum... names) {
        select(getNameStrings(names));
    }

    private String[] getNameStrings(TEnum[] names) {
        String[] nameStrings = new String[names.length];
        for (int i = 0; i < names.length; i++)
            nameStrings[i] = names[i].get();

        return nameStrings;
    }

    private void selectByIndexesAction(int[] indexes, Predicate<WebElement> predicate) {
        List<WebElement> elements = getAvatar().getElements().stream().filter(predicate).collect(Collectors.toList());

        for (int i : indexes) {
            if (i <= 0)
                throw exception("Can't get option with num '%s'. Number should be 1 or more", i);
            if (elements.size() < i)
                throw exception("Can't find option with num '%s'. Find only '%s' options", i, elements.size());

            elements.get(i - 1).click();
        }
    }


    @Override
    public void select(int... indexes) {
        invoker.doJAction(String.format("Select '%s'", print(indexes)), () -> selectByIndexesAction(indexes, w -> true),
                toString());
    }

    @Override
    public void check(String... names) {
        invoker.doJAction(String.format("Check '%s'", print(names)),
                () -> selectByNamesAction(names, w -> !w.isSelected()), toString());
    }

    @Override
    public void check(TEnum... names) {
        check(getNameStrings(names));
    }

    @Override
    public void check(int... indexes) {
        invoker.doJAction(String.format("Check '%s'", print(indexes)),
                () -> selectByIndexesAction(indexes, w -> !w.isSelected()), toString());
    }

    @Override
    public void uncheck(String... names) {
        invoker.doJAction(String.format("Uncheck '%s'", print(names)),
                () -> selectByNamesAction(names, WebElement::isSelected), toString());
    }

    @Override
    public void uncheck(TEnum... names) {
        uncheck(getNameStrings(names));
    }

    @Override
    public void uncheck(int... indexes) {
        invoker.doJAction(String.format("Check '%s'", print(indexes)),
                () -> selectByIndexesAction(indexes, WebElement::isSelected), toString());
    }

    @Override
    public List<String> areSelected() {
        return invoker.doJActionResult("Are selected", this::areSelectedAction, toString());
    }

    private List<String> areSelectedAction() {
        List<WebElement> elements = getAvatar().getElements();

        return elements.stream().filter(WebElement::isSelected).map(e -> e.getAttribute("Name")).
                collect(Collectors.toList());
    }

    @Override
    public void waitSelected(String... names) {
        ElementsUtils.waitByCondition(this::areSelected, selectedList -> selectedList.containsAll(Arrays.asList(names)),
                "%s not selected", print(names));
    }

    @Override
    public void waitSelected(TEnum[] names) {
        String[] nameStrings = getNameStrings(names);
        ElementsUtils.waitByCondition(this::areSelected,
                selectedList -> selectedList.containsAll(Arrays.asList(nameStrings)), "%s not selected",
                print(nameStrings));
    }

    private List<String> areDeSelectedAction() {
        List<WebElement> elements = getAvatar().getElements();

        return elements.stream().filter(e -> !e.isSelected()).map(e -> e.getAttribute("Name")).
                collect(Collectors.toList());
    }

    @Override
    public List<String> areDeselected() {
        return invoker.doJActionResult("Are deselected", this::areDeSelectedAction, toString());
    }

    @Override
    public void waitDeselected(String... names) {
        ElementsUtils.waitByCondition(this::areDeselected,
                selectedList -> selectedList.containsAll(Arrays.asList(names)),
                "%s not selected", print(names));
    }

    @Override
    public void waitDeselected(TEnum[] names) {
        String[] nameStrings = getNameStrings(names);
        ElementsUtils.waitByCondition(this::areDeselected,
                selectedList -> selectedList.containsAll(Arrays.asList(nameStrings)),
                "%s not selected", print(nameStrings));
    }

    @Override
    public List<String> getOptions() {
        List<WebElement> elements = getAvatar().getElements();

        return elements.stream().map(el -> el.getAttribute("Name")).collect(Collectors.toList());
    }

    private void checkAllAction() {
        List<WebElement> elements = getAvatar().getElements();

        elements.stream().filter(e -> !e.isSelected()).forEachOrdered(WebElement::click);
    }

    @Override
    public void checkAll() {
        invoker.doJAction("Check all", this::checkAllAction, toString());
    }

    private void clearAction() {
        List<WebElement> elements = getAvatar().getElements();

        elements.stream().filter(WebElement::isSelected).forEachOrdered(WebElement::click);
    }

    @Override
    public void clear() {
        invoker.doJAction("Check all", this::clearAction, toString());
    }

    @Override
    public void setValue(String value) {
        invoker.doJAction(String.format("Set value '%s'", value),
                () -> selectByNamesAction(new String[]{value}, w -> true), toString());
    }

    @Override
    public String getValue() {
        return invoker.doJActionResult("get value",() -> print(areSelectedAction()), toString());
    }
}
