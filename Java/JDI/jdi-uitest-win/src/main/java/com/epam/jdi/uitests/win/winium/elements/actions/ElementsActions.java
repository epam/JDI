package com.epam.jdi.uitests.win.winium.elements.actions;
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


import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.win.winium.elements.BaseElement;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.commons.LinqUtils.where;
import static com.epam.commons.PrintUtils.print;
import static com.epam.commons.Timer.getByCondition;
import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static java.lang.String.format;
import static java.lang.String.join;

/**
 * Created by Roman_Iovlev on 9/3/2015.
 */
public class ElementsActions {
    private BaseElement element;

    public ElementsActions(BaseElement element) {
        this.element = element;
    }

    public ActionInvoker invoker() {
        return element.invoker;
    }

    // Element Actions
    public boolean isDisplayed(Supplier<Boolean> isDisplayed) {
        return invoker().doJActionResult("Is element displayed", isDisplayed);
    }

    public boolean isHidden(Supplier<Boolean> isHidden) {
        return invoker().doJActionResult("Is element hidden", isHidden);
    }

    public void waitDisplayed(Supplier<Boolean> isDisplayed) {
        invoker().doJActionResult("Wait element displayed", isDisplayed);
    }

    public void waitVanished(Supplier<Boolean> isVanished) {
        invoker().doJActionResult("Wait element vanished", isVanished);
    }

    // Value Actions
    public String getValue(Supplier<String> getValueFunc) {
        return invoker().doJActionResult("Get value", getValueFunc);
    }

    public void setValue(String value, Consumer<String> setValueAction) {
        invoker().doJAction("Get value", () -> setValueAction.accept(value));
    }

    // Click Action
    public void click(JAction clickAction) {
        invoker().doJAction("Click on Element", clickAction);
    }

    // Text Actions
    public String getText(Supplier<String> getTextAction) {
        return invoker().doJActionResult("Get text", getTextAction);
    }

    public String waitText(String text, Supplier<String> getTextAction) {
        return invoker().doJActionResult(format("Wait text contains '%s'", text),
                () -> getByCondition(getTextAction, t -> t.contains(text)));
    }

    public String waitMatchText(String regEx, Supplier<String> getTextAction) {
        return invoker().doJActionResult(format("Wait text match regex '%s'", regEx),
                () -> getByCondition(getTextAction, t -> t.matches(regEx)));
    }

    // Check/Select Actions
    public boolean isSelected(Supplier<Boolean> isSelectedAction) {
        return invoker().doJActionResult("Is Selected", isSelectedAction);
    }

    public void check(JAction checkAction) {
        invoker().doJAction("Check Checkbox", checkAction);
    }

    public void uncheck(JAction uncheckAction) {
        invoker().doJAction("Uncheck Checkbox", uncheckAction);
    }

    public boolean isChecked(Supplier<Boolean> isCheckedAction) {
        return invoker().doJActionResult("IsChecked",
                isCheckedAction,
                result -> "Checkbox is " + (result ? "checked" : "unchecked"));
    }

    // Input Actions
    public void inputLines(JAction clearAction, Consumer<String> inputAction, String... textLines) {
        invoker().doJAction("Input several lines of text in textarea",
                () -> {
                    clearAction.invoke();
                    inputAction.accept(join("\n", textLines));
                });
    }

    public void addNewLine(String textLine, Consumer<String> inputAction) {
        invoker().doJAction("Add text from new line in textarea",
                () -> inputAction.accept("\n" + textLine));
    }

    public String[] getLines(Supplier<String> getTextAction) {
        return invoker().doJActionResult("Get text as lines", () -> getTextAction.get().split("\\n"));
    }

    public void input(CharSequence text, Consumer<CharSequence> inputAction) {
        invoker().doJAction("Input text '" + text + "' in text field",
                () -> inputAction.accept(text));
    }

    public void clear(JAction clearAction) {
        invoker().doJAction("Clear text field", clearAction);
    }

    public void focus(JAction focusAction) {
        invoker().doJAction("Focus on text field", focusAction);
    }

    // Selector
    public void select(String name, Consumer<String> selectAction) {
        invoker().doJAction(format("Select '%s'", name), () -> selectAction.accept(name));
    }

    public void select(int index, Consumer<Integer> selectByIndexAction) {
        invoker().doJAction(format("Select '%s'", index), () -> selectByIndexAction.accept(index));
    }

    public boolean isSelected(String name, Function<String, Boolean> isSelectedAction) {
        return invoker().doJActionResult(format("Wait is '%s' selected", name), () -> isSelectedAction.apply(name));
    }

    public String getSelected(Supplier<String> isSelectedAction) {
        return invoker().doJActionResult("Get Selected element name", isSelectedAction);
    }

    public int getSelectedIndex(Supplier<Integer> isSelectedAction) {
        return invoker().doJActionResult("Get Selected element index", isSelectedAction);
    }

    //MultiSelector
    public void select(Consumer<String[]> selectListAction, String... names) {
        invoker().doJAction(String.format("Select '%s'", print(names)), () -> selectListAction.accept(names));
    }

    public void select(Consumer<int[]> selectListAction, int[] indexes) {
        List<String> listIndexes = new ArrayList<>();
        for (int i : indexes)
            listIndexes.add(Integer.toString(i));
        invoker().doJAction(String.format("Select '%s'", print(listIndexes)), () -> selectListAction.accept(indexes));
    }

    public List<String> areSelected(Supplier<List<String>> getNames, Function<String, Boolean> waitSelectedAction) {
        return invoker().doJActionResult("Are selected", () ->
                where(getNames.get(), waitSelectedAction));
    }

    public void waitSelected(Function<String, Boolean> waitSelectedAction, String... names) {
        boolean result = invoker().doJActionResult(String.format("Are deselected '%s'", print(names)), () -> {
            for (String name : names)
                if (!waitSelectedAction.apply(name))
                    return false;
            return true;
        });
        asserter.isTrue(result);
    }

    public List<String> areDeselected(Supplier<List<String>> getNames, Function<String, Boolean> waitSelectedAction) {
        return invoker().doJActionResult("Are deselected", () ->
                where(getNames.get(), name -> !waitSelectedAction.apply(name)));
    }

    public void waitDeselected(Function<String, Boolean> waitSelectedAction, String... names) {
        boolean result = invoker().doJActionResult(String.format("Are deselected '%s'",
                print(names)), () -> {
            for (String name : names)
                if (waitSelectedAction.apply(name))
                    return false;
            return true;
        });
        asserter.isTrue(result);
    }

    public <T> T findImmediately(Supplier<T> func, T ifError) {
        element.setWaitTimeout(0);
        Function<WebElement, Boolean> temp = element.avatar.localElementSearchCriteria;
        element.avatar.localElementSearchCriteria = el -> true;
        T result;
        try {
            result = func.get();
        } catch (Exception | Error ex) {
            result = ifError;
        }
        element.avatar.localElementSearchCriteria = temp;
        element.restoreWaitTimeout();
        return result;
    }
}