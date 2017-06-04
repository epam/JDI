package com.epam.jdi.uitests.core.logger;

import com.epam.commons.linqinterfaces.JActionEx;
import com.epam.commons.linqinterfaces.JFuncREx;
import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.ArrayList;
import java.util.List;

import static com.epam.jdi.uitests.core.logger.LogLevels.*;
import static java.lang.Thread.currentThread;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Roman_Iovlev on 1/25/2016.
 */
public class JDILogger implements ILogger {

    public JDILogger() {
        logger = getLogger("JDI Logger");
        this.name = "JDI Logger";
    }
    public JDILogger(String name) {
        logger = getLogger(name);
        this.name = name;
    }

    private LogLevels settingslogLevel = INFO;
    public LogLevels logLevel = settingslogLevel;

    public void logOff(JActionEx action) {
        logOff(() -> { action.invoke(); return null; });
    }
    public <T> T logOff(JFuncREx<T> func) {
        if (logLevel == OFF) {
            try { return func.invoke();
            } catch (Exception ex) { throw new RuntimeException(ex); }
        }
        logLevel = OFF;
        T result;
        try{ result = func.invoke(); }
        catch (Exception ex) {throw new RuntimeException(ex); }
        logLevel = settingslogLevel;
        return result;
    }
    public void setLogLevel(LogLevels logLevel) {
        this.logLevel = logLevel;
        this.settingslogLevel = logLevel;
    }
    private String name;
    private Logger logger;
    private List<Long> multiThread = new ArrayList<>();
    private String getRecord(String record) {
        if (!multiThread.contains(currentThread().getId()))
            multiThread.add(currentThread().getId());
        return multiThread.size() > 1
                ? "[ThreadID: " + currentThread().getId() + "]" + record
                : record;
    }


    public String getName() {
        return name;
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public void trace(String s) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(getRecord(s));
    }

    public void trace(String s, Object o) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(getRecord(s), o);
    }

    public void trace(String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(getRecord(s), o, o1);
    }

    public void trace(String s, Object... objects) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(getRecord(s), objects);
    }

    public void trace(String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(getRecord(s), throwable);
    }

    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    public void trace(Marker marker, String s) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(marker, getRecord(s));

    }

    public void trace(Marker marker, String s, Object o) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(marker, getRecord(s), o);

    }

    public void trace(Marker marker, String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(marker, getRecord(s), o, o1);

    }

    public void trace(Marker marker, String s, Object... objects) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(marker, getRecord(s), objects);

    }

    public void trace(Marker marker, String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(TRACE))
            logger.trace(marker, getRecord(s), throwable);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String s) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(getRecord(s));
    }

    public void debug(String s, Object o) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(getRecord(s), o);
    }

    public void debug(String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(getRecord(s), o, o1);

    }

    public void debug(String s, Object... objects) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(getRecord(s), objects);

    }

    public void debug(String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(getRecord(s), throwable);
    }

    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    public void debug(Marker marker, String s) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(marker, getRecord(s));

    }

    public void debug(Marker marker, String s, Object o) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(marker, getRecord(s), o);

    }

    public void debug(Marker marker, String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(marker, getRecord(s), o, o1);

    }

    public void debug(Marker marker, String s, Object... objects) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(marker, getRecord(s), objects);

    }

    public void debug(Marker marker, String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(DEBUG))
            logger.debug(marker, getRecord(s), throwable);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public void info(String s) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(getRecord(s));
    }

    public void info(String s, Object o) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(getRecord(s), o);
    }

    public void info(String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(getRecord(s), o, o1);

    }

    public void info(String s, Object... objects) {
        logger.info(getRecord(s), objects);

    }

    public void info(String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(getRecord(s), throwable);
    }

    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    public void info(Marker marker, String s) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(marker, getRecord(s));

    }

    public void info(Marker marker, String s, Object o) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(marker, getRecord(s), o);

    }

    public void info(Marker marker, String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(marker, getRecord(s), o, o1);

    }

    public void info(Marker marker, String s, Object... objects) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(marker, getRecord(s), objects);

    }

    public void info(Marker marker, String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(INFO))
            logger.info(marker, getRecord(s), throwable);
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public void warn(String s) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(getRecord(s));
    }

    public void warn(String s, Object o) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(getRecord(s), o);
    }

    public void warn(String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(getRecord(s), o, o1);

    }

    public void warn(String s, Object... objects) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(getRecord(s), objects);

    }

    public void warn(String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(getRecord(s), throwable);
    }

    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    public void warn(Marker marker, String s) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(marker, getRecord(s));

    }

    public void warn(Marker marker, String s, Object o) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(marker, getRecord(s), o);

    }

    public void warn(Marker marker, String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(marker, getRecord(s), o, o1);

    }

    public void warn(Marker marker, String s, Object... objects) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(marker, getRecord(s), objects);

    }

    public void warn(Marker marker, String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(WARNING))
            logger.warn(marker, getRecord(s), throwable);
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public void error(String s) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(getRecord(s));
    }

    public void error(String s, Object o) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(getRecord(s), o);
    }

    public void error(String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(getRecord(s), o, o1);

    }

    public void error(String s, Object... objects) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(getRecord(s), objects);

    }

    public void error(String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(getRecord(s), throwable);
    }

    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    public void error(Marker marker, String s) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(marker, getRecord(s));
    }

    public void error(Marker marker, String s, Object o) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(marker, getRecord(s), o);
    }

    public void error(Marker marker, String s, Object o, Object o1) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(marker, getRecord(s), o, o1);
    }

    public void error(Marker marker, String s, Object... objects) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(marker, getRecord(s), objects);
    }

    public void error(Marker marker, String s, Throwable throwable) {
        if (logLevel.equalOrLessThan(ERROR))
            logger.error(marker, getRecord(s), throwable);
    }
}
