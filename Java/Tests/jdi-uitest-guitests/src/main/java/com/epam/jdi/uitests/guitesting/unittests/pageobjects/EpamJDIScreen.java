package com.epam.jdi.uitests.guitesting.unittests.pageobjects;

import com.epam.jdi.uitests.gui.sikuli.elements.composite.Screen;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JLocation;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.JPage;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JScreen;
import com.epam.jdi.uitests.guitesting.unittests.pageobjects.pages.HomePage;
import com.epam.jdi.uitests.guitesting.unittests.pageobjects.sections.Footer;


/**
 * Created by Natalia_Grebenshchikova on 1/14/2016.
 */

@JScreen
public class EpamJDIScreen extends Screen {
    @JPage(fileLogoPath = "homePage.png", similarity = 1)
    public static HomePage homePage;
    @JLocation(filePath = "footer.png", similarity = 0.9)
    public static Footer footer;
}
