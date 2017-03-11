package com.epam.jdi.uitests.core.logger;

import org.slf4j.Logger;

/**
 * Created by Roman_Iovlev on 11/29/2016.
 */
public interface ILogger extends Logger {
    void logEnabled();
    void logDisabled();
}
