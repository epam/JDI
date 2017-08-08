package org.mytests.uiobjects.example.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Button;
import com.epam.jdi.uitests.web.selenium.elements.common.TextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import org.mytests.uiobjects.example.entities.User;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class LoginForm extends Form<User> {
    @FindBy(id="Login")
    public TextField name;
    @FindBy(id="Password")
    public TextField password;

    @FindBy(css="[type=submit]")
    public Button enter;
}
