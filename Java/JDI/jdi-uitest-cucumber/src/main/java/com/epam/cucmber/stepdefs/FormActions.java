package com.epam.cucmber.stepdefs;

/**
 * Created by Dmitry_Lebedev1 on 1/19/2016.
 */
public enum FormActions {
    SUBMIT("submit"), FILL("fill"), CHECK("check");

    private final String text;

    FormActions(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
