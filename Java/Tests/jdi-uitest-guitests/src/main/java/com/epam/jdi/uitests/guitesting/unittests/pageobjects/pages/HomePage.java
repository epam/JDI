package com.epam.jdi.uitests.guitesting.unittests.pageobjects.pages;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ICheckBox;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.gui.sikuli.elements.common.Button;
import com.epam.jdi.uitests.gui.sikuli.elements.composite.Page;
import com.epam.jdi.uitests.gui.sikuli.elements.enums.OffsetUnits;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JLocation;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.JOffset;
import com.epam.jdi.uitests.gui.sikuli.elements.pageobjects.annotations.elements.common.JCheckBox;

import static com.epam.jdi.uitests.guitesting.unittests.pageobjects.EpamJDIScreen.homePage;

/**
 * Created by Natalia_Grebenshchik on 1/14/2016.
 */

public class HomePage extends Page {
    @JCheckBox (checkImg = "HomePage/searchOrg.png", unCheckImg = "555", similarity = 0.9)
    public ICheckBox searchField;
    @JLocation(filePath = "HomePage/searchOrg.png")
    @JOffset(xOffset = 20, offsetUnit = OffsetUnits.PERCENTAGE)
    public IButton searchButton;
    @JLocation(filePath = "HomePage/contactLogo.png")
    public IButton contactLogo;

    @JLocation(filePath = "HomePage/contactLogo.png")
    public ITextField userName;
    @JLocation(filePath = "HomePage/password.png")
    public ITextField password;
    @JLocation(filePath = "HomePage/submit.png")
    public Button submit;

    public void login() {
        homePage.contactLogo.click();
        homePage.userName.input("epam");
        homePage.password.input("1234");
        homePage.submit.click();
    }
}
