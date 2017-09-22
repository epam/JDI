package com.epam.test_generator.dto;

import java.io.Serializable;

public class TagDTO implements Serializable {

    private String name;

    public TagDTO() {
    }

    public TagDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
