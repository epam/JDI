package com.epam.test_generator.dto;

public class AutoCompleteDTO {
    private Long id;

    private String content;

    public AutoCompleteDTO(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public AutoCompleteDTO(String content) {
        this.content = content;
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
