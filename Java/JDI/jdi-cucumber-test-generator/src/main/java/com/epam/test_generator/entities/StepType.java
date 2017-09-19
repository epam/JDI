package com.epam.test_generator.entities;

public enum StepType {

	GIVEN ("Given"),
    WHEN ("When"),
    THEN ("Then"),
    AND ("And"),
    BUT ("But"),
    ANY ("*");

    private String typeName;

    StepType(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
