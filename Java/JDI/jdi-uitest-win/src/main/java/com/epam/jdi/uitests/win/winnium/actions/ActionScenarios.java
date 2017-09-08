package com.epam.jdi.uitests.win.winnium.actions;

import com.epam.commons.Timer;
import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.logger.LogLevels;
import com.epam.jdi.uitests.core.settings.JDISettings;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.logger.LogLevels.INFO;
import static com.epam.jdi.uitests.core.reporting.PerformanceStatistic.addStatistic;
import static com.epam.jdi.uitests.core.settings.JDISettings.asserter;
import static com.epam.jdi.uitests.core.settings.JDISettings.timeouts;

public class ActionScenarios {
    public void actionScenario(String actionName, JAction jAction, LogLevels logSettings, String baseElementToString) {
        logAction(actionName, logSettings, baseElementToString);
        Timer timer = new Timer();
        new Timer(timeouts.getCurrentTimeoutSec()).wait(() -> {
            jAction.invoke();
            return true;
        });
        JDISettings.logger.info("Done");
        addStatistic(timer.timePassedInMSec());
    }

    public <TResult> TResult resultScenario(String actionName, Supplier<TResult> jAction,
                                            Function<TResult, String> logResult, LogLevels level,
                                            String baseElementToString) {
        logAction(actionName, baseElementToString);
        TResult result;
        try {
            result = new Timer(timeouts.getCurrentTimeoutSec()).getResult(jAction::get);
        } catch (Exception | Error ex) {
            throw asserter.exception("Do action %s failed. Can't got result. Reason: %s", actionName, ex.getMessage());
        }
        if (result == null)
            throw asserter.exception("Do action %s failed. Can't got result", actionName);
        String stringResult = logResult == null
                ? result.toString()
                : JDISettings.asserter.silent(() -> logResult.apply(result));
        Timer timer = new Timer();
        Long timePassed = timer.timePassedInMSec();
        addStatistic(timer.timePassedInMSec());
        JDISettings.toLog(String.format("Get result '%s' in %s seconds", stringResult,
                String.format("%.2f", (double) timePassed / 1000)), level);
        return result;
    }

    private void logAction(String actionName, LogLevels level, String baseElementToString) {
        JDISettings.toLog(String.format(JDISettings.shortLogMessagesFormat
                ? "%s for %s"
                : "Perform action '%s' with Element (%s)", actionName, baseElementToString), level);
    }

    private void logAction(String actionName, String baseElementToString) {
        logAction(actionName, INFO, baseElementToString);
    }
}
