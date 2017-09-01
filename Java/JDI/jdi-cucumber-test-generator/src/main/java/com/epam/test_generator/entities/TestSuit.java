package com.epam.test_generator.entities;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import java.util.List;


@Entity
public class TestSuit {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @OneToMany
    private List<TestCase> cases;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TestCase> getCases() {
        return cases;
    }

    public void setCases(List<TestCase> cases) {
        this.cases = cases;
    }
}
