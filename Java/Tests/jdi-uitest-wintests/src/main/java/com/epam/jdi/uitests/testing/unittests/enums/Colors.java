package com.epam.jdi.uitests.testing.unittests.enums;

import java.util.function.Supplier;

public enum Colors implements Supplier<String> {
    COLORS("Colors"), RED("Red"), GREEN("Green"), BLUE("Blue"), YELLOW("Yellow");

    private String value;

    Colors(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
