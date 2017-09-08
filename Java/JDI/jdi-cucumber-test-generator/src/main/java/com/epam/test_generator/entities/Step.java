package com.epam.test_generator.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Step implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Case parentCase;

    private int rowNumber;

    private String description;

    private StepType type;

    public Step() {
    }

    public Step(Long id, Case parentCase, int rowNumber, String description, StepType type) {
        this.id = id;
        this.parentCase = parentCase;
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

    public Case getParentCase() {
        return parentCase;
    }

    public void setParentCase(Case parentCase) {
        this.parentCase = parentCase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StepType getType() {
        return type;
    }

    public void setType(StepType type) {
        this.type = type;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getKeyword(){
        return type.getStepType();
    }


}
