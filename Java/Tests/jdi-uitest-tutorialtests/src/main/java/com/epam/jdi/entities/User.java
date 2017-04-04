package com.epam.jdi.entities;

import lombok.AllArgsConstructor;

/**
 * Created by Roman_Iovlev on 5/21/2015.
 */
@AllArgsConstructor
public class User {
    public static final User DEFAULT = new User("UserTest", "Test Password");
    public static User currentUser;

    public String name;
    public String password;

}

