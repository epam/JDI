package com.epam.jdi.uitests.testing.unittests.enums;

/**
 * Created by Roman_Iovlev on 9/21/2015.
 */
public enum Nature {
    WATER("Water"), EARTH("Earth"), WIND("Wind"), FIRE("Fire");

    public String value;

    Nature(String value) {
        this.value = value;
    }
}
