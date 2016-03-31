package com.epam.jdi.uitests.web.testng.testRunner;

import com.epam.jdi.uitests.core.logger.JDILogger;
import org.slf4j.Marker;

import java.util.function.Function;

import static org.testng.Reporter.log;

/**
 * Created by Roman_Iovlev on 1/25/2016.
 */
public class TestNGLogger extends JDILogger {

    public TestNGLogger() {
    }
    public TestNGLogger(String name) {
        super(name);
    }
    public TestNGLogger(String name, Function<String, String> pattern) {
        super(name, pattern);
    }

    @Override
    public void trace(String s) {
        super.trace(s);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void trace(String s, Object o) {
        super.trace(s, o);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void trace(String s, Object o, Object o1) {
        super.trace(s, o, o1);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void trace(String s, Object... objects) {
        super.trace(s, objects);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void trace(String s, Throwable throwable) {
        super.trace(s, throwable);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void trace(Marker marker, String s) {
        super.trace(marker, s);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void trace(Marker marker, String s, Object o) {
        super.trace(marker, s, o);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void trace(Marker marker, String s, Object o, Object o1) {
        super.trace(marker, s, o, o1);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void trace(Marker marker, String s, Object... objects) {
        super.trace(marker, s, objects);
        log(String.format("%s %s", 0, s));

    }

    public void trace(Marker marker, String s, Throwable throwable) {
        super.trace(marker, s, throwable);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(String s) {
        super.debug(s);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(String s, Object o) {
        super.debug(s, o);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(String s, Object o, Object o1) {
        super.debug(s, o, o1);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(String s, Object... objects) {
        super.debug(s, objects);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(String s, Throwable throwable) {
        super.debug(s, throwable);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(Marker marker, String s) {
        super.debug(marker, s);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void debug(Marker marker, String s, Object o) {
        super.debug(marker, s, o);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(Marker marker, String s, Object o, Object o1) {
        super.debug(marker, s, o, o1);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void debug(Marker marker, String s, Object... objects) {
        super.debug(marker, s, objects);
        log(String.format("%s %s", 0, s));

    }

    @Override
    public void debug(Marker marker, String s, Throwable throwable) {
        super.debug(marker, s, throwable);
        log(String.format("%s %s", 0, s));
    }

    @Override
    public void info(String s) {
        super.info(s);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(String s, Object o) {
        super.info(s, o);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(String s, Object o, Object o1) {
        super.info(s, o, o1);
        log(String.format("%s %s", 1, s));

    }

    @Override
    public void info(String s, Object... objects) {
        super.info(s, objects);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(String s, Throwable throwable) {
        super.info(s, throwable);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(Marker marker, String s) {
        super.info(marker, s);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(Marker marker, String s, Object o) {
        super.info(marker, s, o);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(Marker marker, String s, Object o, Object o1) {
        super.info(marker, s, o, o1);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(Marker marker, String s, Object... objects) {
        super.info(marker, s, objects);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void info(Marker marker, String s, Throwable throwable) {
        super.info(marker, s, throwable);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(String s) {
        super.warn(s);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(String s, Object o) {
        super.warn(s, o);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(String s, Object o, Object o1) {
        super.warn(s, o, o1);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(String s, Object... objects) {
        super.warn(s, objects);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(String s, Throwable throwable) {
        super.warn(s, throwable);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(Marker marker, String s) {
        super.warn(marker, s);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(Marker marker, String s, Object o) {
        super.warn(marker, s, o);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(Marker marker, String s, Object o, Object o1) {
        super.warn(marker, s, o, o1);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(Marker marker, String s, Object... objects) {
        super.warn(marker, s, objects);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void warn(Marker marker, String s, Throwable throwable) {
        super.warn(marker, s, throwable);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(String s) {
        super.error(s);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(String s, Object o) {
        super.error(s, o);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(String s, Object o, Object o1) {
        super.error(s, o, o1);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(String s, Object... objects) {
        super.error(s, objects);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(String s, Throwable throwable) {
        super.error(s, throwable);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(Marker marker, String s) {
        super.error(marker, s);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(Marker marker, String s, Object o) {
        super.error(marker, s, o);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(Marker marker, String s, Object o, Object o1) {
        super.error(marker, s, o, o1);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(Marker marker, String s, Object... objects) {
        super.error(marker, s, objects);
        log(String.format("%s %s", 1, s));
    }

    @Override
    public void error(Marker marker, String s, Throwable throwable) {
        super.error(marker, s, throwable);
        log(String.format("%s %s", 1, s));
    }
}
