package com.epam.test_generator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Tag implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name.toLowerCase();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    @Override
    public String toString() {
        return name;
    }
}
