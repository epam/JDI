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


import com.epam.commons.linqinterfaces.JFuncTREx;

import java.util.List;

/**
 * Created by roman.i on 20.10.2014.
 */

public interface IEntityTable<E,R> extends ITable, List<E> {

    List<R> getRows();
    List<R> getRows(JFuncTREx<R, Boolean> colNames);
    R firstRow(JFuncTREx<R, Boolean> rule);

    R getRow(String value, Column column);

    R getRow(int rowNum);

    R getRow(String rowName);

    List<E> entities(String... colNames);

    default List<E> entities(){
        return all();
    }

    E entity(int rowNum);

    E entity(String value, Column column);

    E entity(String rowName);

    List<E> all();

    default E first(){
        return entity(1);
    }

    default E last(){
        return entity(size());
    }
}