package org.mytests.uiobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import org.mytests.entities.User;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class LoginMDBForm extends Form<User> {
    @FindBy(name = "email")         public ITextField email;
    @FindBy(name = "password")      public ITextField password;
    @FindBy(id = "signInSubmit")    public IButton signInButton;
}
