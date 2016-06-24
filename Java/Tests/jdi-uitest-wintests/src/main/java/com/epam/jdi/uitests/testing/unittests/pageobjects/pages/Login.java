package com.epam.jdi.uitests.testing.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.testing.unittests.User;
import com.epam.jdi.uitests.win.winium.elements.composite.Form;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Maksim_Palchevskii on 9/9/2015.
 */
public class Login extends Form<User> {
    @FindBy(id = "Login")
    private ITextField login;
    @FindBy(id = "Password")
    private ITextField password;
    @FindBy(css = ".btn-login")
    private IButton loginButton;
    @FindBy(css = ".profile-photo")
    private IClickable profile;

    @Override
    public void submit(User user) {
        profile.click();
        super.submit(user);
    }
}
