package com.epam.jdi.uitests.core.exceptions;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException() {
        super();
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}