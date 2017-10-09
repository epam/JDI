package com.epam.jdi.uitests.win.winnium.actions;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.logger.LogLevels;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.logger.LogLevels.INFO;
import static com.epam.jdi.uitests.core.settings.JDISettings.exception;

public class ActionInvoker {
    private ActionScenarios actionScenrios = new ActionScenarios();

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action, String elementToString) {
        return doJActionResult(actionName, action, null, INFO, elementToString);
    }

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action, LogLevels level,
                                                   String elementToString) {
        return doJActionResult(actionName, action, null, level, elementToString);
    }

    public final <TResult> TResult doJActionResult(String actionName, Supplier<TResult> action,
                                                   Function<TResult, String> logResult, LogLevels level,
                                                   String elementToString) {
        try {
            return actionScenrios.resultScenario(actionName, action, logResult, level, elementToString);
        } catch (Exception | Error ex) {
            throw exception("Failed to do '%s' action. Reason: %s", actionName, ex);
        }
    }

    public final void doJAction(String actionName, JAction action, String elementToString) {
        doJAction(actionName, action, INFO, elementToString);
    }

    public final void doJAction(String actionName, JAction action, LogLevels level, String elementToString) {
        actionScenrios.actionScenario(actionName, action, level, elementToString);
    }

}
