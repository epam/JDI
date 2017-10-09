package com.epam.jdi.uitests.win.winnium.elements;

import com.epam.commons.Timer;
import com.epam.jdi.uitests.core.settings.JDISettings;

import java.util.function.Function;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.core.settings.JDISettings.timeouts;

public class ElementsUtils {
    public static  <T> T waitByCondition(Supplier<T> resultSupplier, Function<T, Boolean> conditionFunction,
                                         String exceptionStr, Object ...args) {
        int waitElementSec = JDISettings.timeouts.getCurrentTimeoutSec();
        Timer timer = new Timer(waitElementSec * 1000);
        T resultByCondition = timer.getResultByCondition(resultSupplier, conditionFunction);
        if (resultByCondition == null)
            throw JDISettings.exception(exceptionStr, args);

        return resultByCondition;
    }

    public static Timer timer() {
        return new Timer(timeouts.getCurrentTimeoutSec() * 1000);
    }
}
