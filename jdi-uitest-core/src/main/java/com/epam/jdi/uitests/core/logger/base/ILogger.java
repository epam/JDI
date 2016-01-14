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
package com.epam.jdi.uitests.core.logger.base;

import com.epam.jdi.uitests.core.logger.enums.BusinessInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogLevels;


/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public interface ILogger {
    void init(String message, Object... args);

    void suit(String message, Object... args);

    void test(String message, Object... args);

    void step(String message, Object... args);

    void fatal(String message, Object... args);

    void error(LogInfoTypes logInfoType, String message, Object... args);

    void warning(LogInfoTypes logInfoType, String message, Object... args);

    void info(String message, Object... args);

    void debug(String message, Object... args);

    void toLog(String message, LogSettings settings, Object... args);

    LogLevels getLogLevel();

    void setLogLevels(LogLevels logLevels);

    void inLog(String message, LogLevels logLevel, LogInfoTypes logInfoType);

    void inLog(String message, BusinessInfoTypes infoType);
}