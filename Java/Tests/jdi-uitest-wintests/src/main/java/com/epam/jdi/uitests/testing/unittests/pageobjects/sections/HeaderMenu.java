package com.epam.jdi.uitests.testing.unittests.pageobjects.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.win.winium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Iuliia_Petrova on 6/15/2016.
 */
public class HeaderMenu extends  Section {
    @FindBy(id = "Home")
    public IButton home;

    @FindBy(id = "ContactForm")
    public IButton contactForm;

    @FindBy(id = "Service")
    public IButton service;

    @FindBy(id = "MetalsColors")
    public IButton metalsColors;


//    HOME("Home"),
//    CONTACT("Contact form"),
//    SERVICE("Service"),
//    METALSCOLORS("Metals & Colors");
//
//    public String value;
//
//    HeaderMenu(String value) {
//        this.value = value;
//    }
//
//    @Override
//    public String toString() {
//        return value;
//    }
}
