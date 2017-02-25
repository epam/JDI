package org.mytests.imdb.site.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.web.selenium.elements.complex.Menu;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import org.mytests.imdb.entities.User;
import org.mytests.imdb.enums.TopMenuItems;
import org.mytests.imdb.site.ImdbSite;
import org.openqa.selenium.support.FindBy;

import static org.mytests.imdb.entities.User.ImdbUser;

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
        login(ImdbUser);
    }

    public void login(User user) {
        signInLink.click();
        signInViaMdbButton.click();
        ImdbSite.loginMDBForm.login(user);
    }
}
