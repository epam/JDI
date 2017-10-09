package com.epam.jdi.uitests.core.interfaces.settings;
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

import com.epam.jdi.uitests.core.interfaces.base.IElement;
import com.epam.jdi.uitests.core.settings.HighlightSettings;

/**
 * Created by Roman_Iovlev on 9/3/2015.
 */
public interface IDriver<T> {
    String registerDriver(String driverName);

    void setRunType(String runType);

    T getDriver();

    boolean hasDrivers();

    boolean hasRunDrivers();

    String currentDriverName();
    void setCurrentDriver(String driverName);

    T getDriver(String name);

    void highlight(IElement element);

    void highlight(IElement element, HighlightSettings highlightSettings);

    String getDriverPath();

    void setDriverPath(String driverPath);
}