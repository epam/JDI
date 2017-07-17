package org.mytests.epam.site.entities;

import java.io.File;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class Attendee {

    public String name = "Roman",
        lastName = "Iovlev",
        email = "roman_iovlev@epam.com",
        country = "Russian Federation",
        city = "Saint-Petersburg",
        cv = new File("src/test/resources/cv.txt").getAbsolutePath(),
        comment = "I WANT TO WORK IN EPAM!!!";

    public Attendee() { }
    public Attendee(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + lastName;
    }
}
