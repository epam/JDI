package org.mytests.uiobjects.example.entities;

import com.epam.commons.DataClass;

/**
 * Created by Roman_Iovlev on 5/26/2017.
 */
public class User extends DataClass<User> {
    public String name;
    public String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
