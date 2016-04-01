package com.epam.jdi.uitests.gui.sikuli.elements.actions;
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



import com.epam.commons.Timer;
import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.gui.sikuli.elements.BaseElement;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.reporting.PerformanceStatistic.addStatistic;
import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 8/10/2015.
 */
public class ActionScenarios {
    private BaseElement element;

    public ActionScenarios setElement(BaseElement element) {
        this.element = element;
        return this;
    }

    public void actionScenario(String actionName, JAction jAction, LogLevels logSettings) {
        element.logAction(actionName, logSettings);
        Timer timer = new Timer();
        new Timer(timeouts.currentTimeoutSec).wait(() -> {
            jAction.invoke();
            return true;
        });
        logger.info(actionName + " done");
        addStatistic(timer.timePassedInMSec());
    }

    public <TResult> TResult resultScenario(String actionName, Supplier<TResult> jAction, Function<TResult, String> logResult, LogLevels level) {
        element.logAction(actionName);
        Timer timer = new Timer();
        TResult result = new Timer(timeouts.currentTimeoutSec)
                .getResultByCondition(jAction::get, res -> true);
        if (result == null)
            throw asserter.exception("Do action %s failed. Can't got result", actionName);
        String stringResult = (logResult == null)
                ? result.toString()
                : JDISettings.asserter.silent(() -> logResult.apply(result));
        Long timePassed = timer.timePassedInMSec();
        addStatistic(timer.timePassedInMSec());
        toLog(format("Get result '%s' in %s seconds", stringResult,
                format("%.2f", (double) timePassed / 1000)), level);
        return result;
    }
}