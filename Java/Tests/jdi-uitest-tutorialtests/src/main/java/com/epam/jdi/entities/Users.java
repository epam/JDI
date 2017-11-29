package com.epam.jdi.entities;

/**
 * Created by Roman_Iovlev on 5/21/2015.
 */
/*@AllArgsConstructor*/

public class Users {
    public static final User DEFAULT = new User()
        .set(u->{u.login="epam";u.password="1234";});
    public static User currentUser;

}

