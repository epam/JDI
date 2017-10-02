package com.epam.test_generator.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class CaseDTO {
    private Long id;

    private String description;

    private List<StepDTO> steps;

    private String creationDate;

    private String updateDate;

    private Integer priority;

    private Set<TagDTO> tags;

    public CaseDTO() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        creationDate = formatter.format(Calendar.getInstance().getTime());
        updateDate = formatter.format(Calendar.getInstance().getTime());

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

    public List<StepDTO> getSteps() {
        return steps;
    }

    public void setSteps(List<StepDTO> steps) {
        this.steps = steps;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "CaseDTO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", steps=" + steps +
                ", creationDate=" + creationDate +
                ", priority=" + priority +
                ", tags='" + tags + '\'' +
                '}';
    }

}



