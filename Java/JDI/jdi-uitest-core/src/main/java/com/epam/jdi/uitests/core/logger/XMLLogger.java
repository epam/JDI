package com.epam.jdi.uitests.core.logger;

import org.apache.log4j.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger in XML format class.
 */
public class XMLLogger {

    private Logger log;

    private SimpleDateFormat dateFormat;

    public XMLLogger(Class clazz) {
        this.log = Logger.getLogger(clazz);
    }

    public XMLLogger(Class clazz, String timePatternLayout) {
        this.log = Logger.getLogger(clazz);
        this.dateFormat = new SimpleDateFormat(timePatternLayout);
    }

    /**
     * Adds logs with "trace" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void trace(String message, ILambdaExpression lambda) {
        log.trace("<trace" + getCurrentTime() + ">" + message);
        lambda.doInternalAction();
        log.trace("</trace>");
    }

    /**
     * Adds logs with "trace" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void trace(String message) {
        log.trace("<trace" + getCurrentTime() + ">"
                + message + "</trace>");
    }

    /**
     * Adds logs with "debug" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void debug(String message, ILambdaExpression lambda) {
        log.debug("<debug" + getCurrentTime() + ">" + message);
        lambda.doInternalAction();
        log.debug("</debug>");
    }

    /**
     * Adds logs with "debug" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void debug(String message) {
        log.debug("<debug" + getCurrentTime() + ">"
                + message + "</debug>");
    }

    /**
     * Adds logs with "info" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void info(String message, ILambdaExpression lambda) {
        log.info("<info" + getCurrentTime() + ">" + message);
        lambda.doInternalAction();
        log.info("</info>");
    }

    /**
     * Adds logs with "info" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void info(String message) {
        log.info("<info" + getCurrentTime() + ">"
                + message + "</info>");
    }

    /**
     * Adds logs with "warn" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void warn(String message, ILambdaExpression lambda) {
        log.warn("<warn" + getCurrentTime() + ">" + message);
        lambda.doInternalAction();
        log.warn("</warn>");
    }

    /**
     * Adds logs with "warn" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void warn(String message) {
        log.warn("<warn" + getCurrentTime() + ">"
                + message + "</warn>");
    }

    /**
     * Adds logs with "error" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void error(String message, ILambdaExpression lambda) {
        log.error("<error" + getCurrentTime() + ">" + message);
        lambda.doInternalAction();
        log.error("</error>");
    }

    /**
     * Adds logs with "error" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void error(String message) {
        log.error("<error" + getCurrentTime() + ">"
                + message + "</error>");
    }

    /**
     * Adds logs with "fatal" level into logging file and console and executes another internal method.
     * @param message Message to be shown in logging file and console.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void fatal(String message, ILambdaExpression lambda) {
        log.fatal("<fatal" + getCurrentTime() + ">" + message);
        lambda.doInternalAction();
        log.fatal("</fatal>");
    }

    /**
     * Adds logs with "fatal" level into logging file and console.
     * @param message Message to be shown in logging file and console.
     */
    public void fatal(String message) {
        log.fatal("<fatal" + getCurrentTime() + ">"
                + message + "</fatal>");
    }

    /**
     * Adds log with one of the possible levels into logging file and console and executes lambda expression.
     * @param logLevel Provides the information about the log level.
     * @param message Message to be shown in logging file.
     * @param lambda Lambda expression without arguments which will be executed.
     */
    public void log (LogLevels logLevel, String message, ILambdaExpression lambda){
        switch(logLevel) {
            case ALL:
                trace(message, lambda);
                break;
            case TRACE:
                trace(message, lambda);
                break;
            case DEBUG:
                debug(message, lambda);
                break;
            case INFO:
                info(message, lambda);
                break;
            case WARNING:
                warn(message, lambda);
                break;
            case ERROR:
                error(message, lambda);
                break;
            case FATAL:
                fatal(message, lambda);
                break;
            case OFF :
                break;
        }
    }

    /**
     * Adds log with one of the possible levels into logging file and console
     * @param logLevel Provides the information about the log level.
     * @param message Message to be shown in logging file.
     */
    public void log(LogLevels logLevel, String message) {
        switch(logLevel) {
            case ALL:
            case TRACE:
                trace(message);
                break;
            case DEBUG:
                debug(message);
                break;
            case INFO:
                info(message);
                break;
            case WARNING:
                warn(message);
                break;
            case ERROR:
                error(message);
                break;
            case FATAL:
                fatal(message);
                break;
            case OFF :
                break;
        }
    }

    private String getCurrentTime(){
        return dateFormat != null ? " t=\"" + dateFormat.format(new Date()) + "\"" : "";
    }
}

/**
 * Test class that shows functionality of XMLLogger.
 */
class Test {

    private XMLLogger xmlLogger = new XMLLogger(ILogger.class, "yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Test test = new Test();

        test.method1();
    }

    void method1() {
        xmlLogger.info("message1");
        xmlLogger.debug("message2");
        xmlLogger.error("message3");
        xmlLogger.trace("message4");
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