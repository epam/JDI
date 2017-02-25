package org.mytests.angular.site;

import com.epam.jdi.uitests.web.selenium.elements.composite.WebSite;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.JPage;
import org.mytests.angular.site.pages.AngularPage;

/**
 * Created by Roman_Iovlev on 8/30/2015.
 */
public class AngularSite extends WebSite {

    @JPage(url = "https://material.angularjs.org/latest/demo/input")
    public static AngularPage angularPage;

}
