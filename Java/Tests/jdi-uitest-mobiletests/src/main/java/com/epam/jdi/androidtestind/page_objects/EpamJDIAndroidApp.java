package com.epam.jdi.androidtestind.page_objects;

import com.epam.jdi.uitests.mobile.appium.elements.composite.Site;
import com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.JPage;
import com.epam.jdi.uitests.mobile.appium.elements.pageobjects.annotations.JApp;
import com.epam.jdi.androidtestind.page_objects.pages.*;

/**
 * Created by Natalia_Grebenshchikova on 12/25/2015.
 */
@JApp
public class EpamJDIAndroidApp extends Site {
    @JPage(title = "Add Contact")
    public static AddContactForm addContactForm;
    @JPage(title = "Contacts")
    public static StartPage startPage;
}
