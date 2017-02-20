package org.mytests.uiobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import org.mytests.entities.User;
import org.mytests.enums.TopMenuItems;
import org.mytests.uiobjects.ImdbSite;
import org.openqa.selenium.support.FindBy;

import static org.mytests.entities.User.DefaultUser;
import static org.mytests.uiobjects.ImdbSite.loginMDBForm;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
public class HomePage extends WebPage {
    @FindBy(linkText = "Other Sign in options")
    public ILink signInLink;
    @FindBy(linkText = "Sign in with IMDb")
    public IButton signInViaMdbButton;
    @FindBy(css = "#consumer_main_nav .navCategory a")
    public Menu<TopMenuItems> topMenu;

    public void login() {
        login(DefaultUser);
    }

    public void login(User user) {
        signInLink.click();
        signInViaMdbButton.click();
        loginMDBForm.login(user);
    }
}
