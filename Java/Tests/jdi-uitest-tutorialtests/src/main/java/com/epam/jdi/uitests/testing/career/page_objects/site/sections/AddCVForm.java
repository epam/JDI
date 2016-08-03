package com.epam.jdi.uitests.testing.career.page_objects.site.sections;

import com.epam.jdi.uitests.core.interfaces.common.IButton;
import com.epam.jdi.uitests.core.interfaces.common.ITextArea;
import com.epam.jdi.uitests.core.interfaces.common.ITextField;
import com.epam.jdi.uitests.core.interfaces.complex.IDropDown;
import com.epam.jdi.uitests.testing.career.page_objects.entities.Attendee;
import com.epam.jdi.uitests.web.robot.RFileInput;
import com.epam.jdi.uitests.web.selenium.elements.composite.Form;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 10/23/2015.
 */
public class AddCVForm extends Form<Attendee> {
    @FindBy(css = "[placeholder='First Name']")
    ITextField name;
    @FindBy(css = "[placeholder='Last Name']")
    ITextField lastName;
    @FindBy(css = "[placeholder='Email']")
    ITextField email;

    @JDropdown(
        root = @FindBy(className = "country-selection"),
        value = @FindBy(css = ".country-wrapper .arrow"),
        elementByName = @FindBy(xpath = "*root*//*[contains(@id,'select-box-applicantCountry')]//li[.='%s']"))
    IDropDown country;

    @JDropdown(
            root = @FindBy(className = "city-selection"),
            expand = @FindBy(css = ".city-wrapper .arrow"),
            list = @FindBy(xpath = "*root*//*[contains(@id,'select-box-applicantCity')]//li")
    )
    IDropDown city;

    @FindBy(css = ".file-upload")
    RFileInput cv;
    @FindBy(css = ".comment-input")
    ITextArea comment;

    @FindBy(xpath = "//*[.='Submit']")
    IButton submit;
    @FindBy(xpath = "//*[.='Cancel']")
    IButton cancel;


}
