package com.epam.jdi.uitests.testing.unittests.pageobjects.w3cSite;

import com.epam.jdi.uitests.win.winium.elements.composite.WebSite;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.win.winium.elements.pageobjects.annotations.JSite;

/**
 * Created by Maksim_Palchevskii on 9/10/2015.
 */

@JSite(domain = "http://www.w3schools.com")
public class W3cSite extends WebSite {
    @JPage(url = "/tags/tag_button.asp")
    public static FramePage framePage;

}