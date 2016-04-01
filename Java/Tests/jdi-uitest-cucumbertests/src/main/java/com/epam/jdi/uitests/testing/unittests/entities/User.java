package com.epam.jdi.uitests.testing.unittests.entities;

/**
 * Created by Roman_Iovlev on 5/21/2015.
 */
public class User {
    public static final User DEFAULT_USER = new User();

    public String login = "epam";
    public String password = "1234";
    public String name = "Name";
    public String lastName = "Last Name";
    public String description = "Description";

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


}

