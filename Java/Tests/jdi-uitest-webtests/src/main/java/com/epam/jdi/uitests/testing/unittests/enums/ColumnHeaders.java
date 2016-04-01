package com.epam.jdi.uitests.testing.unittests.enums;

/**
 * Created by Natalia_Grebenshchik on 10/21/2015.
 */
public enum ColumnHeaders {
    col1("Column 1"),
    col2("Column 2"),
    col3("Column 3");

    public String value;

    ColumnHeaders(String value) {
        this.value = value;
    }
}
