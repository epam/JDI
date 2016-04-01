package com.epam.jdi.uitests.mobile.appium.elements.actions;
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


import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.core.settings.JDISettings;
import com.epam.jdi.uitests.mobile.appium.elements.BaseElement;
import com.epam.jdi.uitests.mobile.appium.elements.base.Element;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.commons.ReflectionUtils.isClass;
import static com.epam.jdi.uitests.core.logger.LogLevels.INFO;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;
import static com.epam.jdi.uitests.mobile.appium.elements.BaseElement.actionScenrios;

/**
 * Created by Roman_Iovlev on 9/3/2015.
 */
public class ActionInvoker {
    private BaseElement element;

    public ActionInvoker(BaseElement element) {
        JDISettings.newTest();
        this.element = element;
    }

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action) {
        return doJActionResult(actionName, action, null, INFO);
    }

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action, Function<TResult, String> logResult) {
        return doJActionResult(actionName, action, logResult, INFO);
    }

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action, LogLevels level) {
        return doJActionResult(actionName, action, null, level);
    }

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action,
                                                   Function<TResult, String> logResult, LogLevels level) {
        try {
            processDemoMode();
            return actionScenrios.setElement(element).resultScenario(actionName, action, logResult, level);
        } catch (Exception | Error ex) {
            throw exception("Failed to do '%s' action. Exception: %s", actionName, ex);
        }
    }

    public final void doJAction(String actionName, JAction action) {
        doJAction(actionName, action, INFO);
    }

    public final void doJAction(String actionName, JAction action, LogLevels level) {
        try {
            processDemoMode();
            actionScenrios.setElement(element).actionScenario(actionName, action, level);
        } catch (Exception | Error ex) {
            throw exception("Failed to do '%s' action. Exception: %s", actionName, ex);
        }
    }

    public void processDemoMode() {
        if (JDISettings.isDemoMode)
            if (isClass(element.getClass(), Element.class))
                ((Element) element).highlight(JDISettings.highlightSettings);
    }
}