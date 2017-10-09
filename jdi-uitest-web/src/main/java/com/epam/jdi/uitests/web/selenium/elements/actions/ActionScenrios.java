package com.epam.jdi.uitests.web.selenium.elements.actions;
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
import com.epam.commons.linqinterfaces.JActionTTTT;
import com.epam.commons.linqinterfaces.JFuncTTTTTR;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.web.selenium.elements.base.BaseElement;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.*;
import static java.lang.String.format;

/**
 * Created by Roman_Iovlev on 8/10/2015.
 */
public class ActionScenrios {
    protected BaseElement element;

    public ActionScenrios setElement(BaseElement element) {
        this.element = element;
        return this;
    }
    public static JActionTTTT<BaseElement, String, JAction, LogLevels> actionScenario =
            (element, actionName, jAction, level) -> {
        element.logAction(actionName, level);
        logger.logOff(() -> {
        try {
            new Timer(timeouts.getCurrentTimeoutSec() * 1000).wait(() -> {
                jAction.invoke();
                return true;
            });
        } catch (Exception | Error ex) {
            throw asserter.exception("Do action %s failed. Can't get result. Reason: %s", actionName, ex.getMessage());
        }});
        logger.debug("Done");
    };

    public void actionScenario(String actionName, JAction jAction, LogLevels level) {
        actionScenario.invoke(element, actionName,
                jAction, level);
    }

    public static JFuncTTTTTR<BaseElement, String, Supplier<Object>, Function<Object, String>,
                LogLevels, Object> resultScenario = (element, actionName, jAction, logResult, level) -> {
        element.logAction(actionName);
        Timer timer = new Timer();
        final Object[] result = new Object[1];
        final String[] stringResult = new String[1];
        logger.logOff(() -> {
            try {
                result[0] = new Timer(timeouts.getCurrentTimeoutSec() * 1000)
                        .getResultByCondition(jAction, res -> true);
                if (result[0] == null)
                    throw asserter.exception("Do action %s failed. Can't get result", actionName);
                stringResult[0] = logResult == null
                        ? result[0].toString()
                        : asserter.silent(() -> logResult.apply(result[0]));
            } catch (Exception | Error ex) {
                throw asserter.exception("Do action %s failed. Can't get result. Reason: %s", actionName, ex.getMessage());
            }
        });
        toLog(format("Get result '%s' in %s seconds", stringResult[0],
                format("%.2f", (double) timer.timePassedInMSec() / 1000)), level);
        return result[0];
    };

    public <TResult> TResult resultScenario(String actionName, Supplier<TResult> jAction,
                     Function<TResult, String> logResult, LogLevels level) {
        Function<Object, String> lr = logResult != null
                ? r -> logResult.apply((TResult) r)
                : null;
        return (TResult) resultScenario.invoke(element, actionName,
                jAction::get, lr, level);
    }
}