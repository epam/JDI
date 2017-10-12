package com.epam.test_generator.dto;

public class StepSuggestionDTO {
    private Long id;

    private String content;

    private Integer type;

    public StepSuggestionDTO(Long id, String content, Integer type) {
        this.id = id;
        this.content = content;
        this.type = type;
    }

    public StepSuggestionDTO(String content, Integer type) {
        this.content = content;
        this.type = type;
    }

    public StepSuggestionDTO() {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
