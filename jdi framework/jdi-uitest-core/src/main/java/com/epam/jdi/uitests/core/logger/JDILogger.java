package com.epam.jdi.uitests.core.logger;

import org.slf4j.Logger;
import org.slf4j.Marker;

import java.util.function.Function;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Roman_Iovlev on 1/25/2016.
 */
public class JDILogger implements Logger {

    public JDILogger() {
        logger = getLogger("JDI Logger");
        this.name = "JDI Logger";
    }
    public JDILogger(String name) {
        logger = getLogger(name);
        this.name = name;
    }
    public JDILogger(String name, Function<String, String> pattern) {
        logger = getLogger(name);
        this.name = name;
        this.pattern = pattern;
    }

    private String name;
    private Logger logger;
    private Function<String, String> pattern = s -> s;

    public String getName() {
        return name;
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public void trace(String s) {
        logger.trace(pattern.apply(s));

    }

    public void trace(String s, Object o) {
        logger.trace(pattern.apply(s), o);
    }

    public void trace(String s, Object o, Object o1) {
        logger.trace(pattern.apply(s), o, o1);
    }

    public void trace(String s, Object... objects) {
        logger.trace(pattern.apply(s), objects);
    }

    public void trace(String s, Throwable throwable) {
        logger.trace(pattern.apply(s), throwable);
    }

    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    public void trace(Marker marker, String s) {
        logger.trace(marker, pattern.apply(s));

    }

    public void trace(Marker marker, String s, Object o) {
        logger.trace(marker, pattern.apply(s), o);

    }

    public void trace(Marker marker, String s, Object o, Object o1) {
        logger.trace(marker, pattern.apply(s), o, o1);

    }

    public void trace(Marker marker, String s, Object... objects) {
        logger.trace(marker, pattern.apply(s), objects);

    }

    public void trace(Marker marker, String s, Throwable throwable) {
        logger.trace(marker, pattern.apply(s), throwable);
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public void debug(String s) {
        logger.debug(pattern.apply(s));
    }

    public void debug(String s, Object o) {
        logger.debug(pattern.apply(s), o);
    }

    public void debug(String s, Object o, Object o1) {
        logger.debug(pattern.apply(s), o, o1);

    }

    public void debug(String s, Object... objects) {
        logger.debug(pattern.apply(s), objects);

    }

    public void debug(String s, Throwable throwable) {
        logger.debug(pattern.apply(s), throwable);
    }

    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    public void debug(Marker marker, String s) {
        logger.debug(marker, pattern.apply(s));

    }

    public void debug(Marker marker, String s, Object o) {
        logger.debug(marker, pattern.apply(s), o);

    }

    public void debug(Marker marker, String s, Object o, Object o1) {
        logger.debug(marker, pattern.apply(s), o, o1);

    }

    public void debug(Marker marker, String s, Object... objects) {
        logger.debug(marker, pattern.apply(s), objects);

    }

    public void debug(Marker marker, String s, Throwable throwable) {
        logger.debug(marker, pattern.apply(s), throwable);
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public void info(String s) {
        logger.info(pattern.apply(s));
    }

    public void info(String s, Object o) {
        logger.info(pattern.apply(s), o);
    }

    public void info(String s, Object o, Object o1) {
        logger.info(pattern.apply(s), o, o1);

    }

    public void info(String s, Object... objects) {
        logger.info(pattern.apply(s), objects);

    }

    public void info(String s, Throwable throwable) {
        logger.info(pattern.apply(s), throwable);
    }

    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    public void info(Marker marker, String s) {
        logger.info(marker, pattern.apply(s));

    }

    public void info(Marker marker, String s, Object o) {
        logger.info(marker, pattern.apply(s), o);

    }

    public void info(Marker marker, String s, Object o, Object o1) {
        logger.info(marker, pattern.apply(s), o, o1);

    }

    public void info(Marker marker, String s, Object... objects) {
        logger.info(marker, pattern.apply(s), objects);

    }

    public void info(Marker marker, String s, Throwable throwable) {
        logger.info(marker, pattern.apply(s), throwable);
    }

    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    public void warn(String s) {
        logger.warn(pattern.apply(s));
    }

    public void warn(String s, Object o) {
        logger.warn(pattern.apply(s), o);
    }

    public void warn(String s, Object o, Object o1) {
        logger.warn(pattern.apply(s), o, o1);

    }

    public void warn(String s, Object... objects) {
        logger.warn(pattern.apply(s), objects);

    }

    public void warn(String s, Throwable throwable) {
        logger.warn(pattern.apply(s), throwable);
    }

    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    public void warn(Marker marker, String s) {
        logger.warn(marker, pattern.apply(s));

    }

    public void warn(Marker marker, String s, Object o) {
        logger.warn(marker, pattern.apply(s), o);

    }

    public void warn(Marker marker, String s, Object o, Object o1) {
        logger.warn(marker, pattern.apply(s), o, o1);

    }

    public void warn(Marker marker, String s, Object... objects) {
        logger.warn(marker, pattern.apply(s), objects);

    }

    public void warn(Marker marker, String s, Throwable throwable) {
        logger.warn(marker, pattern.apply(s), throwable);
    }

    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    public void error(String s) {
        logger.error(pattern.apply(s));
    }

    public void error(String s, Object o) {
        logger.error(pattern.apply(s), o);
    }

    public void error(String s, Object o, Object o1) {
        logger.error(pattern.apply(s), o, o1);

    }

    public void error(String s, Object... objects) {
        logger.error(pattern.apply(s), objects);

    }

    public void error(String s, Throwable throwable) {
        logger.error(pattern.apply(s), throwable);
    }

    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    public void error(Marker marker, String s) {
        logger.error(marker, pattern.apply(s));

    }

    public void error(Marker marker, String s, Object o) {
        logger.error(marker, pattern.apply(s), o);

    }

    public void error(Marker marker, String s, Object o, Object o1) {
        logger.error(marker, pattern.apply(s), o, o1);

    }

    public void error(Marker marker, String s, Object... objects) {
        logger.error(marker, pattern.apply(s), objects);

    }

    public void error(Marker marker, String s, Throwable throwable) {
        logger.error(marker, pattern.apply(s), throwable);
    }
}
