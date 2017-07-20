package com.epam.jdi.entities;

import com.epam.commons.DataClass;

/**
 * Created by Roman_Iovlev on 5/21/2015.
 */
/*@AllArgsConstructor*/

public class User extends DataClass {
    public static final User DEFAULT = new User("UserTest", "Test Password");
    public static User currentUser;

    public String name;
    public String password;

    public User (String userName, String userPassword) {
    this.name = userName;
    this.password = userPassword;
    }

}

