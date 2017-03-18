package com.epam.jdi.androidtestind.page_objects.pages;

import com.epam.jdi.uitests.mobile.appium.elements.common.Button;
import com.epam.jdi.uitests.mobile.appium.elements.composite.Section;
import org.openqa.selenium.support.FindBy;
/**
 * Created by Natalia_Grebenshchikova on 12/25/2015.
 */
public class StartPage extends Section {
    @FindBy(className = "android.widget.Button")
    public Button addContact;
}
