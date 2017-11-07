package com.epam.jdi.uitests.core.logger;

import com.epam.commons.linqinterfaces.JActionEx;
import com.epam.commons.linqinterfaces.JFuncREx;
import org.slf4j.Logger;

/**
 * Created by Roman_Iovlev on 11/29/2016.
 */
public interface ILogger extends Logger {
    <T> T logOff(JFuncREx<T> action);
    void logOff(JActionEx action);
    void step(String msg);
    void setLogLevel(LogLevels levels);
}
