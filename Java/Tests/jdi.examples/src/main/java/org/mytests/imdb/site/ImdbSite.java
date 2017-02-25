package org.mytests.imdb.site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.*;
import org.mytests.imdb.site.pages.EditWatchListPage;
import org.mytests.imdb.site.pages.HomePage;
import org.mytests.imdb.site.pages.WatchListPage;
import org.mytests.imdb.site.sections.LoginMDBForm;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class ImdbSite extends WebSite {

    @JPage(url = "/")
    public static HomePage homePage;

    @JPage(url = "/user/ur72549867/lists?ref_=nv_usr_lst_3")
    public static WatchListPage watchListPage;
    public static EditWatchListPage editWatchListPage;

    public static LoginMDBForm loginMDBForm;


}
