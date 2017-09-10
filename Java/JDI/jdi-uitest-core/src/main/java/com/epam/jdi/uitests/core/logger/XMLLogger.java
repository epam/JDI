package com.epam.jdi.uitests.core.logger;

import com.epam.commons.linqinterfaces.JAction;
import org.apache.log4j.Logger;

/**
 * Logger in XML format class.
 */
public class XMLLogger {

    private Logger log;

    public XMLLogger() {
        this.log = Logger.getLogger("JDI Logger");
    }
    public XMLLogger(Class clazz) {
        this.log = Logger.getLogger(clazz);
    }
    public XMLLogger(String name) {
        this.log = Logger.getLogger(name);
    }

    /**
     * Adds logs with "info" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void info(String message, JAction lambda) {
        log(LogLevels.INFO, message, lambda);
    }

    /**
     * Adds logs with "info" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void info(String message) {
        log(LogLevels.INFO, message);
    }

    /**
     * Adds logs with "warn" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void warn(String message, JAction lambda) {
        log(LogLevels.WARNING, message, lambda);
    }

    /**
     * Adds logs with "warn" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void warn(String message) {
        log(LogLevels.WARNING, message);
    }

    /**
     * Adds logs with "off" level into logging file and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void off(String message, JAction lambda) {
        log(LogLevels.OFF, message, lambda);
    }

    /**
     * Adds logs with "off" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void off(String message) {
        log(LogLevels.OFF, message);
    }

    /**
     * Adds logs with "error" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void error(String message, JAction lambda) {
        log(LogLevels.ERROR, message, lambda);
    }

    /**
     * Adds logs with "error" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void error(String message) {
        log(LogLevels.ERROR, message);
    }

    /**
     * Adds logs with "debug" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void debug(String message, JAction lambda) {
        log(LogLevels.DEBUG, message, lambda);
    }

    /**
     * Adds logs with "debug" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void debug(String message) {
        log(LogLevels.DEBUG, message);
    }

    /**
     * Adds logs with "trace" level into logging file and executes another internal method and console.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void trace(String message, JAction lambda) {
        log(LogLevels.TRACE, message, lambda);
    }

    /**
     * Adds logs with "trace" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void trace(String message) {
        log(LogLevels.TRACE, message);
    }

    /**
     * Adds logs with "fatal" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void fatal(String message, JAction lambda) {
        log(LogLevels.FATAL, message, lambda);
    }

    /**
     * Adds logs with "fatal" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void fatal(String message) {
        log(LogLevels.FATAL, message);
    }

    /**
     * Adds logs with "all" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void all(String message, JAction lambda) {
        log(LogLevels.ALL, message, lambda);
    }

    /**
     * Adds logs with "all" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void all(String message) {
        log(LogLevels.ALL, message);
    }

    /**
     * Adds log with one of the possible levels into logging file and console and executes lambda expression.
     * @param logLevel Provides the information about the log level.
     * @param message Message to be shown in logging file.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void log (LogLevels logLevel, String message, JAction lambda){
        log.info("<" + getLogName(logLevel) + ">" + message);
        lambda.invoke();
        log.info("</" + getLogName(logLevel) + ">");
    }
    private String getLogName(LogLevels logLevel) {
        return logLevel.toString().toLowerCase();
    }

    /**
     * Adds log with one of the possible levels into logging file and console
     * @param logLevel Provides the information about the log level.
     * @param message Message to be shown in logging file.
     */
    public void log(LogLevels logLevel, String message) {
        log.info("<" + getLogName(logLevel) + ">" + message + "</" + getLogName(logLevel) + ">");
    }
}
