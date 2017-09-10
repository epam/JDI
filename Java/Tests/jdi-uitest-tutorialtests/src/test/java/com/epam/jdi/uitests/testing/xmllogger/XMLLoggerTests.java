package com.epam.jdi.uitests.testing.xmllogger;

import com.epam.jdi.uitests.core.logger.XMLLogger;
import org.testng.annotations.Test;

/**
 * Created by Roman_Iovlev on 9/10/2017.
 */
public class XMLLoggerTests {
    @Test
    public void xmlLoggerTest() {
        xmlLogger.info("message1");
        xmlLogger.debug("message2");
        xmlLogger.off("message3");
        xmlLogger.all("message4");
        method2();
    }
    private XMLLogger xmlLogger = new XMLLogger();

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
