package com.epam.test_generator.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
public class Suit implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @JsonManagedReference
    @OneToMany(cascade = {CascadeType.MERGE},fetch= FetchType.EAGER, mappedBy = "suit")
    private List<Case> cases;

    public Suit() {
    }

    public Suit(Long id, String name, String description, List<Case> cases) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cases = cases;
    }

    public Suit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Suit(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Case> getCases() {
        return cases;
    }

    public void setCases(List<Case> cases) {
        this.cases = cases;
    }

    @Override
    public String toString() {
        return "Suit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cases=" + cases +
                '}';
    }
}