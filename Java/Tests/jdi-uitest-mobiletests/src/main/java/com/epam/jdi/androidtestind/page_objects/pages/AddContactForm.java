package com.epam.jdi.androidtestind.page_objects.pages;

import com.epam.jdi.uitests.mobile.appium.elements.common.Button;
import com.epam.jdi.uitests.mobile.appium.elements.common.TextField;
import com.epam.jdi.uitests.mobile.appium.elements.composite.Form;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Natalia_Grebenshchikova on 12/25/2015.
 */
public class AddContactForm extends Form<ContactDetails> {

    @FindBy (xpath = "//*[@*='com.example.android.contactmanager:id/contactNameEditText']")
    public TextField contactName;

    @FindBy (xpath = "//*[@*='com.example.android.contactmanager:id/contactPhoneEditText']")
    public TextField phone;

    @FindBy (className = "android.widget.Button")
    public Button save;

}
