package com.epam.test_generator.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StepSuggestion {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    public StepSuggestion(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public StepSuggestion(String content) {
        this.content = content;
    }

    public StepSuggestion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
