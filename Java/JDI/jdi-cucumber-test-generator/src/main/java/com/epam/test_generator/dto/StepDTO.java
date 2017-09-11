package com.epam.test_generator.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.StepType;

public class StepDTO {

    private Long id;

    private int rowNumber;

    @JsonBackReference
    private Case parentCase;

    private String description;

    private String type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StepDTO{" +
                "id=" + id +
                ", rowNumber=" + rowNumber +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}