package com.epam.http.requests;

import io.restassured.internal.NameAndValue;
import io.restassured.internal.assertion.AssertParameter;

public class QueryParameter implements NameAndValue {

    private final String name;
    private final String value;


    public QueryParameter(String name, String value) {
        AssertParameter.notNull(name, "QueryParameter name");
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(name);
        if (value != null) {
            builder.append("=").append(value);
        }
        return builder.toString();
    }
}
