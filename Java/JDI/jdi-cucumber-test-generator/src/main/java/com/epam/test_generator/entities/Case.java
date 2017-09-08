package com.epam.test_generator.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class Case implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "parentCase")
    private List<Step> steps;

//    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE, fetch= FetchType.EAGER)
    private Suit suit;

    private int priority;

    private Date creationDate;

    private String tags;

    public Case(){
    }

    public Case(Long id, String description, List<Step> steps, Suit suit) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.suit = suit;
    }

    public Long getId() {
        return id;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
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

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Case{" +
                "feature='" + description + '\'' +
                ", scenario='" + steps + '\'' +
                '}';
    }
}