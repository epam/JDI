package com.epam.jdi.uitests.core.interfaces.complex.interfaces;
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


import com.epam.commons.map.MapArray;
import com.epam.jdi.uitests.core.interfaces.base.ISelect;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

/**
 * Created by Roman_Iovlev on 7/28/2015.
 */
public interface ITableLine {
    /**
     * Get Columns/Rows count
     */
    @Step
    int count();
    int count(boolean acceptEmpty);
    default int size() { return count(); }

    /**
     * Get Columns/Rows headers
     */
    @Step
    List<String> headers();

    /**
     * Get Columns/Rows in following format <br>
     * For rows: rowName>columnName>cell <br>
     * For columns: columnName>rowName>cell <br>
     * e.g. myTable.columns().get().get("Name").get("5")
     * or   myTable.columns().get().count()
     * But better to use specified commands like
     * cell("Name, "5")
     * myTable.columns().count()
     */
    @Step
    MapArray<String, MapArray<String, ICell>> get();

    @Step
    MapArray<String, MapArray<String, String>> getAsText();

    @Step
    MapArray<String, ISelect> header();

    @Step
    ISelect header(String name);
    void removeHeaders(String... names);
    void addHeaders(String... names);
    void clean();

    void setCount(int value);

    int getStartIndex();
}