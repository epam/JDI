package com.epam.test_generator.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Step implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int rowNumber;

    private String description;

    private StepType type;

    public Step() {
    }

    public Step(Long id, int rowNumber, String description, StepType type) {
        this.id = id;
        this.rowNumber = rowNumber;
        this.description = description;
        this.type = type;
    }

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
        return type.ordinal();
    }

    public void setType(Integer type) {
        this.type = StepType.values()[type];
    }


    @Override
    public String toString() {
        return "Step{" +
                "id=" + id +
                ", rowNumber=" + rowNumber +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }

}
