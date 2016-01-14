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
 * without even the implied warranty ofMERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JDI. If not, see <http://www.gnu.org/licenses/>.
 */
package com.epam.jdi.uitests.core.utils.interfaces;

import com.epam.jdi.uitests.core.interfaces.base.IBaseElement;
import com.epam.jdi.uitests.core.logger.base.LogSettings;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by 12345 on 20.11.2014.
 */
public interface IScenarioWithResult {
    <TResult> TResult invoke(
            IBaseElement element, String actionName, Supplier<TResult> viAction,
            Function<TResult, String> logResult, LogSettings logSettings);
}