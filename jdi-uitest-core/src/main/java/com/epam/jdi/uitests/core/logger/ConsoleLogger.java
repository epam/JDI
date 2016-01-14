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
package com.epam.jdi.uitests.core.logger;

import com.epam.jdi.uitests.core.logger.base.AbstractLogger;
import com.epam.jdi.uitests.core.logger.enums.BusinessInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogLevels;

import static com.epam.commons.Timer.nowTime;
import static java.lang.String.format;
import static java.lang.System.out;

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public class ConsoleLogger extends AbstractLogger {
    public ConsoleLogger() {
        super();
    }

    public ConsoleLogger(LogLevels logLevel) {
        super(logLevel);
    }

    public void inLog(String message, LogLevels logLevel, LogInfoTypes logInfoType) {
        out.println(String.format("%s [%s-%s]: %s ", nowTime(), logInfoType, logLevel, message));
    }

    public void inLog(String message, BusinessInfoTypes infoType) {
        out.println(format("%s [%s]: %s ", nowTime(), infoType, message));
    }
}