package com.epam.jdi.site.gitepam;

import com.epam.jdi.entities.User;
import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 9/9/2015.
 */
public class Login extends Form<User> {
    @FindBy(id = "Login") static ITextField login;
    @FindBy(id = "Password") static ITextField password;
    @FindBy(css = ".btn-login") static IButton loginButton;
    @FindBy(css = ".profile-photo") static IClickable profile;

    public static void loginWith(User user) {
        profile.click();
        login.newInput(user.login);
        password.newInput(user.password);
        loginButton.click();
    }
}
