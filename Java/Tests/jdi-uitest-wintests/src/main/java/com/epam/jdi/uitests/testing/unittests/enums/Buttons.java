package com.epam.jdi.uitests.testing.unittests.enums;

import java.util.function.Supplier;

public enum Buttons implements Supplier<String> {
    SUBMIT("submit"),
    CALCULATE("calculate");

    private String value;

    Buttons(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
