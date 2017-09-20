package com.epam.test_generator.dto;

public class StepSuggestionDTO {
    private Long id;

    private String content;

    public StepSuggestionDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public StepSuggestionDTO(String content) {
        this.content = content;
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
}
