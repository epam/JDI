package com.epam.jdi.uitests.mobile.appium.elements.complex.table.interfaces;
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
import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.mobile.appium.elements.base.SelectElement;

/**
 * Created by Roman_Iovlev on 7/28/2015.
 */
public interface ITableLine {
    /**
     * Get Columns/Rows count
     */
    @JDIAction
    int count();

    /**
     * Get Columns/Rows headers
     */
    @JDIAction
    String[] headers();

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
    @JDIAction
    MapArray<String, MapArray<String, ICell>> get();

    @JDIAction
    MapArray<String, MapArray<String, String>> getAsText();

    @JDIAction
    MapArray<String, SelectElement> header();

    @JDIAction
    SelectElement header(String name);
}