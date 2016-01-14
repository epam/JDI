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

import com.epam.jdi.uitests.core.logger.enums.LogInfoTypes;
import com.epam.jdi.uitests.core.logger.enums.LogLevels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman_Iovlev on 7/13/2015.
 */
public class LogSettings {
    public LogLevels logLevels;
    private int logInfoTypes;
    private LogInfoTypes logInfoType;

    public LogSettings() {
        this(LogLevels.INFO);
    }

    public LogSettings(LogLevels logLevel, LogInfoTypes... logInfoTypes) {
        this.logLevels = logLevel;
        switch (logInfoTypes.length) {
            case 0:
                this.logInfoTypes = LogInfoTypes.BUSINESS.type + LogInfoTypes.FRAMEWORK.type + LogInfoTypes.TECHNICAL.type;
                return;
            case 1:
                this.logInfoType = logInfoTypes[0];
                return;
            default:
                setLogSettings(logInfoTypes);
        }
    }

    public int getLogInfoTypes() {
        return logInfoTypes;
    }

    public LogInfoTypes getLogInfoType() {
        return logInfoType;
    }

    private void setLogSettings(LogInfoTypes[] logInfoTypes) {
        List<LogInfoTypes> usedTypes = new ArrayList<>();
        this.logInfoTypes = 0;
        for (LogInfoTypes type : logInfoTypes)
            if (!usedTypes.contains(type)) {
                usedTypes.add(type);
                this.logInfoTypes += type.type;
            }
    }
}