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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Morokov Danila on 22.05.2018.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface JTypeAhead {

    /**
     * Usually this is a div, a root element of TypeAhead where you need to click to activate TypeAhead's input
     * for searching by text
     */
    FindBy root() default @FindBy();

    /**
     * An input for searching by text
     */
    FindBy input() default @FindBy();

    /**
     * A list with choices, can be dynamically updated by Ajax requests by a text from the input
     */
    FindBy list() default @FindBy();

    /**
     * Timeout in ms before click, send keys and select a value from the list
     */
    int actionTimeout() default 0;
}
