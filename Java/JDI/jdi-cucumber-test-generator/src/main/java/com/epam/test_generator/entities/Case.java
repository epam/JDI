package com.epam.test_generator.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Entity
public class Case implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Step> steps;

    private String creationDate;

    private Integer priority;

    private String tags;

    public Case(){
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        creationDate = formatter.format(Calendar.getInstance().getTime());
    }

    public Case(Long id, String description, List<Step> steps, Integer priority, String tags) {
        this.id = id;
        this.description = description;
        this.steps = steps;
        this.priority = priority;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
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
                "id=" + id +
                ", description='" + description + '\'' +
                ", steps=" + steps +
                ", creationDate=" + creationDate +
                ", priority=" + priority +
                ", tags='" + tags + '\'' +
                '}';
    }

}