package com.epam.jdi.entities;

/**
 * Created by Roman_Iovlev on 5/21/2015.
 */
/*@AllArgsConstructor*/

public class Users {
    public static final User DEFAULT = new User()
        .set(u->{u.name="UserTest";u.password="Test Password";});
    public static User currentUser;

}

