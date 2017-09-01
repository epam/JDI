package com.epam.test_generator.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;


@Entity
public class Suit implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany
    private List<Case> cases;

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
