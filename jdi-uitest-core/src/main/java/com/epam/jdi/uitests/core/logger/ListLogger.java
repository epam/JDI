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

import com.epam.jdi.uitests.core.logger.base.ILogger;
import com.epam.jdi.uitests.core.logger.base.LogSettings;
import com.epam.jdi.uitests.core.logger.enums.BusinessInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogLevels;

/**
 * Created by Roman_Iovlev on 7/27/2015.
 */
public class ListLogger implements ILogger {
    private ILogger[] loggers;

    public ListLogger(ILogger... loggers) {
        this(LogLevels.INFO, loggers);
    }

    public ListLogger(LogLevels logLevels, ILogger... loggers) {
        this.loggers = loggers;
        setLogLevels(logLevels);
    }

    public void setLogLevels(LogLevels logLevels) {
        for (ILogger logger : loggers)
            logger.setLogLevels(logLevels);
    }

    public void init(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.init(message, args);
    }

    public void suit(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.suit(message, args);
    }

    public void test(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.test(message, args);
    }

    public void step(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.step(message, args);
    }

    public void fatal(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.fatal(message, args);
    }

    public void error(LogInfoTypes logInfoType, String message, Object... args) {
        for (ILogger logger : loggers)
            logger.error(logInfoType, message, args);
    }

    public void warning(LogInfoTypes logInfoType, String message, Object... args) {
        for (ILogger logger : loggers)
            logger.warning(logInfoType, message, args);
    }

    public void info(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.info(message, args);
    }

    public void debug(String message, Object... args) {
        for (ILogger logger : loggers)
            logger.debug(message, args);
    }

    public void toLog(String message, LogSettings settings, Object... args) {
        for (ILogger logger : loggers)
            logger.toLog(message, settings, args);
    }

    public LogLevels getLogLevel() {
        LogLevels logLevel = LogLevels.OFF;
        for (ILogger logger : loggers) {
            if (logLevel.getPriority() < logger.getLogLevel().getPriority())
                logLevel = logger.getLogLevel();
        }
        return logLevel;
    }

    public void inLog(String message, LogLevels logLevel, LogInfoTypes logInfoType) {
        for (ILogger logger : loggers)
            logger.inLog(message, logLevel, logInfoType);
    }

    public void inLog(String message, BusinessInfoTypes infoType) {
        for (ILogger logger : loggers)
            logger.inLog(message, infoType);
    }
}