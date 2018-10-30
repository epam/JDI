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

import io.qameta.allure.Step;

/**
 * Created by Roman_Iovlev on 7/26/2015.
 */
public interface IVisible {
    /**
     * @return Check is Element visible
     */
    @Step
    boolean isDisplayed();

    /**
     * @return Check is Element hidden
     */
    @Step
    boolean isHidden();

    /**
     * Waits while Element becomes visible
     */
    @Step
    void waitDisplayed();

    /**
     * Waits while Element becomes invisible
     */
    @Step
    void waitVanished();

}