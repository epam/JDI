package com.epam.jdi.uitests.testing.unittests.enums;

import java.util.function.Supplier;

public enum Nature implements Supplier<String> {
    WATER("Water"), EARTH("Earth"), WIND("Wind"), FIRE("Fire");

    private String value;

    Nature(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
