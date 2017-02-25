package org.mytests.epam.site.entities;

/**
 * Created by Roman_Iovlev on 11/22/2016.
 */
public class Job {
    public String name, category, location;

    public Job() {  }
    public Job(String name, String category) {
        this(name, category, "");
    }
    public Job(String name, String category, String location) {
        this.name = name;
        this.category = category;
        this.location = location;
    }

}
