package com.epam.jdi.uitests.core.interfaces.base;
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

import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

import static com.epam.commons.PrintUtils.print;

/**
 * Created by Roman_Iovlev on 6/10/2015.
 */
public interface IMultiSelector<TEnum extends Enum> extends IBaseElement, ISetValue {
    /**
     * @param names Specify names
     *              Select options with name (use text) from list (change their state selected/deselected)
     */
    @Step
    void select(String... names);

    /**
     * @param names Specify names
     *              Select options with name (use enum) from list (change their state selected/deselected)
     */
    @Step
    void select(TEnum... names);

    /**
     * @param indexes Specify indexes
     *                Select options with name (use index) from list (change their state selected/deselected)
     */
    @Step
    void select(int... indexes);

    /**
     * @param names Specify names
     *              Check only specified options (use text) from list (all other options unchecked)
     */
    @Step
    void check(String... names);

    /**
     * @param names Specify names
     *              Check only specified options (use enum) from list (all other options unchecked)
     */
    @Step
    void check(TEnum... names);

    /**
     * @param indexes Specify indexes
     *                Check only specified options (use index) from list (all other options unchecked)
     */
    @Step
    void check(int... indexes);

    /**
     * @param names Specify names
     *              Uncheck only specified options (use text) from list (all other options checked)
     */
    @Step
    void uncheck(String... names);

    /**
     * @param names Specify names
     *              Uncheck only specified options (use enum) from list (all other options checked)
     */
    @Step
    void uncheck(TEnum... names);

    /**
     * @param indexes Specify indexes
     *                Uncheck only specified options (use index) from list (all other options checked)
     */
    @Step
    void uncheck(int... indexes);

    /**
     * @return Get names of checked options
     */
    @Step
    List<String> areSelected();

    /**
     * @param names Specify names
     * Wait while all options with names (use text) selected. Return false if this not happens
     */
    @Step
    void waitSelected(String... names);

    /**
     * @param names Specify names
     * Wait while all options with names (use enum) selected. Return false if this not happens
     */
    @Step
    void waitSelected(TEnum... names);

    /**
     * @return Get names of unchecked options
     */
    @Step
    List<String> areDeselected();

    /**
     * @param names Specify names
     * Wait while all options with names (use text) deselected. Return false if this not happens
     */
    @Step
    void waitDeselected(String... names);

    /**
     * @param names Specify names
     * Wait while all options with names (use enum) deselected. Return false if this not happens
     */
    @Step
    void waitDeselected(TEnum... names);

    /**
     * @return Get labels of all options
     */
    @Step
    List<String> getOptions();

    /**
     * @return Get labels of all options
     */
    @Step
    default List<String> getNames() {
        return getOptions();
    }

    /**
     * @return Get labels of all options
     */
    @Step
    default List<String> getValues() {
        return getOptions();
    }

    /**
     * @return Get all options labels in one string separated with “; ”
     */
    @Step
    default String getOptionsAsText() {
        return print(getOptions());
    }

    /**
     * Set all options checked
     */
    @Step
    void checkAll();

    /**
     * Set all options checked
     */
    @Step
    default void selectAll() {
        checkAll();
    }

    /**
     * Set all options unchecked
     */
    @Step
    void clear();

    /**
     * Set all options unchecked
     */
    @Step
    default void uncheckAll() {
        clear();
    }
}