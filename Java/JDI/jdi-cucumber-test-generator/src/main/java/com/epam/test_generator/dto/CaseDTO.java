package com.epam.test_generator.dto;

import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.Suit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Date;
import java.util.List;

public class CaseDTO {
    private Long id;

    private String description;

    @JsonManagedReference
    private List<StepDTO> steps;

    @JsonBackReference
    private SuitDTO suit;

    private Date creationDate;

    private Integer priority;

    private String tags;


    public Long getId() {
        return id;
    }

    public SuitDTO getSuit() {
        return suit;
    }

    public void setSuit(SuitDTO suit) {
        this.suit = suit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDTO> steps) {
        this.steps = steps;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public int getPriority() {
        return priority;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Case{" +
                "feature='" + description + '\'' +
                ", scenario='" + steps + '\'' +
                '}';
    }
}



