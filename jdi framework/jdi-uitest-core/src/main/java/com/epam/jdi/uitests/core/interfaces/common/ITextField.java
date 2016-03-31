package com.epam.jdi.uitests.core.interfaces.common;
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


import com.epam.jdi.uitests.core.annotations.JDIAction;
import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.interfaces.base.ISetValue;

/**
 * Created by Roman_Iovlev on 7/6/2015.
 */
public interface ITextField extends ISetValue, IText, IElement {
    /**
     * @param text Specify text to input to TextField
     *             Input text in textfield
     */
    @JDIAction
    void input(CharSequence text);

    /**
     * @param text Specify text to send keys to TextField
     *             Input text in textfield
     */
    @JDIAction
    default void sendKeys(CharSequence text) {
        input(text);
    }
    /**
     * @param text Specify text to input to TextField
     *             Clear and input text in textfield
     */
    @JDIAction
    void newInput(CharSequence text);

    /**
     * Clear textfield
     */
    @JDIAction
    void clear();

    /**
     * Focus(click) on textfield
     */
    @JDIAction
    void focus();
}