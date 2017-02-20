package org.mytests.entities;

/**
 * Created by Roman_Iovlev on 2/9/2017.
 */
public class User {
    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User DefaultUser =
        new User("romanyister@gmail.com", "SeleniumCamp2017");
}
