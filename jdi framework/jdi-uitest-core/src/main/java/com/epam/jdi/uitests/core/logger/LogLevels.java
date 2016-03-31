package com.epam.jdi.uitests.core.logger;
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

/**
 * Created by Roman_Iovlev on 6/9/2015.
 */
public enum LogLevels {
    OFF(-1),        // No logging
    FATAL(0),       // Unexpected errors
    ERROR(3),       // Critical errors
    WARNING(4),     // Errors due to wrong params
    INFO(6),        // Actions Info
    DEBUG(7),       // Debug info (not for prod)
    ALL(100);       // All log messages

    private int priority;

    LogLevels(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public boolean equalOrLessThan(LogLevels level) {
        return getPriority() >= level.getPriority();
    }
}