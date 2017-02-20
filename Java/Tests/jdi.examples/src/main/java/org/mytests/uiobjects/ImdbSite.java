package org.mytests.uiobjects;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.*;
import org.mytests.uiobjects.pages.EditWatchListPage;
import org.mytests.uiobjects.pages.HomePage;
import org.mytests.uiobjects.pages.WatchListPage;
import org.mytests.uiobjects.sections.LoginMDBForm;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
@JSite(domain = "https://www.epam.com")
public class ImdbSite extends WebSite {

    @JPage(url = "/")
    public static HomePage homePage;
    @JPage(url = "/user/ur72549867/lists?ref_=nv_usr_lst_3")
    public static WatchListPage watchListPage;
    public static EditWatchListPage editWatchListPage;

    public static LoginMDBForm loginMDBForm;


}
