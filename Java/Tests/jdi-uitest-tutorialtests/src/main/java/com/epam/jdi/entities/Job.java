package com.epam.jdi.entities;

import com.epam.commons.DataClass;


/**
 * Created by Roman_Iovlev on 11/22/2016.
 */
/*@AllArgsConstructor
@NoArgsConstructor*/
public class Job extends DataClass {
    public String name;
    public String description;
    public String location;

    public Job() {
    }

    public Job(String position, String description, String location) {
        this.name = position;
        this.description = description;
        this.location = location;
    }
}

