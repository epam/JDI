package com.epam.http;

/**
 * Created by Roman_Iovlev on 12/19/2016.
 */
public class ExceptionHandler {

    public static RuntimeException exception(String message, Object... args) {
        return new RuntimeException(String.format(message, args));
    }
}
