package com.epam.jdi.uitests.mobile.appium.elements.complex.table;
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


import java.util.function.Function;

/**
 * Created by Roman_Iovlev on 7/17/2015.
 */
abstract class RowColumn {
    private int num;
    private String name;

    RowColumn(int num) {
        this.num = num;
    }

    RowColumn(String name) {
        this.name = name;
    }

    public boolean haveName() {
        return name != null;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public <T> T get(Function<RowColumn, T> action) {
        return action.apply(this);
    }

    public <T> T get(Function<String, T> nameAction, Function<Integer, T> numAction) {
        return haveName() ? nameAction.apply(name) : numAction.apply(num);
    }
}