package com.epam.jdi.uitests.core.logger;

import org.apache.log4j.Logger;

/**
 * Logger in XML format class.
 */
public class XMLLogger {

    private Logger log;

    public XMLLogger(Class clazz) {
        this.log = Logger.getLogger(clazz);
    }

    /**
     * Adds logs with "info" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void info(String message, ILambdaExpression lambda) {
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
    public void warn(String message, ILambdaExpression lambda) {
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
    public void off(String message, ILambdaExpression lambda) {
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
    public void error(String message, ILambdaExpression lambda) {
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
    public void debug(String message, ILambdaExpression lambda) {
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
    public void trace(String message, ILambdaExpression lambda) {
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
    public void fatal(String message, ILambdaExpression lambda) {
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
    public void all(String message, ILambdaExpression lambda) {
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
    public void log (LogLevels logLevel, String message, ILambdaExpression lambda){
        log.info("<" + logLevel.toString().toLowerCase() + ">" + message);
        lambda.doInternalAction();
        log.info("</" + logLevel.toString().toLowerCase() + ">");
    }

    /**
     * Adds log with one of the possible levels into logging file and console
     * @param logLevel Provides the information about the log level.
     * @param message Message to be shown in logging file.
     */
    public void log(LogLevels logLevel, String message) {
        log.info("<" + logLevel.toString().toLowerCase() + ">" + message + "</" + logLevel.toString().toLowerCase() + ">");
    }
}

/**
 * Test class that shows functionality of XMLLogger.
 */
class Test {

    private XMLLogger xmlLogger = new XMLLogger(Test.class);

    public static void main(String[] args) {
        Test test = new Test();

        test.method1();
    }

    void method1() {
        xmlLogger.info("message1");
        xmlLogger.debug("message2");
        xmlLogger.off("message3");
        xmlLogger.all("message4");
        method2();
    }

    void method2() {
        xmlLogger.info("message5", () -> method3());
    }

    void method3() {
        xmlLogger.error("message6", () -> method4());
    }

    void method4() {
        xmlLogger.debug("message7");
    }
}