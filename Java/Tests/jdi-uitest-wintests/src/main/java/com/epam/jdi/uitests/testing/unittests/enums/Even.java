package com.epam.jdi.uitests.testing.unittests.enums;

/**
 * Created by Roman_Iovlev on 9/15/2015.
 */
public enum Even {
    TWO(2), FOUR(4), SIX(6), EIGHT(8);

    public int value;

    Even(int value) {
        this.value = value;
    }
}
