package com.epam.jdi.uitests.testing.unittests.enums;

/**
 * Created by Roman_Iovlev on 9/15/2015.
 */
public enum Odds {
    ONE("1"), THREE("3"), FIVE("5"), SEVEN("7");

    public String value;

    Odds(String value) {
        this.value = value;
    }
}
