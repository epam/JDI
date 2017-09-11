package com.epam.jdi.uitests.testing.unittests.enums;

import java.util.function.Supplier;

public enum Metals implements Supplier<String> {
    COL("Col"), GOLD("Gold"), SILVER("Silver"), BRONZE("Bronze"), SELEN("Selen");

    private String value;

    Metals(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
