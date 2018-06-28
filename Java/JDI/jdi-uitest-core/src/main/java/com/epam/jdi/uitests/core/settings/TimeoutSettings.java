package com.epam.jdi.uitests.core.settings;
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

import static java.lang.ThreadLocal.withInitial;

/**
 * Created by 12345 on 04.07.2015.
 */
public class TimeoutSettings {
    private ThreadLocal<Integer> waitElementSec = withInitial(() -> 20);
    private ThreadLocal<Integer> defaultWaitTimeout = withInitial(() -> 20);
    private ThreadLocal<Integer> waitPageLoadSec = withInitial(() -> 20);
    private ThreadLocal<Integer> retryMSec = withInitial(() -> 100);

    public TimeoutSettings() {
        setCurrentTimeoutSec(waitElementSec.get());
    }

    public void setDefaultTimeoutSec(int timeoutSec) {
        defaultWaitTimeout.set(timeoutSec);
    }

    public void setCurrentTimeoutSec(int timeoutSec) {
        waitElementSec.set(timeoutSec);
    }

    public int getDefaultTimeoutSec() {
        return defaultWaitTimeout.get();
    }

    public int getCurrentTimeoutSec() {
        return waitElementSec.get();
    }

    public void dropTimeouts() {
        setCurrentTimeoutSec(defaultWaitTimeout.get());
    }
}