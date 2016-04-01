package com.epam.jdi.uitests.testing.unittests.entities;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dmitry_Lebedev1 on 10/15/2015.
 */
public class Contact {

    public static final Contact DEFAULT_CONTACT = defaultContact();

    public String name;
    public String lastName;
    public String description;

    public Contact(String name, String lastName, String description) {
        this.name = name;
        this.lastName = lastName;
        this.description = description;
    }

    public static Contact defaultContact() {
        return new Contact("John", "Doe", "Description");
    }

    public Contact() {
//        this("John", "Doe", "Description");
    }

    public List<String> toList() {
        return Arrays.asList(name, lastName, description);
    }

    @Override
    public String toString() {
        return String.format(
                "Summary: 3\n"
                        + "Name: %s\n"
                        + "Last Name: %s\n"
                        + "Description: %s",
                name, lastName, description);
    }
}
