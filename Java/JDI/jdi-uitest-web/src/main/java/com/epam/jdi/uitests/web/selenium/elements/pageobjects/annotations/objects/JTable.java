package com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects;
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


import org.openqa.selenium.support.FindBy;

import java.lang.annotation.*;

import static com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.TableHeaderTypes.COLUMN_HEADERS;

/**
 * Created by roman.i on 06.10.2014.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JTable {
    FindBy root() default @FindBy();
    String[] header() default {};
    String[] rowsHeader() default {};

    FindBy cell() default @FindBy();
    FindBy row() default @FindBy();
    FindBy column() default @FindBy();
    FindBy footer() default @FindBy();

    int height() default -1;
    int width() default -1;
    String size() default "";

    int rowStartIndex() default -1;
    int colStartIndex() default -1;

    TableHeaderTypes headerType() default COLUMN_HEADERS;
    boolean useCache() default true;


}