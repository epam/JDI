package com.epam.test_generator.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.epam.test_generator.entities.Case;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SuitDTO {


    private Long id;

    private String name;

    private String description;

     @JsonManagedReference
     private List<CaseDTO> cases;

    private Integer priority;

    private Date creationDate;

    private String tags;

    public SuitDTO(String name, String description, Integer priority, String tags){
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationDate = Calendar.getInstance().getTime();
        this.tags = tags;
    }

    public SuitDTO(){

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

    public List<CaseDTO> getCases() {
        return cases;
    }

    public void setCases(List<CaseDTO> cases) {
        this.cases = cases;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

