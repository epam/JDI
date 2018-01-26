package com.epam.jdi.entities;

import com.epam.commons.DataClass;


/**
 * Created by Roman_Iovlev on 11/22/2016.
 */
public class Vacancy extends DataClass {
    public String name;
    public String description;
    public String apply;

    public Vacancy() {
    }

    public Vacancy(String position, String description) {
        this.name = position;
        this.description = description;
    }
}