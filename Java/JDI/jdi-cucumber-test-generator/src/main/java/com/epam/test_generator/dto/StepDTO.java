package com.epam.test_generator.dto;

public class StepDTO {

    private Long id;

    private int rowNumber;

    private String description;

    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StepDTO{" +
                "id=" + id +
                ", rowNumber=" + rowNumber +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}