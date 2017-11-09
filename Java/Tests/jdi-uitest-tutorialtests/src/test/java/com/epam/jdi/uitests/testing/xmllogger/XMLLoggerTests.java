package com.epam.jdi.uitests.testing.xmllogger;

import com.epam.jdi.uitests.core.logger.XMLLogger;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;

/**
 * Created by Roman_Iovlev on 9/10/2017.
 */
public class XMLLoggerTests {

    private XMLLogger xmlLogger;

    @Test
    public void xmlLoggerTest() {
        xmlLogger = new XMLLogger();
        xmlLogger.info("message1");
        xmlLogger.debug("message2");
        method2();
    }
    @Test
    public void xmlLoggerWithTimeTest(){
        xmlLogger = new XMLLogger(new SimpleDateFormat("mm:ss.SS"));
        xmlLogger.info("message1");
        xmlLogger.debug("message2");
        method2();
    }
    void method2() {
        xmlLogger.info("message5", this::method3);
    }

    void method3() {
        xmlLogger.error("message6", this::method4);
    }

    void method4() {
        xmlLogger.debug("message7");
    }
}
