package com.epam.commons.logger.aop;

import java.util.function.Consumer;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

/**
 * Created by Roman_Iovlev on 7/30/2017.
 */
public class Logger {
    public static Consumer<String> writeLog = msg ->
        out.println(format("[%s] %s", currentTimeMillis(), msg));
    public static void write(String msg) {
        if (writeLog != null)
            writeLog.accept(msg);
        else out.println("Can't write '"+msg+"'. Logger.writeLog function not specified");
    }
}
