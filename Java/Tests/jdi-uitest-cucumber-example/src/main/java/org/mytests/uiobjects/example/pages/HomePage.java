package org.mytests.uiobjects.example.pages;

import com.epam.jdi.uitests.web.selenium.elements.common.Text;
import com.epam.jdi.uitests.web.selenium.elements.composite.WebPage;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JFindBy;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;

/**
 * Created by Roman_Iovlev on 10/22/2015.
 */
@JPage(url = "/index.htm", title = "Index Page")
public class HomePage extends WebPage {

    @JFindBy(css = ".profile-photo span")
    public Text userName;

}
